package fr.shield.games.api.core.sessions.ports

import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.sessions.models.PlayerSession

interface GameSessionManager {
    fun registerGame(game: Game): Boolean
    fun unregisterGame(gameId: String): Boolean

    fun addPlayerToGame(player: PlayerSession, gameId: String): List<PlayerSession>
    fun removePlayerFromGame(playerId: String, gameId: String): List<PlayerSession>
}
