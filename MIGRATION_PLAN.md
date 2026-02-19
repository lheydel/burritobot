# BurritoBot Kotlin Migration Plan

## Overview

Rewrite the BurritoBot Discord bot from JavaScript/Node.js to Kotlin, maintaining all existing functionality while establishing a clean, maintainable architecture for future development.

## Technology Stack

| Component       | Choice                  | Rationale                                       |
|-----------------|-------------------------|-------------------------------------------------|
| Language        | Kotlin                  | Target language                                 |
| Discord Library | Kord                    | Kotlin-native, coroutine-first                  |
| HTTP Client     | Ktor Client             | Kotlin-native, coroutines                       |
| DI Framework    | Koin                    | Lightweight, Kotlin-native, ready for future DB |
| Build Tool      | Gradle (Kotlin DSL)     | Modern, Kotlin-native                           |
| Async Model     | Coroutines              | Kotlin-native, clean code                       |
| Config          | Environment variables   | Simple, matches current setup                   |
| Deployment      | Docker (ARM-compatible) | Raspberry Pi target                             |

## Project Structure

```
burritobot/
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── Dockerfile
├── Makefile
├── .env.example
├── CLAUDE.md
├── src/
│   └── main/
│       ├── kotlin/
│       │   └── com/
│       │       └── burritobot/
│       │           ├── Main.kt                 # Entry point
│       │           ├── BurritoBot.kt           # Bot initialization & event handling
│       │           ├── config/
│       │           │   └── Config.kt           # Environment config
│       │           ├── di/
│       │           │   └── AppModule.kt        # Koin modules
│       │           ├── command/
│       │           │   ├── Command.kt          # Command interface
│       │           │   ├── CommandHandler.kt   # Command dispatcher
│       │           │   ├── CmdCommand.kt       # !cmd
│       │           │   ├── BlblblCommand.kt    # !blblbl
│       │           │   ├── OkCommand.kt        # !ok
│       │           │   ├── PdCommand.kt        # !pd
│       │           │   ├── NoiseCommand.kt     # !noise
│       │           │   ├── GifCommand.kt       # !gif
│       │           │   └── InsultCommand.kt    # !insult
│       │           ├── reaction/
│       │           │   ├── Reaction.kt         # Reaction data class
│       │           │   ├── ReactionHandler.kt  # Pattern matching & responses
│       │           │   └── RandomResponder.kt  # Random yes/no/ok responses
│       │           ├── emoji/
│       │           │   └── Emoji.kt            # Custom emoji definitions
│       │           ├── user/
│       │           │   ├── User.kt             # User data class
│       │           │   └── UserRepository.kt   # User lookup
│       │           ├── insult/
│       │           │   ├── InsultService.kt    # Insult generation orchestration
│       │           │   ├── InsultGenerator.kt  # Interface for generators
│       │           │   ├── StaticInsultGenerator.kt    # Pre-written insults
│       │           │   ├── TemplateInsultGenerator.kt  # Template-based (port of 'insults' lib)
│       │           │   └── ComplimentGenerator.kt      # Compliments for bots
│       │           ├── external/
│       │           │   └── TenorClient.kt      # Tenor GIF API
│       │           └── util/
│       │               ├── StringUtils.kt      # String helpers (unaccent, etc.)
│       │               └── RandomUtils.kt      # Random helpers
│       └── resources/
│           ├── insults/
│           │   ├── static-insults.txt      # From 'insult' package
│           │   ├── compliments.txt         # From 'insult-compliment' package
│           │   └── buckets.json            # Template buckets from 'insults' package
│           └── logback.xml                 # Logging config
└── src/
    └── test/
        └── kotlin/
            └── com/
                └── burritobot/
                    └── ...                 # Unit tests (future)
```

## Migration Phases

### Phase 1: Project Setup
- [ ] Initialize Gradle project with Kotlin DSL
- [ ] Configure dependencies (Kord, Ktor, Koin)
- [ ] Set up project structure
- [ ] Create Config class for environment variables
- [ ] Create Main.kt entry point
- [ ] Set up Koin dependency injection

### Phase 2: Core Bot Infrastructure
- [ ] Create BurritoBot class with Kord client
- [ ] Implement bot connection and ready event
- [ ] Implement message event listener skeleton
- [ ] Create Emoji sealed class/object with all custom emojis
- [ ] Create User data class and UserRepository

