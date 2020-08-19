
import RollupsApi.Groups.DateHistogram.DateHistogram
import RollupsApi.Groups.Groups
import RollupsApi.Groups.Terms.Terms
import RollupsApi.Metrics.Metrics
import RollupsApi.RollupConfig


object MainRollup extends App {

  val port = "http://localhost:9200"
  val id = "rollup1"

  val rollupConfig = RollupConfig(
    index_pattern = "kibana_sample_data_*",
    rollup_index = "rollup1_index",
    cron = "*/30 * * * * ?",
    page_size = 1000L,
    groups = Groups(DateHistogram("timestamp", "60m"),
    terms = Some(Terms(List("DistanceKilometers", "AvgTicketPrice")))),
    metrics = Some(List(Metrics("DistanceKilometers", List("min", "max", "sum")),
                        Metrics("AvgTicketPrice", List("avg"))))
  )

  /** 1. Tworzenie rollup-u */
  //RollupApi().putRollUpJob(rollupConfig, id, port)

  /** 2. Startowanie rollup-u */
  //RollupApi().startRollupJob(id, port)

  /** 3. Zatrzymanie rollup-u */
  //RollupApi().stopRollupJob(id, port)

  /** 4. Usunięcie rollup-u */
  //RollupApi().deleteRollupJob(id, port)

  /** 5. Szczegóły rollup-u  */
  //RollupApi().getRollupJobDetails(id, port)

  /** 6. Informacje o możliwościach indexu */
  //RollupApi().getRollupIndexCapabilities("rollup1_index", port)

//  /** 7. Informacje o danych rollup-u */
//  //RollupApi().searchRollupData("rollup1_index", RollupRequestBody().rollupSearch1)

}
