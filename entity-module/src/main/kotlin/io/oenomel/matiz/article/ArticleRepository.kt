package io.oenomel.matiz.article

import io.oenomel.matiz.subscriber.Subscriber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleRepository: JpaRepository<Article, Long> {

    @Query("select a from Article a join a.subject s join s.subjectSubscribers sub left join a.subscribeHistories h where sub.subscriber = :subscriber and h.id is null")
    fun findUnreadArticle(subscriber: Subscriber): List<Article>

    fun findArticleById(id: Long?): Article?
}
