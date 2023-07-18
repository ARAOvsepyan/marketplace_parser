package parser.model.aliexpress

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class AliProducts(products: List[AliIndex])

case object AliProducts{
  implicit val codecAliProducts: Codec[AliProducts] = deriveCodec[AliProducts]

}
