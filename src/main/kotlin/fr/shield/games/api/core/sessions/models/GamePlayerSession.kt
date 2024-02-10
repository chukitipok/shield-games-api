package fr.shield.games.api.core.sessions.models

import fr.shield.games.api.core.games.models.GameDTO

data class GamePlayerSession(val gameDTO: GameDTO, val sessions: List<PlayerSession>) : Room {
    override fun game(): GameDTO = gameDTO
    override fun playerSessions(): Set<PlayerSession> = sessions.toSet()
}
