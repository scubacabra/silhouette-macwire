package com.github.jacobono.modules

import play.api.{ Configuration, Environment }
import play.api.http.HttpErrorHandler
import play.api.routing.Router
import play.core.SourceMapper
import play.filters.csrf.{ CSRFConfig, CSRFFilter }
import play.filters.headers.SecurityHeadersFilter

import com.softwaremill.macwire._

import com.github.jacobono.utils._

trait UtilModule {
  def environment: Environment
  def configuration: Configuration
  def sourceMapper: Option[SourceMapper]
  def routerOption: Option[Router]
  def csrfFilter: CSRFFilter
  def csrfConfig: CSRFConfig
  def securityHeadersFilter: SecurityHeadersFilter

  lazy val filters = wire[Filters]
  lazy val csrfHelper = wire[CSRFHelper]

  lazy val errorHandler: HttpErrorHandler = wire[ErrorHandler]
}
