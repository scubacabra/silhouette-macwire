package com.github.jacobono.controllers

import play.api._
import play.api.i18n.MessagesApi

import com.mohiva.play.silhouette.api.{ Environment, LogoutEvent }
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.SocialProviderRegistry

import com.github.jacobono.forms._
import com.github.jacobono.models.User
import com.github.jacobono.utils.CSRFHelper

import scala.concurrent.Future

/**
 * The basic application controller.
 *
 * @param messagesApi The Play messages API.
 * @param env The Silhouette environment.
 * @param socialProviderRegistry The social provider registry.
 */
class Application(
    val messagesApi: MessagesApi,
    val env: Environment[User, CookieAuthenticator],
    socialProviderRegistry: SocialProviderRegistry,
    csrfHelper: CSRFHelper
) extends CookieAuthentication {

  /**
   * Handles the index action.
   *
   * @return The result to display.
   */
  def index = SecuredAction.async { implicit request =>
    Future.successful(Ok(com.github.jacobono.views.html.home(request.identity)))
  }

  /**
   * Handles the Sign In action.
   *
   * @return The result to display.
   */
  def signIn = UserAwareAction.async { implicit request =>
    request.identity match {
      case Some(user) => Future.successful(Redirect(routes.Application.index()))
      case None => Future.successful(Ok(com.github.jacobono.views.html.signIn(SignInForm.form, socialProviderRegistry, csrfHelper)))
    }
  }

  /**
   * Handles the Sign Up action.
   *
   * @return The result to display.
   */
  def signUp = UserAwareAction.async { implicit request =>
    request.identity match {
      case Some(user) => Future.successful(Redirect(routes.Application.index()))
      case None => Future.successful(Ok(com.github.jacobono.views.html.signUp(SignUpForm.form, csrfHelper)))
    }
  }

  /**
   * Handles the Sign Out action.
   *
   * @return The result to display.
   */
  def signOut = SecuredAction.async { implicit request =>
    val result = Redirect(routes.Application.index())
    env.eventBus.publish(LogoutEvent(request.identity, request, request2Messages))

    env.authenticatorService.discard(request.authenticator, result)
  }
}
