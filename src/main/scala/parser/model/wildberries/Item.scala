package parser.model.wildberries

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Item(id: Int, name: String, brand: String)

case object Item {
  implicit val codecItem: Codec[Item] = deriveCodec[Item]
}