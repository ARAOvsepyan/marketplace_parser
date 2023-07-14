package parser

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Recommendations(data: List[Recommendations])

case object Recommendations {
  implicit val codecRecommendations: Codec[Recommendations] = deriveCodec[Recommendations]
}
