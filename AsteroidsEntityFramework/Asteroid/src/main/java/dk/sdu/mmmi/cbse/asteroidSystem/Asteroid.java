package dk.sdu.mmmi.cbse.asteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author corfixen
 */
public class Asteroid extends Entity {
    public final int size;

    public Asteroid(int outerSize) {
        this.size = outerSize;
    }
}
