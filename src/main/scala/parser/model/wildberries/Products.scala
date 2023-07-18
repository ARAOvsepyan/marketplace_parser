package parser.model.wildberries

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Products(products: List[Item])

case object Products {
  implicit val codecProduct: Codec[Products]   = deriveCodec[Products]
}

