package io.oenomel.matiz.publisher

import javax.persistence.*

@Entity
@Table
class Publisher (
    name: String,
    publishKey: String,
    subjectPublishers: List<SubjectPublisher>? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var name: String = name

    var publishKey: String = publishKey

    @OneToMany
    var subjectPublishers: List<SubjectPublisher>? = subjectPublishers
}