package fr.shield.games.api.core.sessions.models

interface GamePlayersSession {
    fun gameId(): String
    fun sessions(): List<PlayerSession>
}
