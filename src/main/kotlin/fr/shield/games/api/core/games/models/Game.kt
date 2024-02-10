package fr.shield.games.api.core.games.models

import fr.shield.games.api.core.players.models.Player

data class Game(
    val id: String,
    val name: String,
    val maxPlayers: Int,
    val owner: Player,
    val players: List<Player>,
    val active: Boolean
) : GameDTO {

    constructor(game: GameDTO) : this(
        game.id()!!,
        game.name(),
        game.maxPlayers(),
        game.owner(),
        game.players(),
        game.isActive()
    )
    override fun id(): String = id

    override fun name(): String = name

    override fun maxPlayers(): Int = maxPlayers

    override fun players(): List<Player> = players

    override fun isActive(): Boolean = active

    override fun owner(): Player = owner
    fun addPlayer(player: Player): GameDTO = Game(id, name, maxPlayers, owner, players.plus(player), active)
}
