package io.oenomel.matiz.subscribe.dto

import java.time.LocalDateTime

data class ArticleDTO (
    val id: Long,

    var content: String,

    var createdAt: LocalDateTime
)
