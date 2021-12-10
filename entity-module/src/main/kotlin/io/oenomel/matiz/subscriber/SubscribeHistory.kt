package io.oenomel.matiz.subscriber

import io.oenomel.matiz.article.Article
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
@EntityListeners(value = [AuditingEntityListener::class])
data class SubscribeHistory (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    var subjectSubscriber: SubjectSubscriber,

    @ManyToOne
    var article: Article,

    @CreatedDate
    val createdAt: LocalDateTime
)
