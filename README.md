# ATA-Learn-And-Be-Curious-Project

Follow the instructions in the course for completing the group LBC project.

Ideas:
loadLevel(int Level, int hitPercentage, string alienShipUrl) { 
    ...
}
 - Instead of keeping them in a DB we can just load them with the level, so we don't have to wait for a DB call


After first move we update the ShipInformationTable with both ship locations, then every move after that we keep
ship information in the cache so the loads will be faster paced