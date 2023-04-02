package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

import java.io.*;
import java.net.URISyntaxException;


public class SpritePart implements EntityPart{
    private static final String ASSET_LOCATION = "Assets";
    private String spriteName;
    private final String atlasPath;

    private final File atlasFile;


    public String getAtlasPath() {
        return atlasPath;
    }

    /**
     *
     * @param instanceOfClassWithResource This class must have a ressources folder with an atlas and corresponding texture present
     * @param atlasName Must be including the extension (.atlas)
     * @param spriteName The name of the first sprite to use. Must be present in the Atlas
     */
    public SpritePart(Entity instanceOfClassWithResource, String atlasName, String spriteName) {
        File tempAtlasFile;
        tempAtlasFile = createFile(instanceOfClassWithResource, atlasName);

        int extensionIndex = atlasName.lastIndexOf(".");
        String pngName = atlasName.substring(0, extensionIndex) + ".png";
        createFile(instanceOfClassWithResource, pngName);


        this.spriteName = spriteName;
        String path = instanceOfClassWithResource.getClass().getPackageName();
        path = path.replace(".", "/");
        if (!atlasName.startsWith("/")){
            atlasName = "/" + atlasName;
        }
        this.atlasPath = path + atlasName;
        atlasFile = tempAtlasFile;
    }

    private static File createFile(Entity instanceOfClassWithResource, String fileName) {
        File tempAtlasFile;
        try (InputStream inputStream = instanceOfClassWithResource.getClass().getResourceAsStream(fileName)) {
            String[] packageNameParts = instanceOfClassWithResource.getClass().getName().split("\\.");
            String packageIdentifier =packageNameParts[packageNameParts.length -1];
            tempAtlasFile = new File(ASSET_LOCATION + "/" + packageIdentifier + "/" + fileName);
            tempAtlasFile.getParentFile().mkdirs();

            try (OutputStream outputStream = new FileOutputStream(tempAtlasFile)) {
                assert inputStream != null;
                outputStream.write(inputStream.readAllBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tempAtlasFile;
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

    public File getAtlasFile() {
        return atlasFile;
    }
}
