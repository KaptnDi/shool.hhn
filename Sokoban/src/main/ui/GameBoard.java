package main.ui;

import main.logic.figure.*;
import main.logic.utils.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Created by Dima on 13.05.2017.
 */
public class GameBoard extends JPanel implements Serializable {

    // Abstand in Pixel zum Rand
    private final static int OFFSET = 30;

    // Größe eines Feldes in Pixel
    private final static int FIELD_SIZE = 30;

    // Alle möglichen Figuren
    private ArrayList<Wall> walls;
    private ArrayList<MoneyBag> bags;
    private ArrayList<Destination> destinations;
    private Player player;

    // Größe des Spielfeldes in Pixel
    private int boardWidth;
    private int boardHeight;

    private boolean levelCompleted;
    private static int moveCounter;
    private final String level;

    private GameBoard live;
    private GameBoard undo;

    public GameBoard(String level) {
        live = this;
        initVariables();
        parseLevel(level);
        this.level = level;
        registerKeyListener();
    }

    public void registerKeyListener() {
        super.addKeyListener(new SokobanKeyListener());
        super.setFocusable(true);
    }

    private void restartCurrentLevel() {
        initVariables();
        parseLevel(level);
    }

    /**
     * Setzt alle Variablen auf den Startzustand.
     */
    private void initVariables() {
        walls = new ArrayList<>();
        bags = new ArrayList<>();
        destinations = new ArrayList<>();
        player = null;
        boardWidth = 0;
        boardHeight = 0;
        levelCompleted = false;
        moveCounter = 0;
    }

    /**
     * Erstellt anhand des String Figuren an den entsprechenden Positionen.
     * @param level
     */
    private void parseLevel(String level) {

        int currentX = OFFSET;
        int currentY = OFFSET;

        // Für jedes Zeichen im Level entsprechendes Objekt erstellen
        for (int i = 0; i < level.length(); i++) {

            // Aktuelles Zeichen auslesen und zu Objekt parsen
            LevelField currentField = LevelField.getInstanceByValue(level.substring(i, i+1));

            // Felder erstellen
            switch (currentField) {
                case DESTINATION:
                    destinations.add(new Destination(currentX, currentY));
                    currentX += FIELD_SIZE;
                    continue;
                case WALL:
                    walls.add(new Wall(currentX, currentY));
                    currentX += FIELD_SIZE;
                    continue;
                case PLAYER:
                    player = new Player(currentX, currentY);
                    currentX += FIELD_SIZE;
                    continue;
                case MONEY_BAG:
                    bags.add(new MoneyBag(currentX, currentY));
                    currentX += FIELD_SIZE;
                    continue;
                case EMPTY:
                    currentX += FIELD_SIZE;
                    continue;
                case LINE_BREAK:
                    if (boardWidth < currentX) {
                        boardWidth = currentX;
                    }
                    currentX = OFFSET;
                    currentY += FIELD_SIZE;
                    continue;
                default:
                    System.err.printf("Invalid char '%s' found", level.charAt(i));
                    break;
            }
        }
    }

    private void drawLevel(Graphics graphics) {

        // Alle Figuren in eine Liste zusammenfassen
        ArrayList<BaseFigure> all = new ArrayList<>();
        all.addAll(walls);
        all.addAll(bags);
        all.addAll(destinations);
        all.add(player);

        for (BaseFigure figure : all) {
            graphics.drawString(figure.getImage(), figure.getPosX(), figure.getPosY());
        }

        if (levelCompleted) {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            parent.setTitle("Gewonnen in " + moveCounter + " Zügen");
            graphics.drawString("Level completed", boardWidth / 2, boardHeight / 2);
        }
    }

    private boolean checkCollision(Player player, Direction direction) {
        boolean collided = false;

        // Objekte holen mit dennen kolidiert werden kann
        List<BaseFigure> barriers = new ArrayList<>();
        barriers.addAll(walls);
        barriers.addAll(bags);

        // Neue Position ermitteln
        int newX = player.getPosX();
        int newY = player.getPosY();

        switch (direction) {
            case LEFT:
                newX -= FIELD_SIZE;
                break;
            case UP:
                newY -= FIELD_SIZE;
                break;
            case DOWN:
                newY += FIELD_SIZE;
                break;
            case RIGHT:
                newX += FIELD_SIZE;
                break;
            default:
                break;
        }

        // Alle Hindernisse überprüfen
        for (BaseFigure barrier : barriers) {

            // Wenn ein Hindernis an neuer Position gefunden wurde
            if (barrier.getPosX() == newX && barrier.getPosY() == newY) {

                // Wenn es sich dabei um ein MoneyBag handelt
                if (barrier instanceof MoneyBag) {
                    MoneyBag moneyBag = (MoneyBag) barrier;
                    // Neue Position ermitteln
                    int newBagX = moneyBag.getPosX();
                    int newBagY = moneyBag.getPosY();

                    switch (direction) {
                        case LEFT:
                            newBagX -= FIELD_SIZE;
                            break;
                        case UP:
                            newBagY -= FIELD_SIZE;
                            break;
                        case DOWN:
                            newBagY += FIELD_SIZE;
                            break;
                        case RIGHT:
                            newBagX += FIELD_SIZE;
                            break;
                        default:
                            break;
                    }

                    // Jede Wall überprüfen
                    for (Wall wall: walls) {

                        // Wenn eine Wall an der neuen Stelle steht wird nicht bewegt
                        if (wall.getPosX() == newBagX && wall.getPosY() == newBagY) {
                            collided = true;
                            break;
                        }
                    }

                    // Wenn keine Wall im Weg steht wird MoneyBag und Spieler bewegt
                    if (!collided) {
                        moneyBag.move(direction, FIELD_SIZE);
                        levelCompleted = checkIfLevelCompleted();
                    }

                // Wenn es sich um eine Wand handelt
                } else {
                    collided = true;
                    break;
                }
            }
        }

        return collided;
    }

    private boolean checkIfLevelCompleted() {
        int bagsSize = bags.size();
        int bagsOnDestination = 0;
        for (MoneyBag bag : bags) {
            for (Destination destination : destinations) {
                if (bag.getPosX() == destination.getPosX() && bag.getPosY() == destination.getPosY()) {
                    bagsOnDestination++;
                    break;
                }
            }
        }
        return bagsOnDestination == bagsSize;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawLevel(g);
    }

    public class SokobanKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            // KeyCode der gedrückten Taste ermitteln
            int keyCode = e.getKeyCode();

            // W, S, A, D und Pfeiltasten zum Bewegen, R zum Neustarten, Z für Undo
            Direction direction = null;
            switch (keyCode) {
                case KeyEvent.VK_Z:
                    live = undo;
                case KeyEvent.VK_R:
                    restartCurrentLevel();
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                    direction = Direction.UP;
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    direction = Direction.DOWN;
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    direction = Direction.LEFT;
                    break;
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    direction = Direction.RIGHT;
                    break;
                default:
                    direction = null;
                    break;
            }

            if (!levelCompleted) {
                // Wenn eine Richtungstaste gedrückt wurde und keine Wall im Weg steht wird bewegt.
                if (direction != null && !checkCollision(player, direction)) {
                    moveCounter++;
                    undo = live;
                    player.move(direction, FIELD_SIZE);
                }
            }

            repaint();
        }
    }
}
