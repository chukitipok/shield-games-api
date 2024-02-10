package fr.shield.games.api.infrastructure.adapters

import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.core.games.models.GameDTO
import fr.shield.games.api.core.games.ports.GameWriter
import fr.shield.games.api.infrastructure.repositories.player.PlayerEntity
import fr.shield.games.api.infrastructure.repositories.game.GameEntity
import fr.shield.games.api.infrastructure.repositories.game.GameRepository
import java.util.*

class GameJpaRepositoryAdapter(private val gameRepository: GameRepository) : GameWriter {
    override fun create(name: String, maxPlayers: Int, owner: Player): GameDTO {
        val roomOwner = PlayerEntity(UUID.fromString(owner.id()), owner.username())
        val gameEntity = GameEntity(null, name, maxPlayers, listOf(roomOwner), roomOwner, true)

        return gameRepository.save(gameEntity)
    }

    override fun retrieveAllActive(): List<GameDTO> = gameRepository.findAllByActiveIsTrue()

    override fun retrieveById(gameId: String): GameDTO? = gameRepository.findById(UUID.fromString(gameId)).orElse(null)

    override fun update(game: GameDTO): GameDTO {
        val entity = GameEntity(
            UUID.fromString(game.id()),
            game.name(),
            game.maxPlayers(),
            game.players().map { PlayerEntity(UUID.fromString(it.id()), it.username()) },
            PlayerEntity(UUID.fromString(game.owner().id()), game.owner().username()),
            game.isActive())

        return gameRepository.save(entity)
    }
}
