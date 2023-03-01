package dk.sdu.mmmi.cbse.enemySystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.playerSystem.Player;

/**
 *
 * @author corfixen
 */
public class Enemy extends Entity {
    public final int outerSize;
    public final int innerSize;

    public Enemy(int outerSize, int innerSize) {
        this.outerSize = outerSize;
        this.innerSize = innerSize;
    }

    @Override
    public void handleCollision(Entity other, World world) {
        if (other instanceof Enemy){
            // both take damage
            return;
        }
        if (other instanceof Player){
            other.handleCollision(this, world);
            return;
        }
        super.handleCollision(this, world);
    }
}
