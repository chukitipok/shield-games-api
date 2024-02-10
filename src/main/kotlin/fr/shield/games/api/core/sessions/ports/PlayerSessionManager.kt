package fr.shield.games.api.core.sessions.ports

import fr.shield.games.api.core.sessions.models.PlayerSession

interface PlayerSessionManager {
    fun retrieveAllConnected(): List<PlayerSession>
    fun retrievePlayerSessionByPlayerId(playerId: String): PlayerSession?
    fun retrievePlayersSessionByPlayersId(playersId: List<String>): List<PlayerSession>

    fun registerPlayer(playerSession: PlayerSession): Boolean
    fun unregisterPlayer(playerSession: PlayerSession): Boolean
}
