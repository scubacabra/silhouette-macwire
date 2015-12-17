package com.github.jacobono.modules

import com.mohiva.play.silhouette.api.Environment
import com.mohiva.play.silhouette.api.repositories.AuthInfoRepository
import com.mohiva.play.silhouette.api.services.AvatarService
import com.mohiva.play.silhouette.api.util.Clock
import com.mohiva.play.silhouette.api.util.PasswordHasher
import com.mohiva.play.silhouette.impl.authenticators.CookieAuthenticator
import com.mohiva.play.silhouette.impl.providers.{ CredentialsProvider, SocialProviderRegistry }

import com.softwaremill.macwire._

import play.api.Configuration
import play.api.i18n.MessagesApi

import com.github.jacobono.controllers._
import com.github.jacobono.models.User
import com.github.jacobono.models.services.UserService
import com.github.jacobono.utils.CSRFHelper

trait ControllerModule {
  def messagesApi: MessagesApi
  def silhouetteEnvironment: Environment[User, CookieAuthenticator]
  def socialProviderRegistry: SocialProviderRegistry
  def csrfHelper: CSRFHelper
  def userService: UserService
  def authInfoRepository: AuthInfoRepository
  def credentialsProvider: CredentialsProvider
  def configuration: Configuration
  def clock: Clock
  def avatarService: AvatarService
  def passwordHasher: PasswordHasher

  lazy val applicationController: Application = wire[Application]
  lazy val credentialsAuthController: CredentialsAuthController = wire[CredentialsAuthController]
  lazy val signUpController: SignUpController = wire[SignUpController]
  lazy val socialAuthController: SocialAuthController = wire[SocialAuthController]
}
