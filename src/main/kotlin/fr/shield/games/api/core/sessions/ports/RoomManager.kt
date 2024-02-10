package fr.shield.games.api.core.sessions.ports

import fr.shield.games.api.core.games.models.GameDTO
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.models.Room

interface RoomManager {
    fun registerRoom(gameDTO: GameDTO): Boolean
    fun unregisterRoom(roomId: String): Boolean

    fun addPlayerToRoom(roomId: String, session: PlayerSession): Room?
    fun removePlayerFromRoom(roomId: String, playerId: String): Room?

    fun retrieveById(roomId: String): Room?
}
