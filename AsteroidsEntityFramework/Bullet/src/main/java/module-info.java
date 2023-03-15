import dk.sdu.mmmi.cbse.BulletSystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.BulletSystem.BulletPlugin;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;

module Bullet {
    exports dk.sdu.mmmi.cbse.BulletSystem;
    requires Common;
    requires Asteroid;
    provides IEntityProcessingService with BulletControlSystem;
    provides IGamePluginService with BulletPlugin;
}