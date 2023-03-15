import dk.sdu.mmmi.cbse.collisionSystem.CircularCollisionSystem;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;

module CircularCollisionSystem {
//    exports dk.sdu.mmmi.cbse.collisionSystem;
    requires Common;
    provides IPostEntityProcessingService with CircularCollisionSystem;
}