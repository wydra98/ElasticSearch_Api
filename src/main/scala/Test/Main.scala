package Test

import com.sksamuel.elastic4s._
import com.sksamuel.elastic4s.fields.{DoubleField, TextField}
import com.sksamuel.elastic4s.http.JavaClient
import com.sksamuel.elastic4s.requests.searches.SearchResponse

import scala.collection.mutable.ArrayBuffer
// we must import the dsl
import com.sksamuel.elastic4s.ElasticDsl._

object Main extends App {

  // in this example we create a client to a local Docker container at localhost:9200
  val client = ElasticClient(JavaClient(ElasticProperties(s"http://${sys.env.getOrElse("ES_HOST", "127.0.0.1")}:${sys.env.getOrElse("ES_PORT", "9200")}")))
  val option = null

  case class Pracownik(name: String, surname: String, earnings: String, pesel: String) {
    override def toString: String = name + " " + surname + " " + earnings + " " + pesel
  }

  client.execute {
    createIndex("pracownicy").mapping(
      properties(
        TextField("name"),
        TextField("surname"),
        DoubleField("earnings"),
        TextField("pesel")
      )
    )
  }

  val pracownik1 = Pracownik("Adrian", "Wydra", "3000", "11111111111")
  val pracownik2 = Pracownik("Rafał", "Gęgotek", "3000", "22222222222")
  val pracownik3 = Pracownik("Wojciech", "Lepich", "3000", "33333333333")
  val pracownik4 = Pracownik("Przemysław", "Lechowicz", "3000", "44444444444")


  //    client.execute {
  //      bulk(
  //        indexInto("pracownicy").fields("name" -> pracownik1.name, "surname" -> pracownik1.surname, "earnings" -> pracownik1.earnings, "pesel" -> pracownik1.pesel),
  //        indexInto("pracownicy").fields("name" -> pracownik2.name, "surname" -> pracownik2.surname, "earnings" -> pracownik2.earnings, "pesel" -> pracownik2.pesel),
  //        indexInto("pracownicy").fields("name" -> pracownik3.name, "surname" -> pracownik3.surname, "earnings" -> pracownik3.earnings, "pesel" -> pracownik3.pesel),
  //        indexInto("pracownicy").fields("name" -> pracownik4.name, "surname" -> pracownik4.surname, "earnings" -> pracownik4.earnings, "pesel" -> pracownik4.pesel)
  //      )
  //    }

  do {
    val listaPracownikow: ArrayBuffer[Pracownik] = takeListOfEmplyee
    takeListOfEmplyee
    println("\nLista pracowników: Co chcesz zrobić?\n")
    listaPracownikow.foreach(println)
    println("\n1. Zatrudnij pracownika.")
    println("2. Zwolnij pracownika.")
    println("3. Aktualizuj info o pracowniku.")
    println("4. Koniec.")
    println()

    val option = scala.io.StdIn.readLine()

    option.toInt match {
      case 1 => {
        println("Podaj imie:")
        val name = scala.io.StdIn.readLine()

        println("Podaj nazwisko:")
        val surname = scala.io.StdIn.readLine()

        println("Podaj zarobki:")
        val earnings = scala.io.StdIn.readLine()

        println("Podaj pesel:")
        val pesel = scala.io.StdIn.readLine()

        val pracownik = new Pracownik(name, surname, earnings, pesel)
        client.execute {
          indexInto("pracownicy").fields("name" -> pracownik.name, "surname" -> pracownik.surname, "earnings" -> pracownik.earnings, "pesel" -> pracownik.pesel)
        }
        Thread.sleep(1000)
      }

      case 2 => {
        println("Podaj pesel:")
        val pesel = scala.io.StdIn.readLine()

        val resp = client.execute {
          deleteByQuery("pracownicy", termQuery("pesel", pesel))
        }

        resp match {
          case failure: RequestFailure => println("We failed " + failure.error)
          case success => println("Usunięto pracownika o id " + pesel)
        }
        Thread.sleep(1000)
      }

      case 3
      => {
        println("Podaj pesel:")
        val pesel = scala.io.StdIn.readLine()

        println("Podaj nowe imie:")
        val name = scala.io.StdIn.readLine()

        println("Podaj nowe nazwisko:")
        val surname = scala.io.StdIn.readLine()

        println("Podaj nowe zarobki:")
        val earnings = scala.io.StdIn.readLine()

        client.execute {
          updateByQuery("pracownicy", termQuery("pesel", pesel)).script(script(s"ctx._source.name = '$name'"))
        }.await
        Thread.sleep(500)
        client.execute {
          updateByQuery("pracownicy", termQuery("pesel", pesel)).script(script(s"ctx._source.surname = '$surname'"))
        }.await
        Thread.sleep(500)
        client.execute {
          updateByQuery("pracownicy", termQuery("pesel", pesel)).script(script(s"ctx._source.earnings = '${earnings}'"))
        }.await
        Thread.sleep(500)
      }

      case 4
      => {
        println("Kończę działanie.")
      }
      case _ => println("Wrong option. Zacznę jeszcze raz!")
    }
  }
  while (option != 4)


  def takeListOfEmplyee /*: ArrayBuffer[Pracownik]*/ = {
    val resp = client.execute {
      search("pracownicy")
    }.await


    resp match {
      case results: RequestSuccess[SearchResponse] => {

        val listaPracownikow = new ArrayBuffer[Pracownik]()

        val details = results.body.get

        val pat = """"hits":\[.*?]""".r
        val source = """"_source":\{.*?}""".r
        val brackets = """\{.*?}""".r
        val namePattern = """(?<="name":").*?(?=")""".r
        val surnamePattern = """(?<="surname":").*?(?=")""".r
        val earningsPattern = """(?<="earnings":).*?(?=,)""".r
        val peselPattern = """(?<="pesel":).*?(?=})""".r


        val hits = pat.findAllIn(details).mkString

        val sources = source.findAllIn(hits).mkString
        val newStr = brackets.findAllIn(sources).mkString

        val finalJson = newStr.replace("}{", "};{")
        val arrayString = finalJson.split(";")

        for (i <- arrayString) {
          val name = namePattern.findAllIn(i).mkString
          val surname = surnamePattern.findAllIn(i).mkString
          val earnings = earningsPattern.findAllIn(i).mkString
          val pesel = peselPattern.findAllIn(i).mkString
          val pracownik = new Pracownik(name, surname, earnings, pesel)
          listaPracownikow.append(pracownik)
        }
        listaPracownikow
      }
    }
  }

  client.close()


}