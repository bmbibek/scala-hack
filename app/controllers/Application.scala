
package controllers

import java.util.ArrayList

import org.apache.http.{HttpEntity,HttpResponse}
import org.apache.http.client.{ClientProtocolException,HttpClient}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

import models.forms.MySearchForm
import models.forms.MySearchForm
import models.processor.RequestProcessor
import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc._
import utils.Constants
import utils.RequestDTO
import utils.ScalaDTO






object Application extends Controller {
  def index = Action {
    // var outBoundList : java.util.List[ScalaDTO] = new java.util.ArrayList()
   Ok(views.html.flights("Done"))
  }
  
  val searchForm: Form[MySearchForm] = Form {
  mapping("searchQuery" -> text)(MySearchForm.apply)(MySearchForm.unapply)
  }
  def searchResults = Action { implicit request => 
    val searchData = searchForm.bindFromRequest.get
    println(searchData.searchQuery+" hello")
    var result: String ="";
    val requestDTO : RequestDTO =  RequestProcessor.processRequest(searchData.searchQuery)
    if (requestDTO.method != null && requestDTO.method.length() >0 
        && requestDTO.parameters.size > 0) {
     
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
        val json = Json.parse(result) 
   
   val json2 = json.as[Seq[String]]
    var outBoundList : java.util.List[ScalaDTO] = new java.util.ArrayList()
     var inBoundList : java.util.List[ScalaDTO] = new java.util.ArrayList()
   
    json2.foreach { node => 
      if(node.startsWith("OT")) {
      val outFlt : Array[String] = node.split("~")
       var scalaDto : ScalaDTO = new ScalaDTO (
           outFlt(1),
           outFlt(2),
           outFlt(3),
           outFlt(4),
           "",
           outFlt(5),
           outFlt(6),
           "Economy",
          outFlt(7) )
      outBoundList.add(scalaDto)
      } else if(node.startsWith("IN")) {
        val outFlt : Array[String] = node.split("~")
      }
       } 
  Ok(views.html.flights("test"))
  }
  
  /**
   * Get Rest Content From Middleware 
   */
   def getRestContent(url:String, parameter :  scala.collection.mutable.Map[String,String]): String = {

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

     
    

    /*println("Sequence of String  "+json2)
    val jelement = new JsonParser().parse(content)
     var  jarray = jelement.getAsJsonObject()
     println(jarray)*/
     
    httpClient.getConnectionManager().shutdown()

    return content
  }
   
   
   
   /**
    * 
    */
   def generateURIUsingParaMeters(uri: String ,map: scala.collection.mutable.Map[String,String]): String = {
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