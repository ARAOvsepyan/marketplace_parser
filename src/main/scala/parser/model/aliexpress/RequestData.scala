package parser.model.aliexpress

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec
import jdk.incubator.vector.ByteVector

import java.util.Base64

case class RequestData private (
                        catId: String,
                        g: String,
                        isGoldenItems: String, // Топ-товары ("n"/"y")
                        searchInfo: String,
                        isFavorite: String, // 4 звезды и более ("n"/"y")
                        source: String = "nav_category"
                      )

case object RequestData{
   def apply(
              catId: String,
              g: String,
              isGoldenItems: String,
              searchInfo: String,
              isFavorite: String,
  ): RequestData = {
     val text = searchInfo
       .replaceAll("\\s+:\\s+", ":")
       .replaceAll("\\n+", "")
       .replaceAll(",\\s+",", ")
       .replaceAll("\\{\\s+","{")

     val encodedSearchInfo = "category:" + Base64.getEncoder.encodeToString(text.getBytes("UTF-8"))

     new RequestData(
      catId=catId,
      g=g,
      isGoldenItems=isGoldenItems,
      searchInfo=encodedSearchInfo,
      isFavorite=isFavorite
    )
  }

  implicit val codecRequestData: Codec[RequestData] = deriveCodec[RequestData]
}


