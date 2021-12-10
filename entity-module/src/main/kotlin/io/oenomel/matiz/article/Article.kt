package io.oenomel.matiz.article

import io.oenomel.matiz.subject.Subject
import io.oenomel.matiz.subscriber.SubscribeHistory
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table
@EntityListeners(value = [AuditingEntityListener::class])
data class Article (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @CreatedDate
    val createdAt: LocalDateTime,

    @ManyToOne
    var subject: Subject,

    @OneToMany(mappedBy = "article")
    var subscribeHistories: List<SubscribeHistory>? = null
)
