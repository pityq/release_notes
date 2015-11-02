package sky.sns.rn.http

import org.json4s.DefaultFormats
import sky.sns.rn.AsyncServiceClient
import sky.sns.rn.data.{Commit, Serializers}

import scala.concurrent.Future
import scalaj.http.{HttpRequest, HttpResponse}

class AsyncHttpClient extends AsyncServiceClient[HttpRequest, HttpResponse[Seq[Commit]]] {
  implicit val formats = DefaultFormats + Serializers.commitSerializer()

  override def makeRequest(request: HttpRequest): Future[HttpResponse[Seq[Commit]]] = {
    Future[HttpResponse[Seq[Commit]]] {
      ???
    }
  }

}
