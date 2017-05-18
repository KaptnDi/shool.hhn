package main.logic.figure;

import java.io.Serializable;

/**
 * Created by Dima on 13.05.2017.
 */
public class Wall extends BaseFigure {

    public Wall(int posX, int posY) {
        super(posX, posY, LevelField.WALL.getImage());
    }

}
