package com.github.jacobono.controllers

import com.mohiva.play.silhouette.api.Environment
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import java.util.UUID
import play.api.test.{ FakeRequest, PlaySpecification }
import com.github.jacobono.models.User
import play.api.libs.concurrent.Execution.Implicits._

class ApplicationSpec extends PlaySpecification {
  "the index action" should {
    "redirect to login page if user is unauthorized" in new IntegrationContext {
      val Some(redirectResult) = route(FakeRequest(GET, "/"))

      status(redirectResult) must be equalTo SEE_OTHER

      val redirectURL = redirectLocation(redirectResult).getOrElse("")
      redirectURL must contain(com.github.jacobono.controllers.routes.Application.signIn().toString())

      val Some(unauthorizedResult) = route(FakeRequest(GET, redirectURL))

      status(unauthorizedResult) must be equalTo OK
      contentType(unauthorizedResult) must beSome("text/html")
      contentAsString(unauthorizedResult) must contain("Silhouette - Sign In")
    }

    // "return 200 if user is authorized" in new IntegrationContext {
    //   val Some(result) = route(
    //     FakeRequest(GET, "/")
    //       .withAuthenticator[CookieAuthenticator](identity.loginInfo)
    //   )

    //   status(result) must beEqualTo(OK)
    // }
  }

  // /**
  //   * An identity.
  //   */
  // val identity = User(
  //   userID = UUID.randomUUID(),
  //   loginInfo = LoginInfo("facebook", "user@facebook.com"),
  //   firstName = None,
  //   lastName = None,
  //   fullName = None,
  //   email = None,
  //   avatarURL = None
  // )

}
