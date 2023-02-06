package fr.shield.games.api.infrastructure.web.controllers.players

import fr.shield.games.api.infrastructure.repositories.player.PlayerEntity
import fr.shield.games.api.infrastructure.repositories.player.PlayerRepository
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/players")
class PlayerController(private val playerRepository: PlayerRepository) {

    @PostMapping
    fun create(@RequestBody requestBody: CreatePlayerRequest): PlayerEntity {
        return this.playerRepository.save(PlayerEntity(null, requestBody.username))
    }
}
