package fr.shield.games.api.infrastructure.adapters

import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.games.ports.GameWriter
import fr.shield.games.api.infrastructure.repositories.player.PlayerEntity
import fr.shield.games.api.infrastructure.repositories.game.GameEntity
import fr.shield.games.api.infrastructure.repositories.game.GameRepository
import java.util.*

class GameJpaRepositoryAdapter(private val gameRepository: GameRepository) : GameWriter {
    override fun create(name: String, maxPlayers: Int, owner: Player): Game {
        val roomOwner = PlayerEntity(UUID.fromString(owner.id()), owner.username())
        val gameEntity = GameEntity(null, name, maxPlayers, listOf(), roomOwner, true)

        return gameRepository.save(gameEntity)
    }
}
