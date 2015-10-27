package sky.sns.rn

import sky.sns.rn.http.{HttpConnections, SyncHttpClient}

import scalaj.http.HttpRequest

object Main {
  def main(args: Array[String]) {
    val request: HttpRequest = HttpConnections.get("https://api.github.com/repos/lranasingha/release_notes/git/commits/119684f145868fe0b08410115d4fa6aef234c8ac")
    val response = SyncHttpClient().makeRequest(request)
    println("git commit -> " + response.body)
  }
}
