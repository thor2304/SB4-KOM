package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class ImagePart implements EntityPart{
    private String path;

    public ImagePart(String path) {
        this.path = path;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }

    public String getPath() {
        return path;
    }
}
