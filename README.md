<div align="center">
  <img src="src/main/resources/icon.png" alt="Dross Cave Sounds Logo" width="128" height="128">

  <h1>Dross Cave Sounds</h1>

  <p>
    <b>A Minecraft Fabric Mod that brings the terror of DrossRotzank to your caves.</b>
  </p>

  <p>
    <a href="https://fabricmc.net/"><img src="https://img.shields.io/badge/Loader-Fabric-be7a5a?style=flat&logo=fabric" alt="Fabric"></a>
    <a href="https://github.com/Shozaman/drosscavesounds/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License"></a>
    <img src="https://img.shields.io/badge/Minecraft-1.21-green.svg" alt="Minecraft Version">
  </p>
</div>

---

## 📖 About
**Dross Cave Sounds** is a mod inspired by the legendary horror narrator and content creator **DrossRotzank**. It replaces or adds to the standard cave ambience with suspenseful and terrifying sounds, creating a unique atmosphere of psychological horror while you mine.

Perfect for playing with friends, as the sounds are synchronized in the environment—if you hear it, they hear it too!

## ✨ Features
- **Atmospheric Horror:** Adds custom sound events that trigger when exploring dark, deep caves.
- **Smart Detection:** Sounds only play when specific conditions are met (Low light, Deep Y level, Cave ceiling detected).
- **Immersive Multiplayer:** Sounds are played in the world at the player's position, meaning other players nearby will also hear the terror coming from your direction.
- **Fully Configurable:** You control the horror frequency.

## ⚙️ Configuration
The mod generates a config file located at `config/drosscavesounds.json`. You can edit this file to tweak the experience.

### Default Configuration:
```json
{
  "averageMinutes": 5,
  "yCaveLevel": 40
}