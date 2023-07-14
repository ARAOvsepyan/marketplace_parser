import io.circe.generic.semiauto.deriveCodec
import io.circe.syntax._
import io.circe.{Codec, Error, Json}
import parser.{Data, WildberriesParser}
import parser.dto.{CardInfoDto, ParserDto, RecDto}
import sttp.client3._
import sttp.client3.circe._
import sttp.model.Uri

object Main extends App {

 val categoryParseUrl: Uri = uri"https://search.wb.ru/exactmatch/ru/common/v4/search"
 val getRecommendedUrl: Uri = uri"https://waterfall-card-rec.wildberries.ru/api/v1/recommendations"
 val getCardInfoUrl: Uri = uri"https://card.wb.ru/cards/detail"
 val resultset = "catalog"
 val query = "женщинам"
 val sort = "popular"

 val category = ParserDto(categoryParseUrl, query, sort, resultset)

 val product = WildberriesParser.parseCategoriesPage(category)

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
