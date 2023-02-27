package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.BulletSystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.BulletSystem.BulletPlugin;
import dk.sdu.mmmi.cbse.asteroidSystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidSystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.collisionSystem.CircularCollisionSystem;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.ColorPart;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IGamePluginService;
import dk.sdu.mmmi.cbse.common.serviceInterfaces.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.EntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.GamePluginService;
import dk.sdu.mmmi.cbse.common.services.PostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemySystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemySystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;
import java.util.ArrayList;
import java.util.List;

public class Game
        implements ApplicationListener {

    private static OrthographicCamera cam;
    private ShapeRenderer sr;

    private final GameData gameData = new GameData();
    private final World world = new World();

    @Override
    public void create() {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2f, gameData.getDisplayHeight() / 2f);
        cam.update();

        sr = new ShapeRenderer();

        Gdx.input.setInputProcessor(
                new GameInputProcessor(gameData)
        );

        GamePluginService.getInstance().startAll(gameData, world);
    }

    @Override
    public void render() {

        // clear screen to black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        EntityProcessingService.getInstance().processAll(gameData, world);
        PostEntityProcessingService.getInstance().processAll(gameData, world);
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            ColorPart colorPart = entity.getPart(ColorPart.class);

            sr.setColor((float) colorPart.getR(), (float) colorPart.getG(), (float) colorPart.getB(), (float) colorPart.getA());

            sr.begin(ShapeRenderer.ShapeType.Line);

            float[] shapex = entity.getShapeX();
            float[] shapey = entity.getShapeY();

            for (int i = 0, j = shapex.length - 1;
                    i < shapex.length;
                    j = i++) {

                sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);
            }

            sr.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        GamePluginService.getInstance().stopAll(gameData, world);
    }
}
