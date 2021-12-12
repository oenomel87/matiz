package io.oenomel.matiz.subscriber

import io.oenomel.matiz.subject.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectSubscriberRepository: JpaRepository<SubjectSubscriber, Long> {
    fun findBySubjectAndSubject_ActiveAndSubscriber(subject: Subject, active: Boolean = true, subscriber: Subscriber): SubjectSubscriber?
}
