package main.logic.figure;

import main.logic.utils.Direction;

import java.io.Serializable;

/**
 * Created by Dima on 13.05.2017.
 */
public abstract class BaseMovableFigure extends BaseFigure {

    BaseMovableFigure(int posX, int posY, String image) {
        super(posX, posY, image);
    }

    public void move(Direction direction, int amount) {
        switch (direction) {
            case UP:
                setPosY(getPosY() - amount);
                break;
            case DOWN:
                setPosY(getPosY() + amount);
                break;
            case LEFT:
                setPosX(getPosX() - amount);
                break;
            case RIGHT:
                setPosX(getPosX() + amount);
                break;
        }
    }
}
