package fr.shield.games.api.core.sessions.services

import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.GameSessionManager
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager

class GameSessionManagerService(private val playerSessionManager: PlayerSessionManager) : GameSessionManager {
    private val games: MutableMap<String, MutableSet<PlayerSession>> = mutableMapOf()

    override fun registerGame(game: Game): Boolean {
        val ownerPlayerSession = playerSessionManager.retrievePlayersSessionByPlayersId(listOf(game.owner().id()))
        game.id()?.let { games.put(it, ownerPlayerSession.toMutableSet()) }
        return true
    }

    override fun unregisterGame(gameId: String): Boolean = games.remove(gameId) != null

    override fun addPlayerToGame(player: PlayerSession, gameId: String): List<PlayerSession> {
        val players = games[gameId] ?: throw fr.shield.games.api.common.exceptions.ResourceNotFoundException()

        return if (!players.contains(player)) {
            players.add(player)
            players.toList()
        }
        else players.toList()
    }

    override fun removePlayerFromGame(playerId: String, gameId: String): List<PlayerSession> {
        val players = games[gameId] ?: throw fr.shield.games.api.common.exceptions.ResourceNotFoundException()
        players.removeIf { playerId == it.player()?.id() }

        return players.toList()
    }
}
