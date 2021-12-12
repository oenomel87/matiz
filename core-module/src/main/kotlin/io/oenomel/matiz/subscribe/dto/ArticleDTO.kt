package io.oenomel.matiz.subscribe.dto

import io.oenomel.matiz.article.Article
import java.time.LocalDateTime

data class ArticleDTO (
    val id: Long? = null,

    var content: String,

    var createdAt: LocalDateTime
)

fun convertToArticleDTO(article: Article): ArticleDTO {
    return ArticleDTO(id = article.id, content = article.content, createdAt = article.createdAt)
}
