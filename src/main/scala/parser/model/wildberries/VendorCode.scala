package parser.model.wildberries

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class VendorCode(nm: Int)

case object VendorCode {
  implicit val codecVendorCode: Codec[VendorCode] = deriveCodec[VendorCode]
}

