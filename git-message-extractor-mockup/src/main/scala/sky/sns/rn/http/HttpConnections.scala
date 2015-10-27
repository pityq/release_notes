package sky.sns.rn.http

import scalaj.http.{Http, HttpRequest}

object HttpConnections {

  def get(url: String, headers: Seq[(String, String)] = Seq.empty, params: Seq[(String, String)] = Seq.empty): HttpRequest =
    Http(url).method("GET").headers(headers).params(params)

  def post(url: String, headers: Seq[(String, String)] = Seq.empty, body: Seq[(String, String)]): HttpRequest =
    Http(url).method("POST").headers(headers).postForm(body)
}
