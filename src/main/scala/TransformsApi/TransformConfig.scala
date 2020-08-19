package TransformsApi

import TransformsApi.Dest.Dest
import TransformsApi.Pivot.Pivot
import TransformsApi.Settings.Settings
import TransformsApi.Source.Source
import TransformsApi.Sync.Sync

case class TransformConfig(source: Source,
                           dest: Dest,
                           pivot: Pivot,
                           description: Option[String] = None,
                           frequency: Option[String] = None,
                           settings: Option[Settings] = None,
                           sync: Option[Sync] = None
                          )