package dk.sdu.mmmi.cbse.BulletSystem;

import dk.sdu.mmmi.cbse.asteroidSystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class Bullet extends Entity {
    public final int size;

    public Bullet(int outerSize) {
        this.size = outerSize;
    }

    @Override
    public void handleCollision(Entity other, World world) {
        if (other instanceof Bullet){
            //split both?
            return;
        }
        if (other instanceof Asteroid) {
            super.handleCollision(other, world);
            return;
        }
        other.handleCollision(this, world);
    }
}
