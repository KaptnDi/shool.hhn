package main.logic.figure;

import java.io.Serializable;

/**
 * Created by Dima on 13.05.2017.
 */
public class Destination extends BaseFigure {

    public Destination(int posX, int posY) {
        super(posX, posY, LevelField.DESTINATION.getImage());
    }
}
