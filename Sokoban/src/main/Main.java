package main;

import main.ui.GameBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Dima on 13.05.2017.
 */
public class Main extends JFrame implements Serializable {

    private static Main singelton;

    GameBoard board = null;

    private transient JFileChooser fileChooser;

    public Main(String level) {
        board = new GameBoard(level);
        initWindow();
    }

    /**
     * Dieser Konstruktor wird beim deserialisieren verwendet.
     * @param gameBoard
     */
    public Main(GameBoard gameBoard) {
        this.board = gameBoard;
        initWindow();
    }

    private void initWindow() {
        super.setJMenuBar(createMenuBar());
        super.add(board);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setTitle("Sokoban");
        super.setVisible(true);
        super.setLocationRelativeTo(null);
        super.setSize(300, 350);
    }

    private JMenuBar createMenuBar() {
        // Menü Eintrag + Datei Eintrag
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Datei");

        // Levelmenü
        JMenu lvlMenu = new JMenu("Level auswahl");
        JMenuItem lvl1 = new JMenuItem("Level 1");
        lvl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String level1 =  "  #####\n" +
                                "###   #\n" +
                                "# $ # ##\n" +
                                "# #  . #\n" +
                                "#    # #\n" +
                                "## #   #\n" +
                                " #@  ###\n" +
                                " #####";
                singelton = new Main(level1);
            }
        });

        JMenuItem lvl2 = new JMenuItem("Level 2");
        lvl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String level2 = "  #####\n" +
                                "###   #\n" +
                                "# $ # ##\n" +
                                "# #  . #\n" +
                                "#    # #\n" +
                                "##$#.  #\n" +
                                " #@  ###\n" +
                                " #####";
                singelton = new Main(level2);
            }
        });

        JMenuItem lvl3 = new JMenuItem("Level 3");
        lvl3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String level3 = "  #####\n" +
                                "###   #\n" +
                                "# $ # ##\n" +
                                "# #  . #\n" +
                                "# .  # #\n" +
                                "##$#.$ #\n" +
                                " #@  ###\n" +
                                " #####";
                singelton = new Main(level3);
            }
        });

        JMenuItem lvl4 = new JMenuItem("Level 4");
        lvl4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String level4 = "    ####\n" +
                                "#####  #\n" +
                                "#   $  #\n" +
                                "#  .#  #\n" +
                                "## ## ##\n" +
                                "#      #\n" +
                                "# @#   #\n" +
                                "#  #####\n" +
                                "####";
                singelton = new Main(level4);
            }
        });

        JMenuItem lvl5 = new JMenuItem("Level 5");
        lvl5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String level5 = "    ####\n" +
                                "#####  #\n" +
                                "#   $  #\n" +
                                "# *.#  #\n" +
                                "## ## ##\n" +
                                "#      #\n" +
                                "# @#   #\n" +
                                "#  #####\n" +
                                "####";
                singelton = new Main(level5);
            }
        });

        JMenuItem dLvl1 = new JMenuItem("Diff Level 1");
        dLvl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DLvl1 =
                                " ######\n" +
                                "##  . #\n" +
                                "# * # #\n" +
                                "# .$  #\n" +
                                "#  #$##\n" +
                                "## @ #\n" +
                                " #####";
                singelton = new Main(DLvl1);
            }
        });

        JMenuItem dLvl2 = new JMenuItem("Diff Level 2");
        dLvl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DLvl2 =
                                "#######\n" +
                                "#  .@ #\n" +
                                "# #.# #\n" +
                                "#   $ #\n" +
                                "#.$$ ##\n" +
                                "#  ###\n" +
                                "####";
                singelton = new Main(DLvl2);
            }
        });

        JMenuItem DLvl3 = new JMenuItem("Diff Level 3");
        DLvl3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DLvl3 =
                                "   ####\n" +
                                "#### @#\n" +
                                "#  *$ #\n" +
                                "#     #\n" +
                                "## .###\n" +
                                " #$ #\n" +
                                " # .#\n" +
                                " ####";
                singelton = new Main(DLvl3);
            }
        });

        JMenuItem DLvl4 = new JMenuItem("Diff Level 4");
        DLvl4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DLvl4 =
                            "### ###\n" +
                            "#.###.#\n" +
                            "# #  .#\n" +
                            "# $$ @#\n" +
                            "#  $  #\n" +
                            "#  #  #\n" +
                            "#  ####\n" +
                            "####";
                singelton = new Main(DLvl4);
            }
        });

        JMenuItem DLvl5 = new JMenuItem("Diff Level 5");
        DLvl5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String DLvl5 =
                                "   ####\n" +
                                "   # @##\n" +
                                "####   #\n" +
                                "#. #$$ #\n" +
                                "#     ##\n" +
                                "#.  $##\n" +
                                "##.  #\n" +
                                " #####";
                singelton = new Main(DLvl5);
            }
        });

        // Speichern des aktuellen Spielstandes
        JMenuItem save = new JMenuItem("Speichern");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(Main.this);
                if (result == JFileChooser.APPROVE_OPTION) {

                    try (
                        OutputStream os = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
                        OutputStream buffer = new BufferedOutputStream(os);
                        ObjectOutput  output = new ObjectOutputStream(buffer);
                        ) {
                        try {
                        output.writeObject(board);
                        output.flush();
                        } finally {
                            output.close();
                        }
                    } catch (IOException ex) {
                        System.err.print(ex);
                    }
                }
            }
        });
        fileMenu.add(save);

        // Laden eines Serialisierten Spielstandes
        JMenuItem load = new JMenuItem("Laden");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(Main.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try (
                            InputStream is = new FileInputStream(fileChooser.getSelectedFile().getAbsolutePath());
                            InputStream buffer = new BufferedInputStream(is);
                            ObjectInput input = new ObjectInputStream(buffer);
                            ) {
                        try {
                            singelton.remove(board);
                            board = (GameBoard) input.readObject();
                            singelton.add(board);
                            board.repaint();
                            board.updateUI();
                            board.registerKeyListener();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } finally {
                            input.close();
                        }
                    } catch (IOException ex) {
                        System.err.print(ex);
                    }
                }
            }
        });
        fileMenu.add(load);
        menuBar.add(fileMenu);
        menuBar.add(lvlMenu);
        lvlMenu.add(lvl1);
        lvlMenu.add(lvl2);
        lvlMenu.add(lvl3);
        lvlMenu.add(lvl4);
        lvlMenu.add(lvl5);
        lvlMenu.add(dLvl1);
        lvlMenu.add(dLvl2);
        lvlMenu.add(DLvl3);
        lvlMenu.add(DLvl4);
        lvlMenu.add(DLvl5);

        return menuBar;
    }

    public static void main(String[] args) {
        // TODO logik ausarbeiten dass level aus textdatei geladen wird.
        //Main erstellt automatisch das lvl 1
        String level1 =  "  #####\n" +
                         "###   #\n" +
                         "# $ # ##\n" +
                         "# #  . #\n" +
                         "#    # #\n" +
                         "## #   #\n" +
                         " #@  ###\n" +
                         " #####";
        singelton = new Main(level1);
    }
}