module Core {
    requires Common;
    requires CommonAsteroid;
    requires CommonBullet;
    requires java.desktop;
    requires com.badlogic.gdx;

    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;
}