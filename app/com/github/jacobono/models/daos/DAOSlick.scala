package com.github.jacobono.models.daos

import com.github.jacobono.database.Tables
import slick.driver.JdbcProfile
import play.api.db.slick.HasDatabaseConfigProvider

/**
 * Trait that contains generic slick db handling code to be mixed in with DAOs
 */
trait DAOSlick extends Tables with HasDatabaseConfigProvider[JdbcProfile]
