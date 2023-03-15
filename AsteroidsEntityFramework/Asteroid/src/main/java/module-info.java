import dk.sdu.mmmi.cbse.asteroidSystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidSystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroidSystem;
    requires Common;

    provides IEntityProcessingService with AsteroidControlSystem;
    provides IGamePluginService with AsteroidPlugin;
}