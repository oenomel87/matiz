package io.oenomel.matiz.publisher

import io.oenomel.matiz.subject.Subject
import javax.persistence.*

@Entity
@Table
class SubjectPublisher (
    subject: Subject,
    publisher: Publisher,
    publishHistories: List<PublishHistory>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    var subject: Subject = subject

    @ManyToOne
    var publisher: Publisher = publisher

    @OneToMany(mappedBy = "subjectPublisher")
    var publishHistories: List<PublishHistory>? = publishHistories
}