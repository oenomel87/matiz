package io.oenomel.matiz.article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleRepository: JpaRepository<Article, Long> {

    @Query("select a from Article a join a.subscribeHistories h join h.subjectSubscriber s where s.subscriber.serialNumber = :serialNumber")
    fun findUnreadArticle(serialNumber: String): List<Article>
}