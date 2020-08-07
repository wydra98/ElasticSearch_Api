package Test

import com.sksamuel.elastic4s.ElasticDsl.indexInto
import com.sksamuel.elastic4s.fields.TextField
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.searches.SearchResponse

object Main extends App {

  val client = ElasticClient(JavaClient(ElasticProperties(s"http://${sys.env.getOrElse("ES_HOST", "127.0.0.1")}:${sys.env.getOrElse("ES_PORT", "9200")}")))

  client.execute {
    createIndex("artists").mapping(
      properties(
        TextField("name")
      )
    )
  }

  //Usuwanie indexu (wszystkich _all)
  //  client.execute {
  //    deleteIndex("_all")
  //  }

  client.execute {
    bulk(
      indexInto("artists").fields("name" -> "Fajny artysta 1").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Fajny artysta 2").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Fajny artysta 3").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Fajny artysta 4").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Fajny artysta 5").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Fajny artysta 6").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Fajny artysta 7").refresh(RefreshPolicy.Immediate)
    )
  }

  client.execute{
    bulk {
      deleteById("artists", "D7YsyXMBsMEc3Oxj2Fvx")
      deleteById("artists", "ELYsyXMBsMEc3Oxj2Fvx")
      deleteById("artists", "EbYsyXMBsMEc3Oxj2Fvx")
    }
  }


  val resp = client.execute {
    search("artists").query("Fajny")
  }.await


  println("---- Search Results ----")
  resp match {
    case failure: RequestFailure => println("We failed " + failure.error)
    case results: RequestSuccess[SearchResponse] => {
      val res = results.result.hits.hits.toList
      res.foreach((x) => println(x))
    }
    case results: RequestSuccess[_] => println(results.result)
  }

  resp foreach (search => println(s"There were ${search.totalHits} total hits"))

  client.close()

}