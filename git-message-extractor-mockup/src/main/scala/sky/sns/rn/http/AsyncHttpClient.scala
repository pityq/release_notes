package sky.sns.rn.http

import org.json4s.JsonAST.{JObject, JValue}
import org.json4s.jackson.Json
import org.json4s.{DefaultFormats, StreamInput}
import sky.sns.rn.AsyncServiceClient
import sky.sns.rn.data.{Author, Commit, Committer, Serializers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaj.http.HttpRequest

object AsyncHttpClient {
  def apply(): AsyncHttpClient = new AsyncHttpClient()
}

class AsyncHttpClient extends AsyncServiceClient[HttpRequest, Seq[Commit]] {
  private implicit val formats = DefaultFormats + Serializers.commitSerializer()
  private val jsonParser = Json(DefaultFormats)

  override def makeRequest(request: HttpRequest) = Future[Seq[Commit]] {
    request.execute(stream => {
      val nodes = jsonParser.parse(StreamInput(stream))
      nodes.children.map(n => extractFields(n))
    }).body
  }

  def extractFields(node: JValue): Commit = node match {
    case jObj: JObject => {
      println(jObj.values("sha"))
      jObj.values("commit") match {
        case commit: Map[String, Any] => println(s"commit " + commit("author"))
      }
      println(jObj.values("committer"))
      println(jObj.values("url"))
      println()
    }
      Commit("sha", "message", Author("name", "email", 1), Committer("name", "email", 2))
  }
}
