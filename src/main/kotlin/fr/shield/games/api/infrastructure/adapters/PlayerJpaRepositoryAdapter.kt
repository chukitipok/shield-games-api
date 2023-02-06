package fr.shield.games.api.infrastructure.adapters

import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.core.players.ports.PlayerReader
import fr.shield.games.api.infrastructure.repositories.player.PlayerRepository
import java.util.UUID

class PlayerJpaRepositoryAdapter(private val playerRepository: PlayerRepository): PlayerReader {
    override fun retrieveById(id: String): Player? {
        return try {
            val uuid = UUID.fromString(id)
            playerRepository.findById(uuid).orElse(null)
        }
        catch (exception: Exception) { null }
    }

    override fun retrieveAllConnected(): List<Player> {
        TODO("Not yet implemented")
    }
}
