package dk.sdu.mmmi.cbse.playerSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class Player extends Entity {

    @Override
    public void handleCollision(Entity other, World world) {
        super.handleCollision(this, world);
    }
}
