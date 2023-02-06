package fr.shield.games.api.infrastructure.repositories.game

import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.players.models.Player
import fr.shield.games.api.infrastructure.repositories.player.PlayerEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "game")
data class GameEntity(
    @Id @GeneratedValue val id: UUID?,
    val name: String,
    @Column(name = "participants_limit") val participantsLimit: Int,
    @OneToMany val players: List<PlayerEntity>,
    @ManyToOne val owner: PlayerEntity,
    val active: Boolean
) : Game {

    override fun id(): String = id.toString()

    override fun name(): String = name

    override fun maxPlayers(): Int = participantsLimit

    override fun players(): List<Player> = players

    override fun isActive(): Boolean = active

    override fun owner(): Player = owner
}
