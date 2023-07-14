package parser

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class Data(data: Products)

case object Data {
  implicit val codecData: Codec[Data] = deriveCodec[Data]
}
