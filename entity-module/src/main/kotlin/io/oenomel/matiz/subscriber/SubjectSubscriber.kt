package io.oenomel.matiz.subscriber

import io.oenomel.matiz.subject.Subject
import javax.persistence.*

@Entity
@Table
data class SubjectSubscriber (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    var subject: Subject,

    @ManyToOne
    var subscriber: Subscriber,

    @OneToMany(mappedBy = "subjectSubscriber")
    var subscribeHistories: List<SubscribeHistory>? = null
)
