package fr.shield.games.api.infrastructure.web.controllers.games

import fr.shield.games.api.core.games.ports.Games
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api/six-takes/games")
class GameController(private val gameService: Games) {

    @GetMapping("/active")
    fun retrieveAllActive(): List<GameResponse> = gameService.retrieveAllActive().map { GameResponse(it) }
}
