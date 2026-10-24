# The Stalker: Six Nights (Forge 1.20.1)

A lightweight horror mod for Minecraft Forge 1.20.1.

## Testing commands
- `/stalker spawn` — summon a Stalker three blocks in front of you (requires permission level 2 / cheats).
- `/stalker remove` — remove Stalkers within 64 blocks.
- Creative inventory: search `Stalker Spawn Egg` or `stalkersixnights:stalker_spawn_egg`.

## Current features
- Random night events: footsteps, knocking, whispers, door sounds, and sightings.
- Custom Stalker entity and tall custom model using the vanilla Enderman texture.
- Command/egg-spawned Stalkers use basic Minecraft pathfinding, look-around and melee AI.
- The scripted story encounters remain mostly stationary and move while unseen.

## Build
Use Java 17 and Gradle 8.8, then run `gradle build`. Output is in `build/libs/`.

This project has not been verified in a live Minecraft client in this workspace. Build and test in a disposable world first.
