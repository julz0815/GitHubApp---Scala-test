package core.authentication.controllers.mappings

import commons.controllers.mappings.LoginJsonMappings
import core.authentication.api.{AuthenticatedUser, NewSecurityUser, PlainTextPassword}
import play.api.libs.json._

trait AuthenticationJsonMappings extends LoginJsonMappings {

  implicit val newSecurityUserFormat: Format[NewSecurityUser] = Json.format[NewSecurityUser]

  implicit val planTextPasswordReads: Reads[PlainTextPassword] =
    Reads((Reads.StringReads.reads(_)).andThen(_.map(PlainTextPassword)))
  implicit val planTextPasswordWrites: Writes[PlainTextPassword] =
    Writes((Writes.StringWrites.writes(_)).compose(_.value))

  implicit val jsonFormat: Format[AuthenticatedUser] = Json.format[AuthenticatedUser]
}