package io.oenomel.matiz.operator

import javax.persistence.*

@Entity
@Table
class Operator (
    username: String,
    password: String? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var username: String = username

    var password: String? = password
}
