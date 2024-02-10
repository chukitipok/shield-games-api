package fr.shield.games.api.core.sessions.models

import fr.shield.games.api.core.players.models.Player

interface PlayerSession {
    fun player(): Player
    fun session(): String
    fun send(message: Any?)
}
