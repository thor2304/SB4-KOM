package dk.sdu.mmmi.cbse.BulletSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.net.PortUnreachableException;
import java.util.ArrayList;

public class BulletPlugin implements IGamePluginService {

    private ArrayList<Entity> bullets = new ArrayList<>();
    private final int size = 15;
    public static final int verticeCount = 10;

    public BulletPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        int count = 2;
        // Add entities to the world
        for (int i = 0; i < count; i++) {
            PositionPart positionPart = new PositionPart((float) (100 + i * 30), 100 + i * 30, (float) (Math.PI * Math.random()));
            spawnBullet(positionPart, world);
        }
    }

    public void spawnBullet(PositionPart positionPart, World world){
            Entity bullet = createBullet(positionPart);
            bullets.add(bullet);
            world.addEntity(bullet);
    }

    private Entity createBullet(PositionPart positionPart) {
        float acceleration = 800;
        float maxSpeed = 1000;

        Entity asteroid1 = new Bullet(size);
        asteroid1.add(new MovingPart(0, acceleration, maxSpeed, 0));
        asteroid1.add(positionPart);
        asteroid1.add(new ColorPart(0, 91, 46, 1));

        asteroid1.setRadius(size);

        MovingPart movingPart = asteroid1.getPart(MovingPart.class);
        movingPart.setUp(true);

        asteroid1.setShapeX(new float[verticeCount]);
        asteroid1.setShapeY(new float[verticeCount]);

        return asteroid1;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid : bullets) {
            world.removeEntity(asteroid);
        }
    }

}
