package sky.sns.rn

import org.json4s.jackson.Json
import org.json4s.{DefaultFormats, StreamInput}
import sky.sns.rn.data.Commit
import sky.sns.rn.http.{AsyncHttpClient, HttpConnections, SyncHttpClient}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scalaj.http.{HttpRequest, HttpResponse}

object Main {
  def main(args: Array[String]) {
    val request: HttpRequest = HttpConnections.get("https://api.github.com/repos/lranasingha/release_notes/git/commits/119684f145868fe0b08410115d4fa6aef234c8ac")
    val syncResponse = SyncHttpClient().makeRequest(request)

    println("git commits -> " + syncResponse.body)
    Future {
      request.execute(stream => {
        val node = Json(DefaultFormats).parse(new StreamInput(stream))
        node
      })
    } onComplete {
      case Success(res) => println("success response " + res.body)
      case Failure(ex) => println("error " + ex.getMessage)
    }

    val asyncResponse: Future[HttpResponse[Seq[Commit]]] = AsyncHttpClient().makeRequest(HttpConnections.get("https://api.github.com/repos/lranasingha/release_notes/git/commits/"))
    asyncResponse onComplete {
      case Success(res) => println("success response " + res.body)
      case Failure(ex) => println("error " + ex.getMessage)
    }
  }
}
