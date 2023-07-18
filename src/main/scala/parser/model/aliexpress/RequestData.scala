package parser.model.aliexpress

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import jdk.incubator.vector.ByteVector

import java.util.Base64

case class RequestData(
                        catId: String,
                        g: String,
                        isGoldenItems: String, // Топ-товары ("n"/"y")
                        searchInfo: String = "category:",
                        decodedSearchInfo: String, // eyJ0aXRsZSI6ItCc0L7QsdC40LvRjNC90YvQtSDRgtC10LvQtdGE0L7QvdGLIiwgInNsdWciOiJtb2JpbGUtcGhvbmVzLWFjY2Vzc29yaWVzIiwgImlkIjoiMjQifQ==
                        isFavorite: String, // 4 звезды и более ("n"/"y")
                        source: String = "nav_category"
                      )

case object RequestData{
  implicit val codecRequestData: Codec[RequestData] = deriveCodec[RequestData]
}

