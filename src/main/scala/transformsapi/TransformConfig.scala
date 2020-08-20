package transformsapi

import transformsapi.transformsproperties.{Dest, Pivot, Settings, Source, Sync}

case class TransformConfig(id: String,
                           source: Source,
                           dest: Dest,
                           pivot: Pivot,
                           description: Option[String] = None,
                           frequency: Option[String] = None,
                           settings: Option[Settings] = None,
                           sync: Option[Sync] = None
                          )