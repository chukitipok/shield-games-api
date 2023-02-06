package fr.shield.games.api.infrastructure.repositories.player

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PlayerRepository : JpaRepository<PlayerEntity, UUID>
