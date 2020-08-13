package Test

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.fields.TextField
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.requests.common.RefreshPolicy
import com.sksamuel.elastic4s.requests.searches.SearchResponse
import com.sksamuel.elastic4s.{ElasticClient, ElasticProperties, RequestFailure, RequestSuccess}

object main1 extends App {

  val client = ElasticClient(JavaClient(ElasticProperties(s"http://${sys.env.getOrElse("ES_HOST", "127.0.0.1")}:${sys.env.getOrElse("ES_PORT", "9200")}")))

  client.execute {
    createIndex("artists").mapping(
      properties(
        TextField("name")
      )
    )
  }


  client.execute {
    bulk(
      indexInto("artists").fields("name" -> "Artysta A", "age" -> "30").id("1").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Artysta B", "age" -> "20").id("2").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Artysta C", "age" -> "40").id("3").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Artysta D", "age" -> "10").id("4").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Artysta E", "age" -> "50").id("5").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Artysta F", "age" -> "60").id("6").refresh(RefreshPolicy.Immediate),
      indexInto("artists").fields("name" -> "Artysta G", "age" -> "70").id("7").refresh(RefreshPolicy.Immediate)
    )
  }

  client.execute{
    bulk (
      deleteById("artists", "6"),
      deleteById("artists", "7"),
      updateById("artists", "3").doc("name" -> "Artysta X")
    )
  }

  val resp = client.execute {
//    search("artists") query "Artysta" sortBy  (
//      fieldSort("age")
//    )
//    search("artists").query("Artysta").sortByFieldAsc("name")
//    search("artists").query("Artysta").sortBy().sortBy(sorts = )
//    search("artists").query(prefixQuery("name", "Artysta A")).limit(40)
    search("artists").query("Artysta A").limit(40)
//    get("artists", "5") // wyszukiwanie pod ID
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


  //  Usuwanie indexu (wszystkich _all)
  //    client.execute {
  //      deleteIndex("_all")
  //    }

  client.close()

}

/**
 * 1. dostep do zapisanych dokumentów nie jest natychmiastowy (ok sekundy na odswiezenie), dlatego wywołanie wyliczenia
 *    (search.totalHits) ilosci elementów z indexu, zliczy dokumenty, które byla na indexie przed wstawieniem,
 *    po mimo że zostało wywołane wczesniej
 */