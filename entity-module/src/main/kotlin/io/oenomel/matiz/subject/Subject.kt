package io.oenomel.matiz.subject

import io.oenomel.matiz.article.Article
import io.oenomel.matiz.subscriber.SubjectSubscriber
import javax.persistence.*

@Entity
@Table
class Subject(
    name: String,
    active: Boolean,
    articles: List<Article>? = null,
    subjectSubscribers: List<SubjectSubscriber>? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val name: String = name

    val active: Boolean = active

    @OneToMany(mappedBy = "subject")
    var articles: List<Article>? = articles

    @OneToMany(mappedBy = "subject")
    var subjectSubscribers: List<SubjectSubscriber>? = subjectSubscribers
}
