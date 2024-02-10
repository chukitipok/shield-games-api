package fr.shield.games.api.core.games.ports

import fr.shield.games.api.core.games.models.GameDTO
import fr.shield.games.api.core.players.models.Player

interface GameWriter {
    fun create(name: String, maxPlayers: Int, owner: Player): GameDTO?
    fun retrieveAllActive(): List<GameDTO>
    fun retrieveById(gameId: String): GameDTO?
    fun update(game: GameDTO): GameDTO
}
