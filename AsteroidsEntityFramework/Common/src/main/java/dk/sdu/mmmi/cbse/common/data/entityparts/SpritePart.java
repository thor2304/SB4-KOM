package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;


public class SpritePart implements EntityPart{
    private String spriteName;
    private final String atlasPath;


    public String getAtlasPath() {
        return atlasPath;
    }

    /**
     *
     * @param instanceOfClassWithResource This class must have a ressources folder with an atlas and corresponding texture present
     * @param atlasName Must be relative to the resource folder of instanceOfClassWithResource
     * @param spriteName The name of the first sprite to use. Must be present in the Atlas
     */
    public SpritePart(Object instanceOfClassWithResource, String atlasName, String spriteName) {
        this.spriteName = spriteName;
//        System.out.println(atlasName);
        String path = instanceOfClassWithResource.getClass().getPackageName();
        path = path.replace(".", "/");
        if (!atlasName.startsWith("/")){
            atlasName = "/" + atlasName;
        }
        path = path + atlasName;
//        System.out.println(path);
        this.atlasPath = path;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }

    public String getSpriteName() {
        return spriteName;
    }

    public void changeSprite(String spriteName) {
        this.spriteName = spriteName;
    }
}
