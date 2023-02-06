package fr.shield.games.api.core.events.models

enum class EventName(val label: String) {
    CREATE_GAME("create room"),
    CARD_SELECTED("card selected"),
    ERROR("error"),
    GAME_CREATED("room created"),
    GAME_END("game end"),
    GAME_START("game start"),
    PLAYER_CONNECTED("player connected"),
    PLAYER_INFO("player info"),
    PLAYER_JOINS_GAME("player joins game"),
    ROW_SELECTED("row selected")
    ;

    companion object {
        fun from(label: String): EventName = EventName.values().find { it.label == label } ?: throw IllegalArgumentException("Event name does not exists")
    }
}
