package sky.sns.rn.data

import org.json4s.FieldSerializer
import org.json4s.FieldSerializer._

object Serializers {

  case class CommitSerializer[Commit]() {

  }

  def commitSerializer() = FieldSerializer[Commit](
    renameTo("http_url", "url") orElse "",
    renameFrom("url", "http_url")
  )
}
