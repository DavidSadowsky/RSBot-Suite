# AIO Autominer

[![HitCount](http://hits.dwyl.com/DavidSadowsky/Autominer.svg)](http://hits.dwyl.com/DavidSadowsky/Autominer)
![GitHub last commit](https://img.shields.io/github/last-commit/google/skia.svg?style=flat)

All-In-One Autominer for Old School Runescape using Java and the RSPeer API.

## Setup

Follow this [guide](https://docs.rspeer.org/docs/setting-up-dev-environment) on the RSPeer website to set up your development environment.

Create a new package called `org.yourname` or something along those lines. This package will hold all of your scripts and it's not necessary if you're only going to be using one script; however, if you plan on downloading/adding more, this is the structure that I like to use.

Inside `org.yourname`, clone this repository. Then, build module `project name` (you can name the intellij project whatever you'd like) and your JAR executable should be automatically added to the RSPeer client.

## Support

Currently supported options:

- Lumbrige Swamp - Copper/Tin/Copper & Tin
- Rimmington - Iron/Gold/Clay
- Powermine (any location) - Copper/Tin/Copper & Tin/Iron/Gold/Clay

Support for more locations and ores will be added in future releases.
