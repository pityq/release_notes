package sky.sns.rn

import sky.sns.rn.http.{AsyncHttpClient, HttpConnections, SyncHttpClient}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}
import scalaj.http.HttpRequest

object Main {
  def main(args: Array[String]) {
    val request: HttpRequest = HttpConnections.get("https://api.github.com/repos/lranasingha/release_notes/git/commits/119684f145868fe0b08410115d4fa6aef234c8ac")
    val syncResponse = SyncHttpClient().makeRequest(request)

    val asyncResponse: Future[Seq[data.Commit]] = AsyncHttpClient().makeRequest(HttpConnections.get("https://api.github.com/repos/lranasingha/release_notes/commits"))
    asyncResponse onComplete {
      case Success(res) => println("success response " + res)
      case Failure(ex) => println("error " + ex.getMessage)
    }

    Await.result(asyncResponse, 5 seconds)
  }
}
