package parser

import sttp.client3.UriContext
import sttp.model.Uri

object Config {
  // Wildberries configs
  val WB_CAT_URL: Uri = uri"https://search.wb.ru/exactmatch/ru/common/v4/search"
  val WB_REC_URL: Uri = uri"https://waterfall-card-rec.wildberries.ru/api/v1/recommendations"
  val WB_CARD_INFO_URL: Uri = uri"https://card.wb.ru/cards/detail"

  val WB_CAT: Map[String, String] = Map(
    "woman" -> "женщинам",
    "man" -> "мужчинам",
  )

  val WB_SORT: Map[String, String] = Map(
    "catalog" -> "catalog",
  )

  val WB_RES_SET: Map[String, String] = Map(
    "popular" -> "popular",
  )

  // AliExpress configs
  val AE_CAT_URL: Uri = uri"https://aliexpress.ru/aer-jsonapi/v1/category_filter?_bx-v=2.2.3"

  val AE_SEARCH_INFO_TITLE: Map[String, String] = Map(
    "mob_phone" -> "Мобильные телефоны",
  )
  val AE_SEARCH_INFO_SLUG: Map[String, String] = Map(
    "mob_phone" -> "mobile-phones-accessories"
  )
  val AE_SEARCH_INFO_ID: Map[String, Int] = Map(
    "mob_phone" -> 24
  )


}
