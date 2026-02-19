# BurritoBot

A Discord bot written in Kotlin using Kord.

## Tech Stack

- **Language**: Kotlin 2.3
- **Discord**: Kord 0.17
- **HTTP**: Ktor Client 3.4
- **DI**: Koin 4.1
- **Build**: Gradle (Kotlin DSL) with Shadow plugin

## Project Structure

```
src/main/kotlin/com/burritobot/
├── Main.kt              # Entry point
├── Config.kt            # Environment variables
├── AppModule.kt         # Koin dependency injection
├── BurritoBot.kt        # Bot event handling
├── ReactionHandler.kt   # Auto-reactions to messages
├── command/             # Bot commands (!cmd, !gif, etc.)
├── model/               # Emoji, User, UserRepository
├── service/             # TenorClient, InsultService
└── util/                # Extension functions
```

## Commands

| Command | Description |
|---------|-------------|
| `!cmd` | List commands |
| `!blblbl` | Burrito catchphrase |
| `!ok` | Send OK emoji |
| `!pd` | Send PD emoji |
| `!noise <text>` | Text-to-speech (-v to keep message) |
| `!gif <query>` | Search and send a GIF |
| `!insult <target>` | Insult someone (compliments bots) |

## Build & Run

```bash
# Build fat JAR
./gradlew shadowJar

# Run locally
java -jar build/libs/burritobot.jar

# Docker
make up      # Build and start
make down    # Stop and remove
make restart # Restart
make logs    # Follow logs
```

## Environment Variables

- `BOT_TOKEN` - Discord bot token (required)
- `TENOR_TOKEN` - Tenor API key (required)

## Conventions

- Use extension functions for shared behavior (see `util/`)
- Emojis are defined as `data object` in sealed class `Emoji`
- Users with signatures are registered in `UserRepository`
- Commands implement the `Command` interface
- All message content is normalized (lowercase + unaccent) before matching
