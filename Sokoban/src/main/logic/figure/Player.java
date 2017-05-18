package main.logic.figure;

import java.io.Serializable;

/**
 * Created by Dima on 13.05.2017.
 */
public class Player extends BaseMovableFigure {

    public Player(int posX, int posY) {
        super(posX, posY, LevelField.PLAYER.getImage());
    }
}
