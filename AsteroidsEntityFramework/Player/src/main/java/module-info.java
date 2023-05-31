import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
import dk.sdu.mmmi.cbse.playerSystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playerSystem.PlayerPlugin;

module Player {
    requires Common;
    requires CommonPlayer;

    opens dk.sdu.mmmi.cbse.playerSystem.assets;

    provides IEntityProcessingService with PlayerControlSystem;
    provides IGamePluginService with PlayerPlugin;
}