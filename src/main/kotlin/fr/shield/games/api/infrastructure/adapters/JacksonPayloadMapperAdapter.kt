package fr.shield.games.api.infrastructure.adapters

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import fr.shield.games.api.core.events.ports.PayloadMapper

class JacksonPayloadMapperAdapter : PayloadMapper {
    private val mapper: ObjectMapper = jacksonObjectMapper()
    override fun <T> convert(data: Any, classType: Class<T>): T = mapper.convertValue(data, classType)
}
