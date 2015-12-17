package com.github.jacobono.utils

import play.api.mvc._
import play.filters.csrf.CSRF
import play.filters.csrf.CSRF.Token
import play.filters.csrf.CSRFConfig
import play.twirl.api.{ Html, HtmlFormat }

/**
 * CSRF helper for Play calls
 */
class CSRFHelper(csrfConfig: CSRFConfig) {

  def token(request: RequestHeader): Token =
    CSRF.getToken(request, csrfConfig) getOrElse sys.error("Missing CSRF Token")

  /**
   * Add the CSRF token as a query String parameter to this reverse router request
   */
  def addTokenAsQueryParameter(call: Call)(implicit request: RequestHeader): Call = {
    new Call(
      call.method,
      call.url + {
        if (call.url.contains("?")) "&" else "?"
      } + csrfConfig.tokenName + "=" + token(request).value
    )
  }

  /**
   * Render a CSRF form field token
   */
  def csrfFormField(implicit request: RequestHeader): Html = {
    // probably not possible for an attacker to XSS with a CSRF token, but just to be on the safe side...
    Html(s"""<input type="hidden" name="${csrfConfig.tokenName}" value="${HtmlFormat.escape(token(request).value)}"/>""")
  }

}
