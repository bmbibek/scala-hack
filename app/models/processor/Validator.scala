package models.processor

import utils.RequestDTO
import utils.Constants


object Validator {
  def validateParameters(dtoFromNLP: RequestDTO, ip: String, channel: String, sessionID: String) {
    var defaultMap = None: Option[Map[String, String]]
    dtoFromNLP.method match {
      case Constants.BRANDED_SEARCH => 
        
        defaultMap = Some(getDefaultBrandedMap)
        dtoFromNLP.parameters += "uid" -> sessionID
         dtoFromNLP.parameters += "clientIP" -> ip
          dtoFromNLP.parameters += "channelCode" -> channel
            updateRequestMap(defaultMap, dtoFromNLP)
         /* validateParametersForQuery(dtoFromNLP, ip, channel, Constants.BRANDED_SEARCH,
          Constants.BRANDED_SEARCH_INPUT, defaultMap)*/
           
      case Constants.FLEXI_SEARCH =>
        defaultMap = Some(getDefaultBrandedMap)
        dtoFromNLP.parameters += "uid" -> sessionID
         dtoFromNLP.parameters += "clientIP" -> ip
          dtoFromNLP.parameters += "channelCode" -> channel
            updateRequestMap(defaultMap, dtoFromNLP)
      case Constants.FLIGHT_STATUS =>
        defaultMap = Some(getDefaultBrandedMap)
        dtoFromNLP.parameters += "uid" -> sessionID
         dtoFromNLP.parameters += "clientIP" -> ip
          dtoFromNLP.parameters += "channelCode" -> channel
            updateRequestMap(defaultMap, dtoFromNLP)
      case _ =>
        println("not foound");
    }
    
    println("processed map"+ dtoFromNLP.parameters);
  }
  
  private def updateRequestMap (defaultValues: Option[Map[String, String]], dtoFromNLP: RequestDTO) {
     defaultValues.foreach(map => map.keys.foreach { key => 
            if(!dtoFromNLP.parameters.contains(key)) {
              dtoFromNLP.parameters += key -> map(key)
            }
          
          })
  }

  /*private def validateParametersForQuery(dtoFromNLP: RequestDTO, ip: String, channel: String,
                                         operation: String, mandList: String, defaultMap: Option[Map[String, String]]) {

  }*/

  private def getDefaultBrandedMap: Map[String, String] = {
    return Map("uid" -> "VMEXAELdc2CUbheoiZb6XT8Y",
      "clientIP" -> "10.14.128.7",
      "channelCode" -> "MIPD",
      "moduleCode" -> "IBE",
      "deviceID" -> "DEVICEIDFORTEST",
    /*  "originLocation" -> "DXB",
      "destinationLocation" -> "MEL",
      "departureOutboundDateTime" -> "15-06-2016",*/
      "outBoundCabinClass" -> "Y",
      "adult" -> "1",
      "child" -> "0",
      "infant" -> "0",
      "teenager" -> "0",
      "searchType" -> "Revenue",
      "guestLogin" -> "false",
      "skywardsID" -> "EK998949302",
      "locale" -> "en_XX",
      "refresh" -> "false",
      "originCountry" -> "AE",
      "searchOrigin" -> "ON")
  }
  
  private def getFlexiMap: Map[String, String] = {
    return Map("uid" -> "VMEXAELdc2CUbheoiZb6XT8Y",
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
      "searchType" -> "Revenue",
      "guestLogin" -> "false",
      "skywardsID" -> "EK998949302",
      "locale" -> "en_XX",
      "refresh" -> "false",
      "originCountry" -> "AE",
      "searchOrigin" -> "RE")
  }
  
  private def getFlightStatusMap: Map[String, String] = {
    return Map("uid" -> "VMEXAELdc2CUbheoiZb6XT8Y",
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
      "searchType" -> "Revenue",
      "guestLogin" -> "false",
      "skywardsID" -> "EK998949302",
      "locale" -> "en_XX",
      "refresh" -> "false",
      "originCountry" -> "AE",
      "searchOrigin" -> "RE")
  }

}