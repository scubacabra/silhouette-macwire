package com.github.jacobono.modules

import com.softwaremill.macwire._
import play.api.db.slick.DatabaseConfigProvider

import com.github.jacobono.models.daos._

trait DAOModule {
  def dbConfigProvider: DatabaseConfigProvider

  lazy val userDAO: UserDAO = wire[UserDAOImpl]
  lazy val oath1InfoDAO = wire[OAuth1InfoDAO]
  lazy val oath2InfoDAO = wire[OAuth2InfoDAO]
  lazy val openIDInfoDAO = wire[OpenIDInfoDAO]
  lazy val passwordInfoDAO = wire[PasswordInfoDAO]
}
