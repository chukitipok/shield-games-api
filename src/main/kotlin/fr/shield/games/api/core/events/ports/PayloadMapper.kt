package fr.shield.games.api.core.events.ports

interface PayloadMapper {
    fun <T> convert(data: Any, classType: Class<T>) : T
}
