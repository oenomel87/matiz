package io.oenomel.matiz.subscriber

import io.oenomel.matiz.article.Article
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
@EntityListeners(value = [AuditingEntityListener::class])
class SubscribeHistory (
    subjectSubscriber: SubjectSubscriber,
    article: Article,
    createdAt: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subjectSubscriber: SubjectSubscriber = subjectSubscriber

    @ManyToOne
    var article: Article = article

    @CreatedDate
    var createdAt: LocalDateTime = createdAt
}
