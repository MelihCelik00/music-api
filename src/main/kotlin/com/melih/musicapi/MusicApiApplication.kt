package com.melih.musicapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MusicApiApplication

fun main(args: Array<String>) {
	runApplication<MusicApiApplication>(*args)
}
