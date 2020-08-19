package TransformsApi

import TransformsApi.Dest.Dest
import TransformsApi.Settings.Settings
import TransformsApi.Source.Source
import TransformsApi.Sync.Sync

case class TransformUpdateConfig(source: Source,
                                 dest: Dest,
                                 description: Option[String] = None,
                                 frequency: Option[String] = None,
                                 settings: Option[Settings] = None,
                                 sync: Option[Sync] = None
                           ) {

}
