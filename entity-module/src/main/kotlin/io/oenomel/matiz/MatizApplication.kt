package io.oenomel.matiz

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MatizApplication

fun main(args: Array<String>) {
    runApplication<MatizApplication>(*args)
}