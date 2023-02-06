package fr.shield.games.api.core.players.services

import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.core.players.ports.PlayerReader
import fr.shield.games.api.core.players.ports.Players

class PlayerService(private val reader: PlayerReader): Players {
    override fun retrieveById(id: String): Player? = reader.retrieveById(id)

    override fun retrieveAllConnected(): List<Player> {
        TODO("Not yet implemented")
    }
}
