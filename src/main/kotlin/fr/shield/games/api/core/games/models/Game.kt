package fr.shield.games.api.core.games.models

import fr.shield.games.api.core.players.models.Player

interface Game {
    fun id(): String?
    fun name(): String
    fun maxPlayers(): Int
    fun players(): List<Player>
    fun isActive(): Boolean
    fun owner(): Player
}