### Phase 3: Utility & External Services
- [ ] Port StringUtils (unaccent function using java.text.Normalizer)
- [ ] Port RandomUtils
- [ ] Implement TenorClient with Ktor

### Phase 4: Insult System
- [ ] Extract and embed insult lists from npm packages
- [ ] Create InsultGenerator interface
- [ ] Implement StaticInsultGenerator (from 'insult' + 'insult-compliment')
- [ ] Implement TemplateInsultGenerator (port 'insults' bucket system)
- [ ] Implement ComplimentGenerator
- [ ] Create InsultService with weighted selection

### Phase 5: Command System
- [ ] Create Command interface
- [ ] Implement CommandHandler (dispatcher with ! prefix)
- [ ] Port CmdCommand (!cmd)
- [ ] Port BlblblCommand (!blblbl)
- [ ] Port OkCommand (!ok)
- [ ] Port PdCommand (!pd)
- [ ] Port NoiseCommand (!noise with TTS)
- [ ] Port GifCommand (!gif)
- [ ] Port InsultCommand (!insult)

### Phase 6: Reaction System
- [ ] Create Reaction data class
- [ ] Implement ReactionHandler with pattern matching
- [ ] Port all reaction patterns (burrito, 666/999, metal, noel, itk)
- [ ] Implement RandomResponder (yes/no/maybe, random OK)
- [ ] Implement special cases (bot+pue, "k" check)
- [ ] Implement sign() function for GIF reactions

### Phase 7: Integration & Wiring
- [ ] Wire all components in Koin AppModule
- [ ] Connect command and reaction handlers to message events
- [ ] Implement message normalization pipeline (lowercase + unaccent)
- [ ] Test all features manually

### Phase 8: Deployment
- [ ] Create ARM-compatible Dockerfile
- [ ] Update Makefile for new build
- [ ] Create .env.example
- [ ] Test Docker build and run
- [ ] Test on Raspberry Pi (if available)

### Phase 9: Cleanup & Documentation
- [ ] Remove old JavaScript files
- [ ] Create CLAUDE.md with project conventions
- [ ] Update .gitignore for Kotlin/Gradle
- [ ] Final review and cleanup

---

## Detailed Module Translations

### Emoji System (emoji.js -> Emoji.kt)

**Current JS:**
```javascript
class Emoji {
    constructor(id, name) {
        this.id = id;
        this.name = name;
    }
    get string() { return `<:${this.name}:${this.id}>`; }
}
module.exports.OUI = new Emoji("694269347610525716", "oui");
```

**Target Kotlin:**
```kotlin
sealed class Emoji(val id: String, val name: String) {
    val formatted: String get() = "<:$name:$id>"

    object OUI : Emoji("694269347610525716", "oui")
    object NON : Emoji("694269370884800532", "non")
    object MAYBE : Emoji("694271052641411113", "maybe")
    // ... etc
}
```

### User System (user.js -> User.kt + UserRepository.kt)

**Target Kotlin:**
```kotlin
data class User(
    val id: Snowflake,
    val emoji: Emoji
)

class UserRepository {
    private val users = mapOf(
        Snowflake(168333966716174337) to User(Snowflake(168333966716174337), Emoji.CLOCHETTE),
        // ... etc
    )

    fun getById(id: Snowflake): User? = users[id]
    fun isBot(id: Snowflake): Boolean = id in listOf(BOT_SOLO_ID, BOT_BTEAM_ID)
}
```

### Reaction System (reaction.js -> Reaction.kt + ReactionHandler.kt)

**Target Kotlin:**
```kotlin
data class Reaction(
    val pattern: Regex,
    val probability: Double = 1.0,
    val response: suspend (Message) -> Unit
)

class ReactionHandler(
    private val tenorClient: TenorClient
) {
    private val reactions = listOf(
        Reaction(Regex("burrito")) { msg -> msg.addReaction(Emoji.BURRITAL.asReaction()) },
        Reaction(Regex("(666|999)")) { msg -> /* toggle logic */ },
        Reaction(Regex("metal")) { msg -> msg.addReaction(ReactionEmoji.Unicode("🤘")) },
        Reaction(Regex("noel")) { msg -> msg.addReaction(Emoji.BURRITOEL.asReaction()) },
        Reaction(Regex("itk"), probability = 0.1) { msg ->
            tenorClient.searchGif("cow")?.let { msg.channel.createMessage(it) }
        }
    )

    suspend fun handle(message: Message, normalizedContent: String) {
        reactions.filter { it.pattern.containsMatchIn(normalizedContent) }
            .filter { Random.nextDouble() < it.probability }
            .forEach { it.response(message) }
    }
}
```

