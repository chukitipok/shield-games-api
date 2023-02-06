package fr.shield.games.api.core.games.ports

import fr.shield.games.api.core.games.models.Game
import fr.shield.games.api.core.players.models.Player

interface GameWriter {
    fun create(name: String, maxPlayers: Int, owner: Player): Game?
}
