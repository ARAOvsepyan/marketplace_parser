package parser

import parser.model.aliexpress.{AliData, AliIndex, RequestData}

import sttp.client3._
import sttp.client3.circe._

import io.circe.Error
import io.circe.syntax.EncoderOps


object AliexpressParser {
  def parseCategoriesPage(parserDto: RequestData): List[AliIndex] = {

    val params = Map(
      "catId" -> parserDto.catId,
      "g" -> parserDto.g,
      "source" -> parserDto.source,
      "searchInfo" -> parserDto.searchInfo,
      "isGoldenItems" -> parserDto.isGoldenItems,
      "isFavorite" -> parserDto.isFavorite
    )

    val request = basicRequest
      .body(params.asJson)
      .header("Content-Type", "application/json")
      .post(Config.AE_CAT_URL)
      .response(asJson[AliData])

    val backend: SttpBackend[Identity, Any] = HttpClientSyncBackend()

    val response: Response[Either[ResponseException[String, Error], AliData]] = request.send(backend)

    response.body match {
      //      case Left(value) => println(s"Got response exception:\n$value")
      case Right(value) => value.data.productsFeed.products
    }
  }

//  def getRecommended(): Unit = {
//    ???
//  }
//
//  def getCardInfo(): Unit = {
//    ???
//  }
//
//  def getCardPhoto(): Unit = {
//    ???
//  }
}
