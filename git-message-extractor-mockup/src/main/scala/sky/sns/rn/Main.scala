package sky.sns.rn

import sky.sns.rn.http.{HttpConnections, SyncHttpClient}

import scalaj.http.HttpRequest

object Main {
  def main(args: Array[String]) {
    val request: HttpRequest = HttpConnections.get("https://api.github.com/repos/lranasingha/release_notes/git/commits/")
    val response = SyncHttpClient().makeRequest(request)
    println("git commit GET respone " + response)
  }
}
