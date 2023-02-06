package fr.shield.games.api.core.sessions.services

import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager

class PlayerSessionManagerService : PlayerSessionManager {
    private val connectedPlayers: MutableSet<PlayerSession> = mutableSetOf()

    override fun retrieveAllConnected(): List<PlayerSession> = connectedPlayers.toList()

    override fun retrievePlayersSessionByPlayersId(playersId: List<String>): List<PlayerSession> =
        connectedPlayers.filter { playersId.contains(it.player()?.id()) }.toList()

    override fun registerPlayer(playerSession: PlayerSession): Boolean = connectedPlayers.add(playerSession)

    override fun unregisterPlayer(playerSession: PlayerSession): Boolean = connectedPlayers.remove(playerSession)
}
