package io.oenomel.matiz.subscriber

import javax.persistence.*

@Entity
@Table
data class Subscriber (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val serialNumber: String,

    @OneToMany(mappedBy = "subscriber")
    var subjectSubscribers: List<SubjectSubscriber>? = null
)
