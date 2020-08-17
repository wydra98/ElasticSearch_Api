package Test

case class RollupRequestBody() {

  // Utworzenie rollup działajacego co 60 minut i przechowującego metryki min, max, sum dla dystansu oraz avg dla Sredniej oceny biletu
  def rollupEntity1: String = {
    """ {
      "index_pattern": "kibana_sample_data_*",
      "rollup_index": "sensor_rollup",
      "cron": "*/30 * * * * ?",
      "page_size": 1000,
      "groups": {
        "date_histogram": {
        "field": "timestamp",
        "fixed_interval": "60m"
      },
        "terms": {
        "fields": [
        "DistanceKilometers",
        "AvgTicketPrice"
        ]
      }
      },
      "metrics": [
      {
        "field": "DistanceKilometers",
        "metrics": [
        "min","max","sum"
        ]
      },
      {
        "field": "AvgTicketPrice",
        "metrics": [
        "avg"
        ]
      }
      ]
    }"""
  }

  def rollupSearch1: String = {
    """{
      "size": 0,
      "aggregations": {
        "max_DistanceKilometers": {
          "max": {
            "field": "DistanceKilometers"
          }
        }
      }
    }"""
  }
}
