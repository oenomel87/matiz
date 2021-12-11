package io.oenomel.matiz.subscriber

import javax.persistence.*

@Entity
@Table
class Subscriber (
    name: String,
    serialNumber: String,
    subjectSubscribers: List<SubjectSubscriber>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var serialNumber: String = serialNumber

    @OneToMany(mappedBy = "subscriber")
    var subjectSubscribers: List<SubjectSubscriber>? = subjectSubscribers
}
