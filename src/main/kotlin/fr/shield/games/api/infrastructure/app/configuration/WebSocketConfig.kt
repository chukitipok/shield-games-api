package fr.shield.games.api.infrastructure.app.configuration

import fr.shield.games.api.infrastructure.web.handlers.SixTakesWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(private val sixTakesWebSocketHandler: SixTakesWebSocketHandler) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry
            .addHandler(sixTakesWebSocketHandler, "/api/six-takes").setAllowedOrigins("*")
    }
}
