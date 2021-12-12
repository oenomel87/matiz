package io.oenomel.matiz.publisher

import io.oenomel.matiz.article.Article
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
@EntityListeners(value = [AuditingEntityListener::class])
class PublishHistory (
    subjectPublisher: SubjectPublisher,
    article: Article,
    createdAt: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subjectPublisher: SubjectPublisher = subjectPublisher

    @ManyToOne
    var article: Article = article

    @CreatedDate
    var createdAt: LocalDateTime = createdAt
}