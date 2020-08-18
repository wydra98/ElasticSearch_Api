package RollupApi

import RollupApi.Groups.Groups
import RollupApi.Metrics.Metrics

case class RollupJobConfig(index_pattern: String,
                           rollup_index: String,
                           cron: String,
                           page_size: Long,
                           groups: Groups,
                           metrics: Option[List[Metrics]] = None){

}
