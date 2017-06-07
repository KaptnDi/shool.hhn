package main.logic.figure;

import java.io.Serializable;

/**
 * Created by Dima on 13.05.2017.
 */
public abstract class BaseFigure implements Serializable {

    private int posX;
    private int posY;
    private String image;

    public BaseFigure(int posX, int posY, String image) {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}