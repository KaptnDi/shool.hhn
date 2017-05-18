package main.logic.figure;

import java.util.logging.Level;

/**
 * Created by Dima on 13.05.2017.
 */
public enum LevelField {

    DESTINATION("."), MONEY_BAG("$"), PLAYER("@"), WALL("#"), EMPTY(" "), LINE_BREAK("\n");

    private String image;

    LevelField(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public static LevelField getInstanceByValue(String image) {
        LevelField result = null;
        for (LevelField levelField : LevelField.values()) {
            if (levelField.getImage().equalsIgnoreCase(image)) {
                result = levelField;
                break;
            }
        }
        return result;
    }
}
