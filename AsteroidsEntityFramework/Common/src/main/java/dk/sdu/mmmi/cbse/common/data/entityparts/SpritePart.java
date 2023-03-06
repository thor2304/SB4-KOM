package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class SpritePart implements EntityPart{
    private String spriteName;

    private final TextureAtlas atlas;
    private Sprite sprite;

    public SpritePart(String atlasPath, String spriteName) {
        this.spriteName = spriteName;

        this.atlas = new TextureAtlas(Gdx.files.classpath(atlasPath));
        this.sprite = atlas.createSprite(spriteName);
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }

    public String getSpriteName() {
        return spriteName;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void changeSprite(String spriteName) {
        this.spriteName = spriteName;
        this.sprite = atlas.createSprite(spriteName);
    }
}
