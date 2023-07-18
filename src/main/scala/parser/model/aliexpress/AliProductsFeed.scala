package parser.model.aliexpress

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class AliProductsFeed(productsFeed: AliProducts)

case object AliProductsFeed{
  implicit val codecProductsFeed: Codec[AliProductsFeed] = deriveCodec[AliProductsFeed]
}
