package io.oenomel.matiz.subscriber

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SubjectSubscriberRepository: JpaRepository<SubjectSubscriber, Long> {

    fun findSubjectSubscribersBySubscriber_NameAndSubscriber_SerialNumber(subscriberName: String, serialNumber: String): Optional<List<SubjectSubscriber>>
}