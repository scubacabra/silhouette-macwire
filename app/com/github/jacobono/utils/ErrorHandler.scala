package com.github.jacobono.utils

import com.mohiva.play.silhouette.api.SecuredErrorHandler
import play.api.http.DefaultHttpErrorHandler
import play.api.http.HttpErrorHandler
import play.api.i18n.Messages
import play.api.mvc.Results._
import play.api.mvc.{ Result, RequestHeader }
import play.api.routing.Router
import play.api.Configuration
import play.core.SourceMapper

import scala.concurrent.Future

/**
 * A secured error handler.
 */
class ErrorHandler(
    env: play.api.Environment, config: Configuration,
    sourceMapper: Option[SourceMapper], router: Option[Router]
) extends DefaultHttpErrorHandler(env, config, sourceMapper, router) with SecuredErrorHandler {

  /**
   * Called when a user is not authenticated.
   *
   * As defined by RFC 2616, the status code of the response should be 401 Unauthorized.
   *
   * @param request The request header.
   * @param messages The messages for the current language.
   * @return The result to send to the client.
   */
  override def onNotAuthenticated(request: RequestHeader, messages: Messages): Option[Future[Result]] = {
    Some(Future.successful(Redirect(com.github.jacobono.controllers.routes.Application.signIn())))
  }

  /**
   * Called when a user is authenticated but not authorized.
   *
   * As defined by RFC 2616, the status code of the response should be 403 Forbidden.
   *
   * @param request The request header.
   * @param messages The messages for the current language.
   * @return The result to send to the client.
   */
  override def onNotAuthorized(request: RequestHeader, messages: Messages): Option[Future[Result]] = {
    Some(Future.successful(Redirect(com.github.jacobono.controllers.routes.Application.signIn()).flashing("error" -> Messages("access.denied")(messages))))
  }
}
