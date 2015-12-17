package com.github.jacobono.testkit

import play.api.Application
import play.api.ApplicationLoader
import play.api.Configuration
import play.api.Environment

trait SilhouetteMacwire {
  def application: Application

  lazy val environment = Environment.simple(mode = play.api.Mode.Test)
  lazy val configuration = Configuration.load(environment)
  lazy val context = ApplicationLoader.Context(environment, None, new play.core.DefaultWebCommands(), configuration)
}
