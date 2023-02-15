package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class ColorPart implements EntityPart {
    private double[] colors;

    public ColorPart(double r, double g, double b, double a) {
        colors = new double[4];
        colors[0] = r;
        colors[1] = g;
        colors[2] = b;
        colors[3] = a;
    }

    public ColorPart() {
        this(1, 1, 1, 1);
    }

    public double getR() {
        return colors[0];
    }

    public double getG() {
        return colors[1];
    }

    public double getB() {
        return colors[2];
    }

    public double getA() {
        return colors[3];
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        //intentionally blank, the color does not need processing as of now
        // if a spaceship will be changing colors during the game, this is where to add it
    }
}
