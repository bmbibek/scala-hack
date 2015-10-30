package models.processor
import utils.RequestDTO
import models.forms.MySearchForm
import utils.RequestDTO
import utils.Constants
case object RequestProcessor {
  def processRequest(query: String): RequestDTO = {
    
   val dummyDTO : RequestDTO = RequestDTO(Constants.BRANDED_SEARCH,getDefaultSearchmap)
   
   dummyDTO
  }
  
   def getDefaultSearchmap :Map[String,String] = {
     
     val requestMap = Map("uid" -> "VMEXAELdc2CUbheoiZb6XT8Y",
                       "clientIP" -> "10.14.128.7",
                       "channelCode" -> "MIPD",
                       "moduleCode" -> "IBE",
                       "deviceID" -> "DEVICEIDFORTEST",
                       "originLocation" -> "DXB",
                       "destinationLocation" -> "LHR",
                       "departureOutboundDateTime" -> "15-06-2016",
                        "inBoundCabinClass" -> "",
                       "departureInboundDateTime" -> "",
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
                                "searchOrigin"-> "ON"
                       )
   return requestMap
   }
}