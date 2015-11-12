package sky.sns.rn

import org.json4s.jackson.Json
import org.json4s.{DefaultFormats, StreamInput}
import sky.sns.rn.http.{AsyncHttpClient, HttpConnections, SyncHttpClient}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}
import scalaj.http.{HttpRequest, HttpResponse}

object Main {
  def main(args: Array[String]) {
    val request: HttpRequest = HttpConnections.get("https://api.github.com/repos/pityq/release_notes/commits")
    val response = SyncHttpClient().makeRequest(request)
    println("git commit messages -> " + response.body)
    Future {
      request.execute(stream => {
        val node = Json(DefaultFormats).parse(new StreamInput(stream))
        node
      })
    } onComplete {
      case Success(res) => println("success response " + res.body)
      case Failure(ex) => println("error " + ex.getMessage)
    }

    val asyncResponse: Future[HttpResponse[Seq[data.Commit]]] = AsyncHttpClient().makeRequest(HttpConnections.get("https://api.github.com/repos/pityq/release_notes/commits"))
    asyncResponse onComplete {
      case Success(res) => println("success response " + res.body)
      case Failure(ex) => println("error " + ex.getMessage)
    }
  }
}
