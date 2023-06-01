package dk.sdu.mmmi.cbse.asteroidSystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class AsteroidControlSystem implements IEntityProcessingService {
    private final int MINIMUM_SIZE = 5;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroidEntity : world.getEntities(Asteroid.class)) {
            Asteroid asteroid = (Asteroid) asteroidEntity;

            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            ColorPart colorPart = asteroid.getPart(ColorPart.class);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            colorPart.process(gameData, asteroid);

            if (asteroid.shouldSplit()) {
                splitAsteroid(asteroid, world);
            }

            updateShape(asteroid, asteroid.size);
        }
    }

    private void splitAsteroid(Asteroid asteroid, World world){
        world.removeEntity(asteroid);

        PositionPart positionPart = asteroid.getPart(PositionPart.class);
        MovingPart movingPart = asteroid.getPart(MovingPart.class);
        ColorPart colorPart = asteroid.getPart(ColorPart.class);

        if (asteroid.size > MINIMUM_SIZE) {
            for (int i = 0; i < 2; i++) {
                Asteroid newAsteroid = new Asteroid(asteroid.size/2);
                newAsteroid.setRadius(asteroid.getRadius() / 2);

                PositionPart newPositionPart = new PositionPart(positionPart.getX(), positionPart.getY(),
                        positionPart.getRadians() - (float) (Math.PI * 0.5) + (float) (Math.PI * i));
                // Move the asteroids to the side, so they dont collide with each other immediately
                newPositionPart.setX(newPositionPart.getX() + (float) (Math.cos(newPositionPart.getRadians()) * asteroid.getRadius()));
                newPositionPart.setY(newPositionPart.getY() + (float) (Math.sin(newPositionPart.getRadians()) * asteroid.getRadius()));
                newAsteroid.add(newPositionPart);

                MovingPart newMovingPart = movingPart.copy();
                newAsteroid.add(newMovingPart);

                ColorPart newColorPart = colorPart.copy();
                newAsteroid.add(newColorPart);
                world.addEntity(newAsteroid);
            }
        }
    }

    private void updateShape(Entity entity, float outerSize) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();


        double inc = (Math.PI * 2) / AsteroidPlugin.verticeCount;
        for (int i = 0; i < shapey.length ; i++) {
            shapex[i] = (float) (x + Math.cos(radians + (inc * i)) * outerSize);
            shapey[i] = (float) (y + Math.sin(radians + (inc * i)) * outerSize);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
