package Test.TransformApi.CreateTransforms

import Test.TransformApi.CreateTransforms.PivotParts.Pivot
import Test.TransformApi.CreateTransforms.SettingsParts.Settings
import Test.TransformApi.CreateTransforms.SourceParts.Source
import Test.TransformApi.CreateTransforms.SyncParts.SyncProperties.Sync


case class Create(id: String,
                  description: Option[String],
                  dest: Dest,
                  frequency: Option[String],
                  pivot: Pivot,
                  settings: Option[Settings],
                  source: Option[Source],
                  sync: Option[Sync]
                 ) {}
