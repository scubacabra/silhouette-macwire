package com.github.jacobono.controllers

import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.api.Silhouette
import play.api.mvc.{ RequestHeader, Result }
import scala.concurrent.Future

import com.github.jacobono.models.User

trait CookieAuthentication extends Silhouette[User, CookieAuthenticator] {
  override def onNotAuthenticated(request: RequestHeader): Option[Future[Result]] =
    Some(Future.successful(Redirect(routes.Application.signIn())))
}
