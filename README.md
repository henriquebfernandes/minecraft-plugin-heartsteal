# HeartSteal

HeartSteal is a Paper plugin that changes players' maximum health when they die or kill another player. It also provides commands for managing the executing player's health points.

## Requirements

- Paper 26.3
- Java 25

## Build and install

Build with the included Gradle wrapper:

```powershell
./gradlew build
```

Copy the JAR from `build/libs` into your server's `plugins` directory, then start or restart the server. For local plugin development, the Gradle Paper run task starts a test server:

```powershell
./gradlew runServer
```

## Configuration

On first startup, HeartSteal creates `plugins/HeartSteal/config.yml`. The shipped configuration is:

```yaml
initial_hp: 10
max_hp: 20
min_hp: 2
reward: 2
punishment: 2
```

Health is measured in Minecraft health points; 2 points equal one heart.

| Setting      | Meaning                                                        |
|--------------|----------------------------------------------------------------|
| `initial_hp` | Maximum health assigned to a player joining for the first time |
| `max_hp`     | Upper limit for maximum health                                 |
| `min_hp`     | Lower limit for maximum health                                 |
| `reward`     | Maximum health points added to a player killer                 |
| `punishment` | Maximum health points removed from a player who dies           |

On each death, the victim is punished down to `min_hp`. If another player caused the death, that killer receives the reward, up to `max_hp`. The reward also heals the killer by the configured `reward` amount. The plugin validates the health bounds and requires positive reward and punishment values when it loads. Restart the server after changing the configuration.

## Commands

Commands require the `heartsteal.admin` permission, granted to server operators by default. They act on the player running the command, so they cannot be run from console.

| Command                       | Effect                                                |
|-------------------------------|-------------------------------------------------------|
| `/heartsteal hp get`          | Display your maximum health points                    |
| `/heartsteal hp set <amount>` | Set your maximum health, clamped to `min_hp`–`max_hp` |
| `/heartsteal hp reset`        | Set your maximum health to `initial_hp`               |
| `/heartsteal hp reward`       | Apply the configured reward and heal by that amount   |
| `/heartsteal hp punish`       | Apply the configured punishment                       |

## Implementation overview

`HeartStealPlugin` loads the default configuration, creates the config and health manager objects, registers the player event listener, and registers the `/heartsteal` Brigadier command. The listener sets first-time players' maximum health and handles the death and kill changes. Health changes use Bukkit's `MAX_HEALTH` attribute.
