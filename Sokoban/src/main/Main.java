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
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        // Speichern des aktuellen Spielstandes
        JMenuItem save = new JMenuItem("Save");
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
        JMenuItem load = new JMenuItem("Load");
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
        return menuBar;
    }

    public static void main(String[] args) {
        // TODO logik ausarbeiten dass level aus textdatei geladen wird.
        String level =  "  #####\n" +
                        "###   #\n" +
                        "# $ # ##\n" +
                        "# #  . #\n" +
                        "#    # #\n" +
                        "##$#.  #\n" +
                         " #@  ###\n" +
                         " #####";
        singelton = new Main(level);
    }
}