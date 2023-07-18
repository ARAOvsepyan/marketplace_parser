package parser

import sttp.client3._
import sttp.client3.circe._
import io.circe.Error
import parser.dto.{CardInfoDto, CardPhotoDto, CatDto, RecDto, StartDto}
import parser.model.wildberries.{Data, Item, Products, VendorCode}
import sttp.model.Uri

import java.net.URL
import java.nio.file.{Files, Paths}

object WildberriesParser {
  def getCategoriesPage(parserDto: CatDto):Products = {
    val params = Map(
      "resultset" -> parserDto.resultset,
      "query" -> parserDto.query,
      "sort" -> parserDto.sort, 
      "dest" -> parserDto.dest
    )

    val request = basicRequest
      .get(parserDto.url.withParams(params))
      .response(asJson[Data])

    val backend: SttpBackend[Identity, Any] = HttpClientSyncBackend()

    val response: Response[Either[ResponseException[String, Error], Data]] = request.send(backend)

    response.body match {
//      case Left(value) => println(s"Got response exception:\n$value")
      case Right(value) => value.data
    }
  }

  def getRecommended(recDto: RecDto): List[VendorCode] = {
    val params = Map("nm" -> recDto.nm.toString)

    val request = basicRequest
      .get(recDto.url.withParams(params))
      .response(asJson[List[VendorCode]])

    val backend: SttpBackend[Identity, Any] = HttpClientSyncBackend()

    val response: Response[Either[ResponseException[String, Error], List[VendorCode]]] = request.send(backend)

    response.body match {
//      case Left(value) => println(s"Got response exception:\n$value")
      case Right(value) => value
    }
  }

  def getCardInfo(cardInfoDto: CardInfoDto): List[Item] = {
    val params = Map("nm" -> cardInfoDto.nm.toString)

    val request = basicRequest
      .get(cardInfoDto.url.withParams(params))
      .response(asJson[Data])

    val backend: SttpBackend[Identity, Any] = HttpClientSyncBackend()

    val response: Response[Either[ResponseException[String, Error], Data]] = request.send(backend)

    response.body match {
      //      case Left(value) => println(s"Got response exception:\n$value")
      case Right(value) => value.data.products
    }
  }

  def getCardPhoto(cardPhotoDto: CardPhotoDto): Long = {
    val photo_url = new URL(
      s"https://basket-03.wb.ru/vol${cardPhotoDto.nm.toString.take(3)}/" +
      s"part${cardPhotoDto.nm.toString.take(5)}/${cardPhotoDto.nm}/images/big/1.jpg"
    )
    
    val destination = Paths
      .get(s"${Paths.get("")
        .toAbsolutePath.toString}/src/main/photo/${cardPhotoDto.name}_${cardPhotoDto.nm}.jpg"
      )

    println(photo_url)

    Files.copy(photo_url.openStream(), destination)
  }

  def start(startDto: StartDto): Unit = {

    val categoryParseUrl: Uri = uri"https://search.wb.ru/exactmatch/ru/common/v4/search"
    val getRecommendedUrl: Uri = uri"https://waterfall-card-rec.wildberries.ru/api/v1/recommendations"
    val getCardInfoUrl: Uri = uri"https://card.wb.ru/cards/detail"

    val category = CatDto(categoryParseUrl, startDto.query, startDto.sort, startDto.resultset)
    val product = WildberriesParser.getCategoriesPage(category)

    product.products.foreach { x =>
      x.productIterator.foreach { value =>
        println(s"${x.productElementName(x.productIterator.indexOf(value))} : $value")
      }

      println()

      val rec = RecDto(getRecommendedUrl, x.id)

      WildberriesParser.getRecommended(rec).foreach( x => {
        val card = CardInfoDto(getCardInfoUrl, x.nm)

        WildberriesParser.getCardInfo(card).map( x => {
          x.productIterator.foreach { value =>
            println(s"${x.productElementName(x.productIterator.indexOf(value))} : $value")
          }
          println()
        })
      })
    }
  }
}
