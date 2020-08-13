package Test

import com.sksamuel.elastic4s.ElasticDsl.{search, _}
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.requests.searches.SearchResponse
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}

import scala.collection.mutable.ArrayBuffer

object main2 extends App {

  case class Pracownik(name: String, surname: String, earnings: Double) {
        override def toString: String = name + " " + surname + " " + earnings
  }

  val client = ElasticClient(JavaClient(ElasticProperties(s"http://${sys.env.getOrElse("ES_HOST", "127.0.0.1")}:${sys.env.getOrElse("ES_PORT", "9200")}")))

  val pracownik1 = Pracownik("Adrian", "Wydra", 3000)
  val pracownik2 = Pracownik("Rafał", "Gęgotek", 3000)
  val pracownik3 = Pracownik("Wojciech", "Lepich", 3000)
  val pracownik4 = Pracownik("Przemysław", "Lechowicz", 3000)

  //  client.execute {
  //    createIndex("pracownicy").mapping(
  //      properties(
  //        TextField("name"),
  //        TextField("surname"),
  //        DoubleField("earnings")
  //      )
  //    )
  //  }
  //  client.execute {
  //    bulk(
  //      indexInto("pracownicy").fields("name" -> pracownik1.name, "surname" -> pracownik1.surname, "earnings" -> pracownik1.earnings),
  //      indexInto("pracownicy").fields("name" -> pracownik2.name, "surname" -> pracownik2.surname, "earnings" -> pracownik2.earnings),
  //      indexInto("pracownicy").fields("name" -> pracownik3.name, "surname" -> pracownik3.surname, "earnings" -> pracownik3.earnings),
  //      indexInto("pracownicy").fields("name" -> pracownik4.name, "surname" -> pracownik4.surname, "earnings" -> pracownik4.earnings),
  //    )
  //  }


//  implicit object PracownikHitReader extends HitReader[Pracownik] {
//    override def read(hit: Hit): Either[Throwable,Pracownik] = {
//      val source = hit.sourceAsMap
//      Right(Pracownik(source("name").toString, source("location").toString, source("location").toString.toDouble))
//      //        Pracownik(hit.sourceAsMap("name").toString, hit.sourceAsMap("surname").toString, hit.sourceAsMap("earrings").toString.toDouble)
//    }
//  }

  val listaPracownikow = new ArrayBuffer[Pracownik]()
    val resp = client.execute {
      search("pracownicy")
    }.await

  resp match {
    case failure: RequestFailure => println("We failed " + failure.error)
    case results: RequestSuccess[SearchResponse] => {

      //        val workers :Seq[Pracownik] = resp.result.to[Pracownik]
      //        println(workers)

      val details = results.body.get

      val pat = """"hits":\[.*?]""".r
      val source = """"_source":\{.*?}""".r
      val brackets = """\{.*?}""".r
      val namePattern = """(?<="name":").*?(?=")""".r
      val surnamePattern = """(?<="surname":").*?(?=")""".r
      val earningsPattern = """(?<="earnings":).*?(?=})""".r


      val hits = pat.findAllIn(details).mkString
      val sources = source.findAllIn(hits).mkString
      val newStr = brackets.findAllIn(sources).mkString

      val finalJson = newStr.replace("}{", "};{")
      val arrayString = finalJson.split(";")

      for (i <- arrayString) {
        val name = namePattern.findAllIn(i).mkString
        val surname = surnamePattern.findAllIn(i).mkString
        val earnings = earningsPattern.findAllIn(i).mkString.trim.toDouble
        val pracownik = new Pracownik(name, surname, earnings)
        listaPracownikow.append(pracownik)
      }
    }
  }

  listaPracownikow.foreach(println)

  client.close()

}
