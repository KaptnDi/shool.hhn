package flashcards;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Flashcards application user interface.
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
@SuppressWarnings("restriction")
public class UserInterface extends Application {
  FlashcardsSet flashcardsSet = new FlashcardsSet();
  FlashcardsStatistics statFlashCards = new FlashcardsStatistics();

  /**
   * The main entry point for all JavaFX applications. This method builds
   * the graphical interface.
   *
   * <p>In the menu item File -> Create new set of flashcards, you can create
   * a new set of flashcards. Via the menu-item View -> Learn flashcards, a new
   * window will pop-up, where you can go through the flashcards.
   *
   * @param stage The primary stage for this application.
   */
  @Override
  public void start(Stage stage) throws Exception {

    //Menu-bar

    //Menu
    MenuBar menuBar = new MenuBar();

    Menu fileMenu = new Menu("File");
    Menu editMenu = new Menu("Edit");
    Menu viewMenu = new Menu("View");

    menuBar.getMenus().addAll(fileMenu,editMenu,viewMenu);
    MenuItem creatingMode = new MenuItem("New Set of Flashcards");
    MenuItem learningMode = new MenuItem("Learn Flashcards...");
    MenuItem statisticsMode = new MenuItem("Get Learning Suggestions...");
    MenuItem flashcardsList = new MenuItem("Show All Sets of Flashcards...");

    fileMenu.getItems().addAll(creatingMode, flashcardsList);
    viewMenu.getItems().addAll(learningMode, statisticsMode);

    //Creating Flashcards

    //Labels
    Label term = new Label("Term");
    Label description = new Label("Description");

    Label notification = new Label();
    notification.setStyle("-fx-text-fill: red;");
    notification.setId("notification");
    final AtomicReference<Label> notifyRef = new AtomicReference<Label>(notification);

    //Text areas
    TextField termTextField = new TextField();
    final AtomicReference<TextField> termRef = new AtomicReference<TextField>(termTextField);
    TextArea descrpTextArea = new TextArea();
    final AtomicReference<TextArea> descrpRef = new AtomicReference<TextArea>(descrpTextArea);

    //Buttons
    Button saveButton = new Button("Save");
    Button clearButton = new Button("Clear");

    //Layouts
    HBox hbox1 = new HBox(term, termTextField);
    HBox hbox2 = new HBox(description, descrpTextArea);
    StackPane notifyLabel = new StackPane(notification);
    HBox hbox3 = new HBox(notifyLabel, saveButton, clearButton);
    VBox vbox1 = new VBox(hbox1, hbox2);

    BorderPane pane = new BorderPane(vbox1);
    pane.setTop(menuBar);
    pane.setBottom(hbox3);

    //Alignment
    descrpTextArea.setPrefColumnCount(40);

    hbox1.setSpacing(49);
    hbox2.setSpacing(10);
    hbox3.setSpacing(10);
    vbox1.setSpacing(10);

    hbox1.setPadding(new Insets(20, 20,10,10));
    hbox2.setPadding(new Insets(0, 20,10,10));
    hbox3.setPadding(new Insets(0,30,10,0));
    hbox3.setAlignment(Pos.CENTER_RIGHT);
    notifyLabel.setAlignment(Pos.CENTER_LEFT);
    notifyLabel.setMargin(saveButton, new Insets(0,0,0,200));

    //Events

    creatingMode.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        FlashcardsSet set = new FlashcardsSet();
        FlashcardsStatistics statFlashCards = new FlashcardsStatistics();
      }
    });

    learningMode.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        Stage secondStage = new Stage();
        secondStage.setTitle("Learning Mode");
        secondStage.setScene(learningMode());
        secondStage.show();
      }
    });

    statisticsMode.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        if (statFlashCards.getList().size() == 0) {
          notifyRef.get().setText("No suggestions in the list - Lets learn some flashcards!");
        } else {
          Stage thirdStage = new Stage();
          thirdStage.setTitle("Learning Suggestions");
          thirdStage.setScene(statisticsMode());
          thirdStage.show();
        }
      }
    });

    saveButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        if (termRef.get().getText().equals("") && descrpRef.get().getText().equals("")) {
          notifyRef.get().setText("Please enter term and description of the flashcard!");
        } else {
          flashcardsSet.addFlashcard(
              new Flashcard(termRef.get().getText(), descrpRef.get().getText()));
            notifyRef.get().setText("");
        }
        termRef.get().clear();
        descrpRef.get().clear();
      }
    });

    clearButton.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent arg0) {
        termRef.get().clear();
        descrpRef.get().clear();
      }
    });

    Scene scene = new Scene(pane);
    pane.setStyle("-fx-background-color: lightsteelblue; -fx-font-family: 'Helvetica'; "
        + "-fx-font-size: 14px;");

    stage.setTitle("Flashcards");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * This method creates the learning mode,
   * where the user can switch between the flashcards.
   * @return  The scene of the learning mode
   */
  public Scene learningMode() {

    //Controls
    Button showButton = new Button("Show");
    final AtomicReference<Button> showRef = new AtomicReference<Button>(showButton);
    showButton.setVisible(false);

    Button rightButton = new Button("Right");
    final AtomicReference<Button> rightRef = new AtomicReference<Button>(rightButton);
    rightButton.setMinWidth(75);
    rightButton.setVisible(false);

    final Button wrongButton = new Button("Wrong");
    final AtomicReference<Button> wrongRef = new AtomicReference<Button>(wrongButton);
    wrongButton.setMinWidth(75);
    wrongButton.setVisible(false);

    Label term = new Label();
    term.setStyle("-fx-font-weight: bold;");
    final AtomicReference<Label> termRef = new AtomicReference<Label>(term);
    term.setId("termStyle");

    Label description = new Label();
    final AtomicReference<Label> descrpRef = new AtomicReference<Label>(description);

    if (flashcardsSet.getSize() == 0) {
      term.setText("No flashcards in the set");

    } else {
      term.setText(flashcardsSet.getFlashcard(0).getTerm());
      description.setText("Press the Show-button to see the description");
      showButton.setVisible(true);
    }

    //Layout
    VBox vbox = new VBox(term, description);
    HBox hbox = new HBox(rightButton, wrongButton, showButton);
    BorderPane pane = new BorderPane(vbox);
    pane.setBottom(hbox);

    //Alignment
    vbox.setSpacing(20);
    vbox.setPadding(new Insets(10));
    vbox.setPrefSize(500, 250);
    hbox.setSpacing(10);
    hbox.setAlignment(Pos.CENTER_RIGHT);
    hbox.setPadding(new Insets(10,30,10,10));

    //Events

    final AtomicReference<Integer> indexRef = new AtomicReference<>(flashcardsSet.getSize());
    final AtomicReference<Integer> counterRef = new AtomicReference<>(1);

    class StatEvent implements EventHandler<ActionEvent> {
      @Override
      public void handle(ActionEvent arg0) {
        int counter = counterRef.get();
        int index = indexRef.get();

        if (index == 0 || counter > (index - 1)) {
          if (wrongRef.get().isArmed()) {
            statFlashCards.addFlashcard(
                new Flashcard(termRef.get().getText(), descrpRef.get().getText()));
          }
          termRef.get().setText("No flashcards in the set");
          descrpRef.get().setText("");
          rightRef.get().setVisible(false);
          wrongRef.get().setVisible(false);
        } else if (counter <= (index - 1)) {
          if (wrongRef.get().isArmed()) {
            statFlashCards.addFlashcard(
                new Flashcard(termRef.get().getText(), descrpRef.get().getText()));
          }
          termRef.get().setText(flashcardsSet.getFlashcard(counter).getTerm());
          descrpRef.get().setText("Press the Show-button to see the description");
          showRef.get().setVisible(true);
          rightRef.get().setVisible(false);
          wrongRef.get().setVisible(false);
          counter++;
          counterRef.set(counter);
        }
      }
    }

    EventHandler<ActionEvent> myStatEvent = new StatEvent();

    rightButton.addEventHandler(ActionEvent.ACTION, myStatEvent);
    wrongButton.addEventHandler(ActionEvent.ACTION, myStatEvent);

    showButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        int counter = counterRef.get();
        descrpRef.get().setText(flashcardsSet.getFlashcard(counter - 1).getDescription());
        showRef.get().setVisible(false);
        rightRef.get().setVisible(true);
        wrongRef.get().setVisible(true);
      }
    });

    Scene scene = new Scene(pane);
    pane.setStyle("-fx-background-color: lightsteelblue; -fx-font-family: 'Helvetica'; "
        + "-fx-font-size: 14px;");

    return scene;
  }

  /**
   * This method creates the flashcards-statistics-mode,
   * where the user can go through the flashcards, which he had the most difficulties with.
   * @return  The scene of the statistics mode
   *
   */
  public Scene statisticsMode() {

    ListView<Flashcard> listDifficultCards = new ListView<Flashcard>();
    listDifficultCards.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    ArrayList<Flashcard> list = new ArrayList<Flashcard>();
    list = statFlashCards.showStatistics();

    for (int i = 0; i < list.size(); i++) {
      listDifficultCards.getItems().add(list.get(i));
    }

    VBox pane = new VBox(listDifficultCards);

    Scene scene = new Scene(pane);
    pane.setStyle("-fx-background-color: lightsteelblue; -fx-font-family: 'Helvetica'; "
        + "-fx-font-size: 14px;");
    return scene;
  }

  /**
   * The main method, which calls the JavaFX launch method.
   * The launch method launches a standalone application.
   * @param args  The command line arguments passed to the application
   */
  public static void main(String[] args) {
    launch(args);
  }

}
