package fr.shield.games.api.infrastructure.repositories.game

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface GameRepository: JpaRepository<GameEntity, UUID> {
    fun findAllByActiveIsTrue(): List<GameEntity>
}
