package io.oenomel.matiz.subscriber

import io.oenomel.matiz.subject.Subject
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectSubscriberRepository: JpaRepository<SubjectSubscriber, Long> {
    fun findBySubjectAndSubscriber(subject: Subject, subscriber: Subscriber): SubjectSubscriber?
}
