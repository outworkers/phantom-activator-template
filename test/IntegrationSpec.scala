package controllers

import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.WithBrowser

/**
 * An integration test will fire up a whole play application in a real (or headless) browser
 */
@RunWith(classOf[JUnitRunner])
class IntegrationSpec extends Specification {

  "Application" should {

    "work from within a browser" in new WithBrowser {

      browser.goTo("http://localhost:" + port)

      browser.pageSource must contain("Your new application is ready.")
    }
  }
}
