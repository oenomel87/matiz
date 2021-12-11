package io.oenomel.matiz.subscriber

import io.oenomel.matiz.subject.Subject
import javax.persistence.*

@Entity
@Table
class SubjectSubscriber(
    subject: Subject,
    subscriber: Subscriber,
    subscribeHistories: List<SubscribeHistory>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subject: Subject = subject

    @ManyToOne
    var subscriber: Subscriber = subscriber

    @OneToMany(mappedBy = "subjectSubscriber")
    var subscribeHistories: List<SubscribeHistory>? = subscribeHistories
}
