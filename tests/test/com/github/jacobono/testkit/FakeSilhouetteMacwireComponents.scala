package com.github.jacobono.testkit

import controllers.{ Assets, WebJarAssets }

import play.api.BuiltInComponentsFromContext
import play.api.ApplicationLoader.Context
import play.api.cache.EhCacheComponents
import play.api.db.slick.SlickComponents
import play.api.i18n.I18nComponents
import play.api.libs.openid.OpenIDComponents
import play.api.routing.Router
import play.filters.csrf.CSRFComponents
import play.filters.headers.SecurityHeadersComponents
import router.Routes

import com.softwaremill.macwire._
import com.github.jacobono.modules._

class FakeSilhouetteMacwireComponents(context: Context) extends BuiltInComponentsFromContext(context)
    with ControllerModule with UtilModule with UserModule with SilhouetteModule with DAOModule with DatabaseModule
    with MockWsClient with I18nComponents with CSRFComponents with SecurityHeadersComponents
    with SlickComponents with EhCacheComponents with OpenIDComponents {
  lazy val routerOption = None
  override lazy val httpErrorHandler: play.api.http.HttpErrorHandler = errorHandler
  override lazy val httpFilters = filters.filters
  lazy val webJarAssets: WebJarAssets = wire[WebJarAssets]
  lazy val assets: Assets = wire[Assets]
  lazy val router: Router = {
    lazy val prefix = "/"
    wire[Routes]
  }
}
