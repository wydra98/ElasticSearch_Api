package TransformsApi.Source
import com.sksamuel.exts.OptionImplicits._

case class Source(index: String,
                  var query: Option[String] = None){

  def query(query: String): Source = copy(query = query.some)
}
