package models.processor
import utils.RequestDTO
import models.forms.MySearchForm
import utils.RequestDTO
import utils.Constants
import utils.NLPApi
case object RequestProcessor {
  def processRequest(query: String): RequestDTO = {
   val inputTuple :(String,String) = NLPApi.userInput(query)
  // val dummyDTO : RequestDTO = RequestDTO(Constants.BRANDED_SEARCH,getDefaultSearchmap)
   val inputMap : scala.collection.mutable.Map[String,String] = getDefaultSearchmap
  if(inputTuple._1==Constants.BRANDED_SEARCH) {
         if(inputTuple._2=="Dubai") {
           inputMap += "destinationLocation" -> "DXB"
           inputMap += "originLocation" -> "LHR"
         } else  if(inputTuple._2=="London") {
           inputMap += "destinationLocation" -> "LHR"
           
         }
  }
    val dummyDTO : RequestDTO = RequestDTO(inputTuple._1,inputMap)
   dummyDTO
  }
  
   def validateParameters(dummyDTO : RequestDTO) {
      dummyDTO.method match {
        case Constants.BRANDED_SEARCH =>
                    println("Branded ");
       case Constants.FLEXI_SEARCH =>
             println("Flexi");
       case _ =>
         println("nt foound");
      }
  }
   def getDefaultSearchmap :scala.collection.mutable.Map[String,String] = {
    
   return scala.collection.mutable.Map("uid" -> "VMEXAELdc2CUbheoiZb6XT8Y",
                       "clientIP" -> "10.14.128.7",
                       "channelCode" -> "MIPD",
                       "moduleCode" -> "IBE",
                       "deviceID" -> "DEVICEIDFORTEST",
                       "originLocation" -> "DXB",
                       "destinationLocation" -> "MEL",
                       "departureOutboundDateTime" -> "15-06-2016",
                        "inBoundCabinClass" -> "Y",
                       "departureInboundDateTime" -> "20-06-2016",
                       "outBoundCabinClass" -> "Y",
                        "adult" -> "1",
                         "child" -> "0",
                          "infant" -> "0",
                          "teenager" -> "0",
                           "searchType"-> "Revenue",
                            "guestLogin"-> "false",
                             "skywardsID"-> "EK998949302",
                              "locale"-> "en_XX",
                               "refresh"-> "false",
                                "originCountry"-> "AE",
                                "searchOrigin"-> "RE",
                                "scala" -> "true"
                       )
   }
}