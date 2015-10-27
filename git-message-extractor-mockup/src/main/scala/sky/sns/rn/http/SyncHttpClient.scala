package sky.sns.rn.http

import sky.sns.rn.SyncServiceClient

import scalaj.http.{HttpRequest, HttpResponse}

object SyncHttpClient {
  def apply() = new SyncHttpClient()
}

class SyncHttpClient extends SyncServiceClient[HttpRequest, HttpResponse[String]] {

  override def makeRequest(request: HttpRequest): HttpResponse[String] = request.asString
}
