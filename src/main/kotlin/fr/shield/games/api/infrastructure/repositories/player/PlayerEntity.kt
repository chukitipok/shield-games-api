package fr.shield.games.api.infrastructure.repositories.player

import fr.shield.games.api.core.players.models.Player
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "player")
data class PlayerEntity(
    @Id
    @GeneratedValue
    val id: UUID?,
    val username: String
): Player {
    override fun id(): String = id.toString()

    override fun username(): String = username
}
