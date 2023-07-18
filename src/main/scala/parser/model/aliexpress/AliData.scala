package parser.model.aliexpress

import io.circe.Codec
import io.circe.generic.semiauto.deriveCodec

case class AliData(data: AliProductsFeed)

case object AliData {
  implicit val codecAliData: Codec[AliData] = deriveCodec[AliData]
}

