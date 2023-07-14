package parser.dto

import sttp.model.Uri

case class ParserDto(
                      url: Uri,
                      query: String,
                      sort: String,
                      resultset: String,
                      dest: String = "-1257786"
                    )
