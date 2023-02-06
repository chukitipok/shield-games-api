package fr.shield.games.api.infrastructure.app.run

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["fr.shield.games.api"])
@EnableJpaRepositories("fr.shield.games.api.infrastructure.repositories")
@EntityScan("fr.shield.games.api.infrastructure.repositories")
class ShieldGamesApiApplication

fun main(args: Array<String>) { runApplication<ShieldGamesApiApplication>(*args) }
