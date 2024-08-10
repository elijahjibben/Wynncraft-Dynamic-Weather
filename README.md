# Wynncraft Dynamic Weather
A fully client-side weather mod made specifically for Wynncraft.

## Features

### **Random weather occurances!**
Rain, thunder, snow, and sandstorms can all occur randomly throughout Wynn. Regions like Nemract and Galleon's Graveyard experience significantly more rain. The Nesaak and Lusuco tundra recieve regular snowfall and blizzards. The Almuj desert faces dust and sandstorms. Smog creeps into the Silent Expanse. Over a dozen regions have unique weather chances and hazards.
### Preview

![WynnWeather4](https://github.com/user-attachments/assets/a188d78e-51a2-4a98-831e-66df96c98cf5)
![Silent Expanse](https://cdn.modrinth.com/data/cached_images/80c3d8381b8591648a0d80668b2666beed559b8c.png)

### Technical
The mod can be broken down into a few different key features:

#### Command

```
/wynnweather clear/rain/thunder
```
This command allows the user to force a particular weather mode. If set to rain/thunder, the game will remain in this state until it is turned off. While set to clear the default weather engine is active.

#### Weather Engine
Each tick a calculation is made to determine if weather will start. In most areas, this calculation comes out to ~20% chance per in-game day. This varies from region to region however, for example Nemract has a ~75% chance per day.

#### Weather Overrides
By default, Nesaak would not snow and most of Almuj would not have sandstorms. The mod overrides these areas to make sure the correct particles are displayed. In some regions like the Volcanic Isles and Molten Heights no rain will fall.

## Dependencies
This mod requires:\
[Fabric API](https://modrinth.com/mod/fabric-api)\
[Cloth Config API](https://modrinth.com/mod/cloth-config)

The following mod is _highly_ recommended:\
[Particle Rain](https://modrinth.com/mod/particle-rain)\
I recommend disabling rain particles and enabling render vanilla weather in the Particle Rain config.\
**Note:** Without Particle Rain, sandstorms will not render in Almuj.\
If you do not wish to use Particle Rain, edit this mods config and enable "Render Vanilla Snow". It prevents vanilla snow particles from rendering by default.

## Note
This mod is very fresh! Please report bugs you notice, as it is difficult to debug a mod that takes nearly 2 hours to activate in some areas. Feedback is also welcome!\
**A note on modpacks:** Feel free to implement this mod into your modpacks! This mod was developed for use in my modpack, so check that out too as soon as its available :)
