package com.github.jacobono.testkit

import com.github.jacobono.models.User
import com.mohiva.play.silhouette.api.Environment
import com.mohiva.play.silhouette.api.LoginInfo
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.test.FakeEnvironment
import java.util.UUID

import scala.concurrent.ExecutionContext.Implicits.global

trait Silhouette {
  /**
   * An identity.
   */
  lazy val identity = User(
    userID = UUID.randomUUID(),
    loginInfo = LoginInfo("facebook", "user@facebook.com"),
    firstName = None,
    lastName = None,
    fullName = None,
    email = None,
    avatarURL = None
  )

  /**
   * A Silhouette fake environment.
   */
  implicit lazy val silhouetteEnv: Environment[User, CookieAuthenticator] =
    new FakeEnvironment[User, CookieAuthenticator](Seq(identity.loginInfo -> identity))

}
