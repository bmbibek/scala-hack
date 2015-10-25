package controllers

//import play.api.mvc.{Action, Controller}
import models.MySearchForm
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

object Application extends Controller {
  def index = Action {
    Ok(views.html.index("Enter your query below "))
  }
  
  val searchForm: Form[MySearchForm] = Form {
  mapping("searchQuery" -> text)(MySearchForm.apply)(MySearchForm.unapply)
  }
  def searchResults = Action { implicit request => 
    val searchData = searchForm.bindFromRequest.get
    println(searchData.searchQuery+" hello")
    Redirect(routes.Application.index())
  }
}