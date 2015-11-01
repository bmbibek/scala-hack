package utils

import org.apache.http.{ HttpEntity, HttpResponse }
import org.apache.http.client.{ ClientProtocolException, HttpClient }
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

import java.net.URLEncoder
import com.google.gson._

object NLPApi {

  def processNLP(query: String) = {

    val queryParsed = URLEncoder.encode(query)

    val requestMap = Map("query" -> queryParsed, "v" -> "20150910", "timezone" -> "UAE/Dubai",
      "lang" -> "en")
    val httpClient = new DefaultHttpClient()
    var url4: String = generateURIUsingParaMeters(Constants.NLPApiUrl, requestMap)
    val obj = new HttpGet(url4)

    obj.setHeader("Authorization", Constants.ApiAuth)
    obj.setHeader("ocp-apim-subscription-key", Constants.subscriptionKey)

    println("Content recieved with status " + url4)
    val httpResponse = httpClient.execute(obj)
    val entity = httpResponse.getEntity()
    println("Content recieved with status " + httpResponse.getStatusLine.getStatusCode)
    println("Content recieved " + httpResponse.getEntity.getContentLength)
    var content = ""
    if (entity != null) {
      val inputStream = entity.getContent()
      content = io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    content
  }

  def generateURIUsingParaMeters(uri: String, map: Map[String, String]): String = {
    var tempString: String = uri + "?";
    var count: Int = 0;
    map.keys.foreach { key =>
      if (count == 0) {
        tempString = tempString.concat(key).concat("=").concat(map(key))

        count = count + 1
      } else {
        tempString = tempString.concat("&").concat(key).concat("=").concat(map(key))

        count = count + 1
      }
    }
    tempString
  }
  
  def userInput(query: String) = {
      val response = processNLP(query)
    println("Response " + response)
    val jelement = new JsonParser().parse(response)
    var jobject = jelement.getAsJsonObject()
    jobject = jobject.getAsJsonObject("result")
    //val jarray = jobject.getAsJsonArray("translations")
    jobject = jobject.getAsJsonObject()
    val result: String = jobject.get("action").toString()
    val action = result.replaceAll("\"", "")
    var inputData:String = null

    if ("check.flights" == action) {
      println("User Requested for Flights Status")
      jobject = jobject.getAsJsonObject("parameters")
      inputData = jobject.get("flight_number").toString()
      println("Flight Number " + inputData)
    } else if ("weather.search" == action) {
      println("User Requested for Weather")
      jobject = jobject.getAsJsonObject("parameters")
      inputData = jobject.get("location").toString()
      println("Location " + inputData)
    }else if ("book" == action) {
      println("User Requested for Flight Booking")
      jobject = jobject.getAsJsonObject("parameters")
      inputData = jobject.get("flightNumber").toString()
      println("flightNumber " + inputData)
    }else if ("flightSearch" == action) {
      println("User Requested for Flight Search")
      jobject = jobject.getAsJsonObject("parameters")
      inputData = jobject.get("location").toString()
      println("location " + inputData)   
    }
      inputData = inputData.replaceAll("\"", "")
    (action,inputData) 
  }

 /* def main(args: Array[String]): Unit = {
    println(userInput("EK512")._2)
    
  }*/
}