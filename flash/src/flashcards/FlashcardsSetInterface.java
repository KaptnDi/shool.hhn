package flashcards;

import java.util.ArrayList;

/**
 * The FlashcardsSetInterface contains methods to create new flashcards-set,
 * insert and remove flashcards from a set, modify the flashcards in a set,
 * iterate through a set.
 *
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
public interface FlashcardsSetInterface {

  /**
   * Get the flashcards-set.
   * @return  A flashcards-set
   */
  public ArrayList<Flashcard> getFlashcardsSet();

  /**
   * Get a flashcard from the flashcards-set at a specified position.
   * @param index  The position of a flashcard in the set
   * @return  A flashcard from the flashcards-set at a specified position
   */
  public Flashcard getFlashcard(int index);

  /**
   * Add a flashcard to the flashcards-set.
   * @param flashcard  A flashcard, that will be added to the flashcards-set
   */
  public void addFlashcard(Flashcard flashcard);

  /**
   * Remove a flashcard from a flashcards-set.
   * @param flashcard  A flashcard that will be removed from the flashcards-set
   */
  public void removeFlashcard(Flashcard flashcard);

  /**
   * Edit the term of a flashcard.
   * @param flashcard  The flashcard, which term should be edited
   * @param newTerm  The new term of the flashcard
   */
  public void editFlashcardsTerm(Flashcard flashcard, String newTerm);

  /**
   * Edit the description of a flashcard.
   * @param flashcard  The flashcard, which description should be edited
   * @param newDescription  The new description of the flashcard
   */
  public void editFlashcardsDescription(Flashcard flashcard, String newDescription);

  /**
   * Get all flashcards in a set.
   */
  public void getAllFlashcards();
}
