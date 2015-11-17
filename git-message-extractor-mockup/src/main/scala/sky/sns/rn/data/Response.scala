package sky.sns.rn.data

/**
 * <p>
 * Definition of the response as an Algebraic Data Type, usage of sealed traits will allow compiler
 * to do exhaustive tests to prevent runtime errors.
 * </p>
 */
sealed trait Response

abstract class JsonResponse extends Response

case class Commit(sha: String, message: String, author: Author, committer: Committer, url: Option[String] = None)

case class Author(name: String, email: String, date: Long)

case class Committer(name: String, email: String, date: Long)

