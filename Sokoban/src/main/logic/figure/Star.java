package main.logic.figure;

import java.util.logging.Level;

/**
 * Created by Dima on 07.06.2017.
 */
public class Star extends BaseFigure {
    public Star(int posX, int posY) {
        super(posX, posY, LevelField.STAR.getImage());
    }
}
