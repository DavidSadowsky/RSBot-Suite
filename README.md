# OSRS Bot Suite

[![HitCount](http://hits.dwyl.com/DavidSadowsky/Autominer.svg)](http://hits.dwyl.com/DavidSadowsky/RSBot-Suite)

Collection of all my scripts for Old School Runescape using Java and the RSPeer API.

## Setup

Follow this [guide](https://docs.rspeer.org/docs/setting-up-dev-environment) on the RSPeer website to set up your development environment.

Create a new package called `org.yourname` or something along those lines. This package will hold all of your scripts and it's not necessary if you're only going to be using one script; however, if you plan on downloading/adding more, this is the structure that I like to use.

Inside `org.yourname`, clone this repository. Then, build module `project name` (you can name the intellij project whatever you'd like) and your JAR executable should be automatically added to the RSPeer client.

## Support

Currently supported scripts:

- Mining
- Fireamking
- Zamorak Wine Telegrabber
- Smelting

In progress:

- Tutorial island
- Woodcutting
- Muling
- Magic
- Combat
- Questing (Romeo & Juliet, Ernest the Chicken, Cooks Assistant, Sheep Shearer)
- Cooking
- Fishing
- Smithing

Misc upgrades coming soon:

- Paint utility for experience tracking
- Bug fixes for the time utility
- Improved GUIs
- Improved randomization on mining & smelting scripts
