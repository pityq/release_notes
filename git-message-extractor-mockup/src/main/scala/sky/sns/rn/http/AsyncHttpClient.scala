package sky.sns.rn.http

import org.json4s.jackson.Json
import org.json4s.{DefaultFormats, JValue, StreamInput}
import sky.sns.rn.AsyncServiceClient
import sky.sns.rn.data.{Commit, Serializers}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scalaj.http.{HttpRequest, HttpResponse}

object AsyncHttpClient {
  def apply(): AsyncHttpClient = new AsyncHttpClient()
}

class AsyncHttpClient extends AsyncServiceClient[HttpRequest, HttpResponse[Seq[Commit]]] {
  private implicit val formats = DefaultFormats + Serializers.commitSerializer()
  private val jsonParser = Json(DefaultFormats)

  override def makeRequest(request: HttpRequest): Future[HttpResponse[Seq[Commit]]] = {
    Future[HttpResponse[Seq[Commit]]] {
      request.execute(stream => {
        val nodes = jsonParser.parse(StreamInput(stream))
        Seq.empty[Commit]
      })
    }
  }

}
