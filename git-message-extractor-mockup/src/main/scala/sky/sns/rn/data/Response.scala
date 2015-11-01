package sky.sns.rn.data

sealed trait Response

abstract class JsonResponse extends Response

case class Author(name: String, email: String, date: Long)

case class Committer(name: String, email: String, date: Long)

case class Commit(sha: String, author: Author, committer: Committer) extends JsonResponse

