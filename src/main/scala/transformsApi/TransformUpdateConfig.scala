package transformsApi

import transformsApi.transformsProperties.{Dest, Settings, Source, Sync}

case class TransformUpdateConfig(source: Source,
                                 dest: Dest,
                                 description: Option[String] = None,
                                 frequency: Option[String] = None,
                                 settings: Option[Settings] = None,
                                 sync: Option[Sync] = None
                                )