### Insult System

**Approach:**
1. Extract static insults from `insult` and `insult-compliment` packages into `static-insults.txt`
2. Extract compliments from `insult-compliment` into `compliments.txt`
3. Port the bucket/template system from `insults` package to Kotlin

**Target Kotlin:**
```kotlin
interface InsultGenerator {
    fun generate(): String
}

class InsultService(
    private val staticGenerator: StaticInsultGenerator,      // 5% weight
    private val templateGenerator: TemplateInsultGenerator,  // 50% weight
    private val mixedGenerator: MixedInsultGenerator,        // 45% weight (from insult-compliment)
    private val complimentGenerator: ComplimentGenerator
) {
    private val generators = listOf(
        staticGenerator to 5,
        templateGenerator to 50,
        mixedGenerator to 45
    )

    fun getInsult(): String {
        val total = generators.sumOf { it.second }
        var rand = Random.nextInt(total)
        for ((gen, weight) in generators) {
            rand -= weight
            if (rand < 0) return gen.generate()
        }
        return generators.last().first.generate()
    }

    fun getCompliment(): String = complimentGenerator.generate()
}
```

### Command System

**Target Kotlin:**
```kotlin
interface Command {
    val name: String
    val description: String
    suspend fun execute(message: Message, args: List<String>)
}

class CommandHandler(
    private val commands: List<Command>
) {
    private val commandMap = commands.associateBy { it.name }

    suspend fun handle(message: Message): Boolean {
        val content = message.content
        if (!content.startsWith("!")) return false

        val parts = content.removePrefix("!").split(" ")
        val cmdName = parts.first().lowercase()
        val args = parts.drop(1)

        commandMap[cmdName]?.execute(message, args)
        return commandMap.containsKey(cmdName)
    }
}
```

### TenorClient (external.js -> TenorClient.kt)

**Target Kotlin:**
```kotlin
class TenorClient(
    private val httpClient: HttpClient,
    private val apiKey: String
) {
    suspend fun searchGif(query: String, limit: Int = 50): String? {
        return try {
            val response = httpClient.get<TenorResponse>(
                "https://api.tenor.com/v1/search"
            ) {
                parameter("q", query)
                parameter("key", apiKey)
                parameter("limit", limit)
            }
            response.results.randomOrNull()?.media?.firstOrNull()?.gif?.url
        } catch (e: Exception) {
            null
        }
    }
}
```

---

## Dockerfile (ARM-compatible for Raspberry Pi)

```dockerfile
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY build/libs/burritobot-*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Build process:**
```bash
# Build fat JAR
./gradlew shadowJar

# Build Docker image (on Pi or with buildx)
docker build -t burritobot .
```

---

## Dependencies (build.gradle.kts)

```kotlin
plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

dependencies {
    // Kord - Discord
    implementation("dev.kord:kord-core:0.13.1")

    // Ktor - HTTP Client
    implementation("io.ktor:ktor-client-core:2.3.7")
    implementation("io.ktor:ktor-client-cio:2.3.7")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.7")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.7")

    // Koin - DI
    implementation("io.insert-koin:koin-core:3.5.3")

    // Utilities
    implementation("com.ibm.icu:icu4j:74.2")  // For text normalization (unaccent)

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.14")
}
```

---

## Environment Variables

| Variable | Description | Required |
|----------|-------------|----------|
| `BOT_TOKEN` | Discord bot token | Yes |
| `TENOR_TOKEN` | Tenor API key | Yes |

---

## Notes

- **Kord uses Snowflake** for Discord IDs (wraps Long)
- **Text normalization**: Use `java.text.Normalizer` or ICU4J for removing diacritics
- **Coroutine scope**: Kord provides its own scope, use it for bot operations
- **Logging**: Use SLF4J with Logback (Kord uses it internally)
- **Shadow JAR**: Required for fat JAR deployment

---

## Post-Migration

After migration is complete:
1. Create `CLAUDE.md` with project conventions
2. Remove all `.js` files
3. Update `.gitignore` for Kotlin/Gradle artifacts
4. Test full functionality
5. Deploy to Raspberry Pi
