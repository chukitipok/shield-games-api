package fr.shield.games.api.core.players.ports

import fr.shield.games.api.core.players.models.Player

interface Players {
    fun retrieveById(id: String): Player?
    fun retrieveAllConnected(): List<Player>
}
