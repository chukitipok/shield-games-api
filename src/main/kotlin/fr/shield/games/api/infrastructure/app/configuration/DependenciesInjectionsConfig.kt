package fr.shield.games.api.infrastructure.app.configuration

import fr.shield.games.api.core.events.ports.EventConsumerGenerator
import fr.shield.games.api.core.events.ports.EventEmitter
import fr.shield.games.api.core.events.services.EventConsumerGeneratorService
import fr.shield.games.api.core.events.services.EventEmitterService
import fr.shield.games.api.core.games.ports.GameWriter
import fr.shield.games.api.core.games.ports.Games
import fr.shield.games.api.core.games.services.GameService
import fr.shield.games.api.core.players.ports.PlayerReader
import fr.shield.games.api.core.players.ports.Players
import fr.shield.games.api.core.players.services.PlayerService
import fr.shield.games.api.core.sessions.ports.GameSessionManager
import fr.shield.games.api.core.sessions.ports.PlayerSessionManager
import fr.shield.games.api.core.sessions.services.GameSessionManagerService
import fr.shield.games.api.core.sessions.services.PlayerSessionManagerService
import fr.shield.games.api.infrastructure.adapters.GameJpaRepositoryAdapter
import fr.shield.games.api.infrastructure.adapters.PlayerJpaRepositoryAdapter
import fr.shield.games.api.infrastructure.repositories.player.PlayerRepository
import fr.shield.games.api.infrastructure.repositories.game.GameRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependenciesInjectionsConfig(
    private val playerRepository: PlayerRepository,
    private val gameRepository: GameRepository
) {
    // REPOSITORIES
    @Bean fun playerReader(): PlayerReader = PlayerJpaRepositoryAdapter(playerRepository)
    @Bean fun gameWriter(): GameWriter = GameJpaRepositoryAdapter(gameRepository)

    // SERVICES
    @Bean fun players(): Players = PlayerService(playerReader())
    @Bean fun games(): Games = GameService(players(), gameWriter())
    @Bean fun eventEmitter(): EventEmitter = EventEmitterService()
    @Bean fun playerSessionManager(): PlayerSessionManager = PlayerSessionManagerService()
    @Bean fun gameSessionManager(): GameSessionManager = GameSessionManagerService(playerSessionManager())
    @Bean fun eventConsumerGenerator(): EventConsumerGenerator = EventConsumerGeneratorService(games(), playerSessionManager(), gameSessionManager())
}
