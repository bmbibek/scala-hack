package dto

case class BrandedSearchDTO (
    
  resHeader: ResHeader,
  milesfareInd: Boolean,
  currencyCode: String,
  percentageTaxInd: Boolean,
  totalPaxCount: Double,
  marketApplicableBrds: MarketApplicableBrds,
  legs: Legs,
  thirdPartyPayment: String,
  lowestFare: Double,
  lowestFareTax: Double,
  surcharge: Double,
  isBrazilOTP: Boolean,
  isUsItinerary: Boolean,
  isCanItinerary: Boolean,
  fareLockAvail: Boolean,
  fareLockAvailableHrs: String
)

case class ResHeader(
  uid: String,
  responseCode: String
)
case class Sfrs(
  cat16: String
)
case class MarketApplicableBrds(
  brandCC: List[String]
)
 class FlightDetails(
  deptAirport: String,
  arrAirport: String,
  airlineCode: String,
  flightNumber: String,
  isConnection: Boolean,
  deptDate: String,
  deptTime: String,
  arrDate: String,
  arrTime: String,
  duration: String,
  cabinclass: String,
  isOutbound: Boolean,
  bookingClass: String,
  fbCode: String,
  stops: Double,
  codeshareTxt: String,
  aircraftTypeCode: String,
  aircraft: String,
  stopOvers: List[StopOvers],
  segmentId: Double,
  airfareRev: String,
  carrierImposedChargesRev: String,
  tax: String,
  airFareMiles: String,
  carrierImposedChargesMiles: String,
  upsell: String,
  deptairportCountry: String,
  arrairportCountry: String
)
case class Flights0020(
  flightDetails: List[FlightDetails]
)
case class Brands0020(
  cabinClass: String,
  brandDetails: String
)
case class OptionDetails(
  totalElapsedTime: String,
  flights0020: Flights0020,
  brands0020: List[Brands0020],
  optionId: Double,
  lowestOptionFare: Double,
  lowestOptionTax: Double,
  isLowestTotalFare: Boolean,
  isAllBrandSoldout: Boolean
)
case class Options(
  optionDetails: List[OptionDetails]
)
case class Leg(
  from: String,
  to: String,
  lowestCabinclass: String,
  options: Options,
  legType: String,
  lowestFareOfTheDay: Double
)
case class Legs(
  leg: List[Leg]
)

case class StopOvers(
  id: String,
  depPoint: String,
  depDate: String,
  depTime: String,
  arrPoint: String,
  arrDate: String,
  arrTime: String,
  groundTime: String,
  flightDuration: String
)
