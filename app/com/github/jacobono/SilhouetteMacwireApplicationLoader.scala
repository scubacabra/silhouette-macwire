package com.github.jacobono

import _root_.controllers.Assets
import _root_.controllers.WebJarAssets

import com.softwaremill.macwire._

import org.flywaydb.play.FlywayPlayComponents
import play.api._
import play.api.ApplicationLoader.Context
import play.api.cache.EhCacheComponents
import play.api.db.slick.SlickComponents
import play.api.i18n.I18nComponents
import play.api.libs.openid.OpenIDComponents
import play.api.libs.ws.ning.NingWSComponents
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.filters.csrf.CSRFComponents
import play.filters.headers.SecurityHeadersComponents
import router.Routes

import com.github.jacobono.modules._

class SilhouetteMacwireApplicationLoader extends ApplicationLoader {
  def load(context: Context): Application = {
    Logger.configure(context.environment)
    new SilhouetteMacwireComponents(context).application
  }
}

class SilhouetteMacwireComponents(context: Context) extends BuiltInComponentsFromContext(context)
    with ControllerModule with UtilModule with UserModule with SilhouetteModule
    with DAOModule with DatabaseModule
    with I18nComponents with NingWSComponents with CSRFComponents with SecurityHeadersComponents
    with SlickComponents with EhCacheComponents with OpenIDComponents with FlywayPlayComponents {
  flywayPlayInitializer

  // for the optional Router param in error handler
  // if this is trying to use the Router value from BuiltInComponentsFromContext
  // it results in a circular dependency between the Router and the HttpErrorHandler
  // the application obviously won't start if this happens
  lazy val routerOption = None
  override lazy val httpErrorHandler = errorHandler
  override lazy val httpFilters: Seq[EssentialFilter] = filters.filters
  lazy val webJarAssets: WebJarAssets = wire[WebJarAssets]
  lazy val assets: Assets = wire[Assets]
  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }
}
