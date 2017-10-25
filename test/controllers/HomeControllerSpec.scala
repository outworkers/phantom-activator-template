package controllers

import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import org.slf4j.LoggerFactory
import play.api.test.Helpers._
import play.api.test._

/**
 * Run functional tests using WithApplication
 */
class HomeControllerSpec extends WordSpec with GuiceOneAppPerTest with MustMatchers with BeforeAndAfterAll {

  private val logger = LoggerFactory.getLogger("embedded-cassandra")

  override protected def beforeAll(): Unit = {
    EmbeddedCassandra.start(logger)
  }

  override protected def afterAll(): Unit = {
    EmbeddedCassandra.cleanup(logger)
  }

  "Application" should {

    "render the index page" in {
      val result = route(app, FakeRequest(GET, "/")).get
      status(result) must equal(OK)
      contentAsString(result) must include("Spring Bud")
    }
  }

}
