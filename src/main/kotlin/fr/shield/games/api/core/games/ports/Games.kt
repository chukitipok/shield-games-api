package fr.shield.games.api.core.games.ports

import fr.shield.games.api.core.games.models.Game

interface Games {
    fun create(roomName: String, maxPlayers: Int, ownerId: String): Game?
}
