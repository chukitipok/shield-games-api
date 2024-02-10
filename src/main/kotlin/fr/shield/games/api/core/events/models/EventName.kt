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
    PLAYER_JOINED_ROOM("player joined room"),
    PLAYER_JOINS_ROOM("player joins room"),
    ROW_SELECTED("row selected")
    ;

    companion object {
        fun from(label: String): EventName? = EventName.values().find { it.label == label }
    }
}
