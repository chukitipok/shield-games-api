package fr.shield.games.api.core.sessions.services

import fr.shield.games.api.core.games.models.GameDTO
import fr.shield.games.api.core.games.ports.Games
import fr.shield.games.api.core.sessions.models.GamePlayerSession
import fr.shield.games.api.core.sessions.models.PlayerSession
import fr.shield.games.api.core.sessions.models.Room
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager
import fr.shield.games.api.core.sessions.ports.RoomManager

class RoomManagerService(private val playerSessionManager: PlayerSessionManager, private val gameService: Games) : RoomManager {
    private val rooms: MutableMap<String, Room> = mutableMapOf()

    override fun registerRoom(gameDTO: GameDTO): Boolean {
        val ownerPlayerSession = playerSessionManager.retrievePlayerSessionByPlayerId(gameDTO.owner().id())
        val gameId = gameDTO.id()

        if (gameId == null || ownerPlayerSession == null) return false

        val room = GamePlayerSession(gameDTO, mutableListOf(ownerPlayerSession))
        rooms[gameId] = room

        return true
    }

    override fun unregisterRoom(roomId: String): Boolean = rooms.remove(roomId) != null

    override fun addPlayerToRoom(roomId: String, session: PlayerSession): Room? {
        val room = rooms[roomId] ?: return null

        return if (!room.playerSessions().contains(session)) {
            val game = gameService.registerNewPlayer(room.game().id()!!, session.player()) ?: return null
            val players = room.playerSessions().plus(session).toList()
            val updatedRoom = GamePlayerSession(game, players)

            rooms.replace(roomId, updatedRoom)
            updatedRoom
        }
        else null
    }

    override fun removePlayerFromRoom(roomId: String, playerId: String): Room? {
        val room = rooms[roomId] ?: return null
        val playerSession = room.playerSessions().find { it.player()?.id() == playerId } ?: return null

        val players = room.playerSessions().minus(playerSession).toList()
        val updatedRoom = GamePlayerSession(room.game(), players)
        rooms.replace(roomId, updatedRoom)

        return updatedRoom
    }

    override fun retrieveById(roomId: String): Room? = rooms[roomId]
}
