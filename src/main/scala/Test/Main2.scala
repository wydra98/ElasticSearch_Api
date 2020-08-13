package Test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.util.ByteString

import scala.concurrent.Future
import scala.util.{Failure, Success}

object Main2 extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val port = "http://localhost:9200"
  val index = "kibana_sample_data_flights"

  val request = HttpRequest(
    method = HttpMethods.GET,
    uri = s"$port/$index/_search?pretty=true",
    // entity = HttpEntity(ContentTypes.`application/json`, "{ \"query\": { \"match_all\" : {} } }")
  )

  val responseFuture: Future[HttpResponse] = Http().singleRequest(request)
  responseFuture
    .onComplete {
      case Success(res) => {
        println(res + "\n")
        res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
          println("Got response, body: \n" + body.utf8String)
        }
      }
      case Failure(_) => sys.error("something wrong")
    }

  /** ************ Stworzenie i pobranie indexu PUT *********************
   * ZAPISUJE NIEANULOWANE LOTY, GRUPUJE PO LINIACH LOTNICZYCH I AGREGUJE DO NICH LICZBĘ LOTÓW, MINIMALNA SUMA OPÓŻNIEŃ WSZYSTKICH LOTÓW,
   * MINIMALNA SUMA OPÓŻNIEŃ WSZYSTKICH LOTÓW A NASTĘPNIE WYCIĄGA Z TEGO ŚREDNIĄ PROCENTOWA OPOZNIEN.
   * ***/

  val request2 = HttpRequest(
    method = HttpMethods.PUT,
    uri = s"$port/_transform/first_transform",
    entity = HttpEntity(ContentTypes.`application/json`,
      """{
      "source": {
        "index": "kibana_sample_data_flights",
        "query": {
        "bool": {
        "filter": [ {"term": {"Cancelled": false}}
        ]
      }
      }
      }
      ,
      "dest": {
        "index": "sample_flight_delays_by_carrier"
      }
      ,
      "sync": {
        "time": {
        "field": "timestamp",
        "delay": "60s"
      }
      }
      ,
      "pivot": {
        "group_by": {
        "carrier": {"terms": {"field": "Carrier"}}
      },
        "aggregations": {
        "flights_count": {"value_count": {"field": "FlightNum"}},
        "delay_mins_total": {"sum": {"field": "FlightDelayMin"}},
        "flight_mins_total": {"sum": {"field": "FlightTimeMin"}},
        "delay_time_percentage": {
        "bucket_script": {
        "buckets_path": {
        "delay_time": "delay_mins_total.value",
        "flight_time": "flight_mins_total.value"
      },
        "script": "(params.delay_time / params.flight_time) * 100"
      }
      }
      }
      }
    }""""))


  val responseFuture2: Future[HttpResponse] = Http().singleRequest(request2)

  responseFuture2
    .onComplete {
      case Success(res) => {
        println(res + "\n")
        res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
          // println("Got response, body: \n" + body.utf8String)
        }
      }
      case Failure(_) => sys.error("something wrong")
    }

  val request_2 = HttpRequest(
    method = HttpMethods.POST,
    uri = s"$port/_transform/first_transform/_start")

  val responseFuture_2: Future[HttpResponse] = Http().singleRequest(request_2)
  responseFuture_2
    .onComplete {
      case Success(res) => {
        println(res + "\n")
        res.entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
          println("Got response, body: \n" + body.utf8String)
        }
      }
      case Failure(_) => sys.error("something wrong")
    }




}