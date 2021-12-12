package io.oenomel.matiz.subscriber

import javax.persistence.*

@Entity
@Table
class Subscriber (
    name: String,
    subscribeKey: String,
    subjectSubscribers: List<SubjectSubscriber>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var subscribeKey: String = subscribeKey

    @OneToMany(mappedBy = "subscriber")
    var subjectSubscribers: List<SubjectSubscriber>? = subjectSubscribers
}
