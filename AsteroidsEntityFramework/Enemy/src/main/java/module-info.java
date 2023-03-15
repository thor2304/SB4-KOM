import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
import dk.sdu.mmmi.cbse.enemySystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemySystem.EnemyPlugin;

module Enemy {
    //exports dk.sdu.mmmi.cbse.enemySystem;
    requires Common;
    requires Player;

    provides IEntityProcessingService with EnemyControlSystem;
    provides IGamePluginService with EnemyPlugin;
}