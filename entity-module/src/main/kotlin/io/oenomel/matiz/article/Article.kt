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
class Article (
    content: String,
    createdAt: LocalDateTime,
    subject: Subject,
    subscribeHistories: List<SubscribeHistory>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(columnDefinition = "TEXT")
    var content: String = content

    @CreatedDate
    var createdAt: LocalDateTime = createdAt

    @ManyToOne
    var subject: Subject = subject

    @OneToMany(mappedBy = "article")
    var subscribeHistories: List<SubscribeHistory>? = subscribeHistories
}
