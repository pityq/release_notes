package sky.sns.rn.http

import net.liftweb.json._
import sky.sns.rn.{Commit, SyncServiceClient}

import scala.io.Source
import scalaj.http.{HttpRequest, HttpResponse}

object SyncHttpClient {
  def apply() = new SyncHttpClient()
}


class SyncHttpClient extends SyncServiceClient[HttpRequest, HttpResponse[List[Commit]]] {

  override def makeRequest(request: HttpRequest): HttpResponse[List[Commit]] = request.execute(inputStream => {
    implicit val formats = DefaultFormats
    val json = parse(Source.fromInputStream(inputStream).mkString)
    (json \ "commit").children.map(value => value.extract[Commit])
  })

}
