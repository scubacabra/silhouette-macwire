package com.github.jacobono.database

trait Models {

  case class DBUser(
    userID: String,
    firstName: Option[String],
    lastName: Option[String],
    fullName: Option[String],
    email: Option[String],
    avatarURL: Option[String]
  )

  case class DBLoginInfo(
    id: Option[Long],
    providerID: String,
    providerKey: String
  )

  case class DBUserLoginInfo(
    userID: String,
    loginInfoId: Long
  )

  case class DBPasswordInfo(
    hasher: String,
    password: String,
    salt: Option[String],
    loginInfoId: Long
  )

  case class DBOAuth1Info(
    id: Option[Long],
    token: String,
    secret: String,
    loginInfoId: Long
  )

  case class DBOpenIDAttribute(
    id: String,
    key: String,
    value: String
  )

  case class DBOpenIDInfo(
    id: String,
    loginInfoId: Long
  )

  case class DBOAuth2Info(
    id: Option[Long],
    accessToken: String,
    tokenType: Option[String],
    expiresIn: Option[Int],
    refreshToken: Option[String],
    loginInfoId: Long
  )

}
