package com.github.jacobono.controllers

import play.api.test.WithApplicationLoader
import com.github.jacobono.SilhouetteMacwireApplicationLoader

class IntegrationContext extends WithApplicationLoader(
  applicationLoader = new SilhouetteMacwireApplicationLoader
)
