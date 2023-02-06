package fr.shield.games.api.infrastructure.app.sockets.models

data class GlobalEvent(val name: String, val sender: String, val data: Any?)
