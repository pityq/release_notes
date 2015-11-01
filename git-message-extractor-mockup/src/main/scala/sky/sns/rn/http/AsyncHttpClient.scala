package sky.sns.rn.http

import sky.sns.rn.AsyncServiceClient
import sky.sns.rn.data.Commit

import scala.concurrent.Future
import scalaj.http.{HttpRequest, HttpResponse}

class AsyncHttpClient extends AsyncServiceClient[HttpRequest, HttpResponse[List[Commit]]] {

  override def makeRequest(request: HttpRequest): Future[HttpResponse[List[Commit]]] = ???

}
