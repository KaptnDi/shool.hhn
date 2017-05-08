package flashcards;

import java.util.ArrayList;
import java.util.List;

/**
 * The Statistics interface analyses and evaluates users interaction and learning process
 * with the flashcards application.
 *
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
public interface Statistics {

  /**
   * Add a flashcard to the flashcard to the set.
   * @param flashcard  The flashcards, that will be added to the list difficult to learn flashcards
   */
  public void addFlashcard(Flashcard flashcard);

  /**
   * Get the specified list of flashcards.
   * @return  The specified list of flashcards
   */
  public ArrayList<Flashcard> getList();

  /**
   * This method calculates and returns the statistic of a user activity
   * with the Flashcards application.
   * @return  The calculated result in a list.
   */
  public ArrayList<Flashcard> showStatistics();

}
