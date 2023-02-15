package dk.sdu.mmmi.cbse.enemySystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.LEFT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.RIGHT;
import static dk.sdu.mmmi.cbse.common.data.GameKeys.UP;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class EnemyControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            ColorPart colorPart = enemy.getPart(ColorPart.class);

            movingPart.setLeft(gameData.getKeys().isDown(LEFT));
            movingPart.setRight(gameData.getKeys().isDown(RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(UP));
            
            
            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            colorPart.process(gameData, enemy);

            assert enemy instanceof Enemy;

            updateShape(enemy, ((Enemy) enemy).outerSize, ((Enemy) enemy).innerSize);
        }
    }

    private void updateShape(Entity entity, float outerSize, float innersize) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * outerSize);
        shapey[0] = (float) (y + Math.sin(radians) * outerSize);

        shapex[1] = (float) (x + Math.cos(radians - 4 * Math.PI / 5) * outerSize);
        shapey[1] = (float) (y + Math.sin(radians - 4 * Math.PI / 5) * outerSize);

        shapex[2] = (float) (x + Math.cos(radians + Math.PI) * innersize);
        shapey[2] = (float) (y + Math.sin(radians + Math.PI) * innersize);

        shapex[3] = (float) (x + Math.cos(radians + 4 * Math.PI / 5) * outerSize);
        shapey[3] = (float) (y + Math.sin(radians + 4 * Math.PI / 5) * outerSize);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
