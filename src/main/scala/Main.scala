import parser.{AliexpressParser, Config, WildberriesParser}
import parser.dto.{AliRequestDto, StartDto}
import parser.model.aliexpress.{RequestData}

import io.circe.generic.auto._
import io.circe.syntax._

object Main extends App {

//  val data =  StartDto(Config.WB_RES_SET("catalog"), Config.WB_CAT("woman"), Config.WB_SORT("popular"))

//  WildberriesParser.start(data)

  val searchInfo = AliRequestDto(Config.AE_SEARCH_INFO_TITLE("mob_phone"), Config.AE_SEARCH_INFO_SLUG("mob_phone"), Config.AE_SEARCH_INFO_ID("mob_phone").toString)

  val data = RequestData(
    catId = "1",
    g = "???",
    isGoldenItems = "???",
    searchInfo = searchInfo.asJson.toString() ,
    isFavorite = "???"
  )

  println(AliexpressParser.parseCategoriesPage(data))
}