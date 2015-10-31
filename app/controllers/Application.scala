package controllers

import models.forms.MySearchForm
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import org.apache.http.{HttpEntity,HttpResponse}
import org.apache.http.client.{ClientProtocolException,HttpClient}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import utils.Constants
import models.forms.MySearchForm
import utils.RequestDTO
import models.processor.RequestProcessor



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
   
    val requestDTO : RequestDTO =  RequestProcessor.processRequest(searchData.searchQuery)
    if (requestDTO.method != null && requestDTO.method.length() >0 
        && requestDTO.parameters.size > 0) {
      var result: String ="";
        if(requestDTO.method.equals(Constants.BRANDED_SEARCH)) {
          println("branded serch")
          result = getRestContent(Constants.BrandedSerchUrl,requestDTO.parameters)
        } else if (requestDTO.method.equals(Constants.FLEXI_SEARCH)) {
          println("fLEXI serch") 
          result = getRestContent(Constants.FlexiSerchUrl,requestDTO.parameters)
        } else if (requestDTO.method.equals(Constants.FLIGHT_STATUS)) {
          println("branded serch")   
          result = getRestContent(Constants.FlightStatus,requestDTO.parameters)
        }
    } else {
      println("Inputs are Wrong")
    }
 
    Redirect(routes.Application.index())
  }
  
  /**
   * Get Rest Content From Middleware 
   */
   def getRestContent(url:String, parameter : Map[String,String]): String = {

    val httpClient = new DefaultHttpClient()
    var url4: String = generateURIUsingParaMeters(Constants.BrandedSerchUrl, parameter)

    val obj = new HttpGet(url4);
    
    obj.setHeader("Authorization", "Basic cmVzdEZsaWdodFdhdGNoOjEyMzQ1Ng==")
    println("Content recieved with status" + url4)
    val httpResponse = httpClient.execute(obj)
    val entity = httpResponse.getEntity()
    println("Content recieved with status" + httpResponse.getStatusLine.getStatusCode)
    println("Content recieved" + httpResponse.getEntity.getContentLength)
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent()
      content = io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
 
    
    httpClient.getConnectionManager().shutdown()

    return content
  }
   
   
   
   /**
    * 
    */
   def generateURIUsingParaMeters(uri: String ,map:Map[String,String]): String = {
     var tempString: String = uri+"?"; 
     var count:Int = 0;
       map.keys.foreach { key=>
             if(count == 0) {
            tempString=   tempString.concat(key).concat("=").concat(map(key))
              
               count=count+1
             } else {
        tempString= tempString.concat("&").concat(key).concat("=").concat(map(key))
               
                 count=count+1
             }
       }
       return tempString
   }
  
}