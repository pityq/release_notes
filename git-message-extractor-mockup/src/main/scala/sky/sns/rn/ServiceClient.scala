package sky.sns.rn

import scala.concurrent.Future

trait SyncServiceClient[RQ, RS] {

  def makeRequest(request: RQ): RS
}

trait AsyncServiceClient[RQ, RS] {
  def makeRequest(request: RQ): Future[RS]
}

