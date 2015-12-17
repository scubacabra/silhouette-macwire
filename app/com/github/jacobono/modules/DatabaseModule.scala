package com.github.jacobono.modules

import play.api.db.slick.{ DatabaseConfigProvider, DbName, SlickApi }
import slick.backend.DatabaseConfig
import slick.profile.BasicProfile

import com.softwaremill.macwire._

trait DatabaseModule {
  def api: SlickApi

  lazy val dbName = "default"
  lazy val dbConfigProvider = wire[SilhouetteMacwireDatabaseConfigProvider]

  class SilhouetteMacwireDatabaseConfigProvider(slickApi: SlickApi, name: String) extends DatabaseConfigProvider {
    def get[P <: BasicProfile]: DatabaseConfig[P] = slickApi.dbConfig[P](DbName(name))
  }
}
