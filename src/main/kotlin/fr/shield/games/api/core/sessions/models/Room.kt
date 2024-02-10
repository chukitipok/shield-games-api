package fr.shield.games.api.core.sessions.models

import fr.shield.games.api.core.games.models.GameDTO

interface Room {
    fun game(): GameDTO
    fun playerSessions(): Set<PlayerSession>
}
