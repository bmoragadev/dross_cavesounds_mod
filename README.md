
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
- **3D Spatial Audio:** Sounds are spawned randomly around the player within a configurable radius, creating a sense of paranoia and directionality.
- **Smart Detection:** Sounds only play when specific conditions are met (Light level, Y level, Cave ceiling detected).
- **Live Configuration:** Admins can adjust the frequency, volume, and conditions in real-time using in-game commands.

## ⚙️ Configuration
The mod generates a config file located at `config/drosscavesounds.json`. You can edit this file manually or use the in-game commands.

### Default Configuration:
```json
{
  "averageMinutes": 5,
  "yCaveLevel": 40,
  "soundRadius": 15,
  "soundVolume": 1.0,
  "maxBlockLight": 6
}
```


### Parameters:

| Option | Default | Description |
| --- | --- | --- |
| `averageMinutes` | `5` | The average time (in minutes) between scares. Decrease for more frequency. |
| `yCaveLevel` | `40` | The maximum Y height. Sounds will only play if the player is **below** this level. |
| `soundRadius` | `15` | The distance (in blocks) from the player where sounds can spawn randomly. |
| `soundVolume` | `1.0` | The volume/range of the sound. Higher values allow the sound to be heard from further away. |
| `maxBlockLight` | `6` | The maximum light level allowed for a sound to play (0-15). |

## 💻 Commands

You can configure the mod without restarting the server using the `/drosscavesounds` command. Requires **OP (Level 2)** permissions.

| Command | Description |
| --- | --- |
| `/drosscavesounds info` | Displays the current configuration values in chat. |
| `/drosscavesounds set minutes <number>` | Sets the average frequency in minutes. |
| `/drosscavesounds set ylevel <number>` | Sets the max Y level for sounds to trigger. |
| `/drosscavesounds set radius <number>` | Sets the spawn radius around the player. |
| `/drosscavesounds set volume <decimal>` | Sets the sound volume (e.g., 1.0, 2.5). |
| `/drosscavesounds set light <0-15>` | Sets the maximum light level allowed. |

## 📥 Installation

1. Download and install **[Fabric Loader](https://fabricmc.net/use/)**.
2. Download the **[Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)** mod (required).
3. Download the latest `.jar` release of **Dross Cave Sounds**.
4. Drop both `.jar` files into your `mods` folder.
5. Launch the game and enjoy the fear.

## ⚖️ License & Credits

### Code

The source code of this mod is licensed under the **MIT License**. You are free to learn from it or use it in your projects.

### Assets & Audio (IMPORTANT)

The audio files and iconography included in this mod are **fan-made tributes**.

* **Original Audio References:** Belong to **DrossRotzank**.
* This mod is a non-profit fan creation and is not officially affiliated with or endorsed by DrossRotzank.

---

<div align="center">
<sub>Made with ❤️ and fear by Shozaman</sub>
</div>

```

```