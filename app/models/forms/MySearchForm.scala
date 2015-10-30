package models.forms
import play.api.libs.json.Json

case class MySearchForm (searchQuery: String)
  
object MySearchForm {
  implicit val searchFormat = Json.format[MySearchForm];
}