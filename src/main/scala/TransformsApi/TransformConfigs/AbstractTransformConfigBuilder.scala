package TransformsApi.TransformConfigs

import TransformsApi.Dest.Dest
import TransformsApi.Pivot.Pivot
import TransformsApi.Settings.Settings
import TransformsApi.Source.Source
import TransformsApi.Sync.Sync

abstract class AbstractTransformConfigBuilder {
  var id: String
  var source: Source
  var dest: Dest
  var pivot: Pivot
  var description: Option[String]
  var frequency: Option[String]
  var settings: Option[Settings]
  var sync: Option[Sync]

  def withId(id: String): AbstractTransformConfigBuilder
  def withSource(source: Source): AbstractTransformConfigBuilder
  def withDest(dest: Dest): AbstractTransformConfigBuilder
  def withPivot(pivot: Pivot): AbstractTransformConfigBuilder
  def withDescription(id: Option[String]): AbstractTransformConfigBuilder
  def withFrequency(id: Option[String]): AbstractTransformConfigBuilder
  def withSettings(id: Option[Settings]): AbstractTransformConfigBuilder
  def withSync(id: Option[Sync]): AbstractTransformConfigBuilder
  def build: TransformConfig
}
