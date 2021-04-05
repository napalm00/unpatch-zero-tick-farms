![Logo](https://i.imgur.com/LdJzQSF.png)

# Un-patch Zero Tick Farms

A FabricMC mod for Minecraft 20w14a that restores zero-tick farms (that were patched in 20w12a).

## Installation

1. Get the latest release from the [releases page](https://github.com/napalm00/unpatch-zero-tick-farms/releases)
2. Place into the "mods" folder on your Fabric server. **This is a server-only mod, clients do not have to install it.**


## Configuration

As of v1.0.9, you can configure the mod in `config/unpatch-zero-tick-farms.json` as such:

```javascript
{
  "isZeroTickUnpatchEnabled": true, // Master switch to enable/disable the mod, default: true (enabled)
  "isBambooZeroTickEnabled": true, // Master switch to enable/disable zero ticking on Bamboo, default: true (enabled)
  "isCactusZeroTickEnabled": true, // Master switch to enable/disable zero ticking on Cactus, default: true (enabled)
  "isChorusFlowerZeroTickEnabled": true, // Master switch to enable/disable zero ticking on Chorus Flower, default: true (enabled)
  "isSugarCaneZeroTickEnabled": true, // Master switch to enable/disable zero ticking on Sugarcane, default: true (enabled)
  "isVinesZeroTickEnabled": true // Master switch to enable/disable zero ticking on Vines, default: true (enabled)
}
```