package dk.sdu.mmmi.cbse.enemySystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

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
}
