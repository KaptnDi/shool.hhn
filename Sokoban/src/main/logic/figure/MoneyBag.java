package main.logic.figure;

import java.io.Serializable;

/**
 * Created by Dima on 13.05.2017.
 */
public class MoneyBag extends BaseMovableFigure {

    public MoneyBag(int posX, int posY) {
        super(posX, posY, LevelField.MONEY_BAG.getImage());
    }

}
