
import rollupsApi.RollupApi
import rollupsApi.rollupsProperties._


object MainRollup extends App {

  val port = "http://localhost:9200"
  val id = "rollup1"

  val rollupConfig = rollupsApi.RollupConfig(
    index_pattern = "kibana_sample_data_*",
    rollup_index = "rollup1_index",

    cron = "",// Cron().parseFromData(seconds = 2),
    page_size = 1000L,
    groups = Groups(date_histogram = Some(DateHistogram("timestamp", "60m")),
                    terms = Some(Terms(List("DistanceKilometers", "AvgTicketPrice")))),
    metrics = Some(List(Metrics("DistanceKilometers", List("min", "max", "sum")),
                        Metrics("AvgTicketPrice", List("avg"))))
  )

  /** 1. Tworzenie rollup-u */
//  RollupApi().putRollUpJob(rollupConfig, id, port)

  /** 2. Startowanie rollup-u */
  //RollupApi().startRollupJob(id, port)

  /** 3. Zatrzymanie rollup-u */
  //RollupApi().stopRollupJob(id, port)

  /** 4. Usunięcie rollup-u */
//  RollupApi().deleteRollupJob(id, port)
//  Elasticsearch().deleteIndex("rollup1_index")

  /** 5. Szczegóły rollup-u  */
  //RollupApi().getRollupJobDetails(id, port)

  /** 6. Informacje o możliwościach indexu */
  //RollupApi().getRollupIndexCapabilities("rollup1_index", port)

  /** 7. Informacje o danych rollup-u */
//  //RollupApi().searchRollupData("rollup1_index", RollupRequestBody().rollupSearch1)

  val temp = RollupApi
  val temp1 = Elasticsearch

}
