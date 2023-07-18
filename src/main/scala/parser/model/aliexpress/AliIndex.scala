package parser.model.aliexpress

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class AliIndex(id: String, imgSrc: String, productTitle: String)

case object AliIndex {
  implicit val codecAliIndex: Codec[AliIndex] = deriveCodec[AliIndex]
}
