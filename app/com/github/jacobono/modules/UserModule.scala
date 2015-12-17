package com.github.jacobono.modules

import com.softwaremill.macwire._
import com.github.jacobono.models.services.UserServiceImpl
import com.github.jacobono.models.daos.UserDAO

trait UserModule {
  def userDAO: UserDAO

  lazy val userService = wire[UserServiceImpl]
}
