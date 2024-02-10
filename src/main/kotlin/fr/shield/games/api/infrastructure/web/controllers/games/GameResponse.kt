package fr.shield.games.api.infrastructure.web.controllers.games

import fr.shield.games.api.core.games.models.GameDTO

data class PlayerResponse(val id: String, val username: String)

data class GameResponse(
    val id: String,
    val name: String,
    val maxPlayers: Int,
    val players: List<PlayerResponse>,
    val owner: PlayerResponse
) {
    constructor(gameDTO: GameDTO): this(
        gameDTO.id()!!,
        gameDTO.name(),
        gameDTO.maxPlayers(),
        gameDTO.players().map { PlayerResponse(it.id(), it.username()) },
        PlayerResponse(gameDTO.owner().id(), gameDTO.owner().username())
    )
}
