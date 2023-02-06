package fr.shield.games.api.infrastructure.app.sockets.models

import fr.shield.games.api.core.sessions.models.PlayerSession

data class EventMessagePayload(val name: String, val data: Any?, val sender: PlayerSession)
