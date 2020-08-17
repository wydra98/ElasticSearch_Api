package Test

case class TransformRequestBody() {

  /** ************ Stworzenie i pobranie indexu PUT *********************
   * ZAPISUJE NIEANULOWANE LOTY, GRUPUJE PO LINIACH LOTNICZYCH I AGREGUJE DO NICH LICZBĘ LOTÓW, MINIMALNA SUMA OPÓŻNIEŃ WSZYSTKICH LOTÓW,
   * MINIMALNA SUMA OPÓŻNIEŃ WSZYSTKICH LOTÓW A NASTĘPNIE WYCIĄGA Z TEGO ŚREDNIĄ PROCENTOWA OPOZNIEN.
   * ***/
  def transformEntity1: String = {
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
    }""""
  }

  // Dla danego kraju wylotu wypisuje średnią cenę biletów, średnie opóźnienie i średni czas lotu.
  def transformEntity2: String = {
    """{
        "source": {
          "index": "kibana_sample_data_flights",
          "query": {
          "bool": {
          "filter": [
        { "term": { "Cancelled": false } }
          ]
        }
        }
        },
        "dest" : {
          "index" : "second_transform"
        },
        "sync" : {
          "time": {
          "field": "timestamp",
          "delay": "60s"
        }
        },
        "pivot": {
          "group_by": {
          "origin_country": { "terms": { "field": "OriginCountry" }}
        },
          "aggregations": {
          "avg_ticket_price": { "avg": { "field": "AvgTicketPrice" }},
          "flight_delay_time": { "avg": { "field": "FlightDelayMin" }},
          "flight_time_hour": { "avg": { "field": "FlightTimeMin"}}
        }
        }
      }""""
  }

  // Dla danego przewoźnika oblicza średni koszt lotu na 1000km i 1000 mil
  def transformEntity3: String = {
    """{
      "source": {
        "index": "kibana_sample_data_flights",
        "query": {
        "bool": {
        "filter": [
      { "term": { "Cancelled": false } }
        ]
      }
      }
      },
      "dest" : {
        "index" : "third_transform"
      },
      "sync" : {
        "time": {
        "field": "timestamp",
        "delay": "60s"
      }
      },
      "pivot": {
        "group_by": {
        "origin_country": { "terms": { "field": "Carrier" }}
      },
        "aggregations": {
        "ticket_price_total": { "sum": { "field": "AvgTicketPrice" }},
        "distance_km_total": { "sum": { "field": "DistanceKilometers" }},
        "distance_miles_total": { "sum": { "field": "DistanceMiles" }},
        "price_for_1000km" :{
        "bucket_script": {
        "buckets_path": {
        "ticket_price": "ticket_price_total.value",
        "distance_km": "distance_km_total.value"
      },
        "script": "(params.ticket_price / params.distance_km) * 1000"
      }
      },
        "price_for_1000Miles" :{
        "bucket_script": {
        "buckets_path": {
        "ticket_price": "ticket_price_total.value",
        "distance_miles": "distance_miles_total.value"
      },
        "script": "(params.ticket_price / params.distance_miles) * 1000"
      }
      }
      }
      }
    }""""
  }

  // 4. Wyświetla miasto i ilość danej pogody występującej nad nim.*/
  def transformEntity4: String = {
    """{
      "source": {
        "index": "kibana_sample_data_flights",
        "query": {
        "bool": {
        "filter": [
      { "term": { "Cancelled": false } }
        ]
      }
      }
      },
      "dest" : {
        "index" : "fourth_transform"
      },
      "sync" : {
        "time": {
        "field": "timestamp",
        "delay": "60s"
      }
      },
      "pivot": {
        "group_by": {
        "origin_country": { "terms": { "field": "Carrier" }}
      },
        "aggregations": {
        "ticket_price_total": { "sum": { "field": "AvgTicketPrice" }},
        "distance_km_total": { "sum": { "field": "DistanceKilometers" }},
        "distance_miles_total": { "sum": { "field": "DistanceMiles" }},
        "price_for_1000km" :{
        "bucket_script": {
        "buckets_path": {
        "ticket_price": "ticket_price_total.value",
        "distance_km": "distance_km_total.value"
      },
        "script": "(params.ticket_price / params.distance_km) * 1000"
      }
      },
        "price_for_1000Miles" :{
        "bucket_script": {
        "buckets_path": {
        "ticket_price": "ticket_price_total.value",
        "distance_miles": "distance_miles_total.value"
      },
        "script": "(params.ticket_price / params.distance_miles) * 1000"
      }
      }
      }
      }
    }""""
  }
}
