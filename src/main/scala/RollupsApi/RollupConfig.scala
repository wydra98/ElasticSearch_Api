package RollupsApi

import RollupsApi.Groups.Groups
import RollupsApi.Metrics.Metrics

case class RollupConfig(index_pattern: String,
                        rollup_index: String,
                        cron: String,
                        page_size: Long,
                        groups: Groups,
                        metrics: Option[List[Metrics]] = None)
