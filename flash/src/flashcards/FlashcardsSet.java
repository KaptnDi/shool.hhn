package flashcards;

import java.util.ArrayList;

/**
 * This class represents a set of flashcards.
 * @author Marina Schubernel, Dimitri Dobler
 * @version 30th March 2017
 */

public class FlashcardsSet implements FlashcardsSetInterface {

  /**An ArrayList that contains the set of flashcards.*/
  private ArrayList<Flashcard> flashcardsSet = new ArrayList<Flashcard>();

  /**An ArrayList that contains all existing flashcards-sets.*/
  private static ArrayList<FlashcardsSet> setList = new ArrayList<FlashcardsSet>();

  /**
   * The constructor to create a flashcards-set.
   */
  public FlashcardsSet() {
    setList.add(this);
  }

  /**
   * Get the flashcards-set.
   * @return  A flashcards-set
   */
  public ArrayList<Flashcard> getFlashcardsSet() {
    return flashcardsSet;
  }

  /**
   * Get a flashcard from the flashcards-set at a specified position.
   * @param index  The position of a flashcard in the set
   * @return  A flashcard from the flashcards-set at a specified position
   */
  public Flashcard getFlashcard(int index) {
    return flashcardsSet.get(index);
  }

  /**
   * Add a flashcard to the flashcards-set.
   * @param flashcard  A flashcard, that will be added to the flashcards-set
   */
  public void addFlashcard(Flashcard flashcard) {
    flashcardsSet.add(flashcard);
  }

  /**
   * Get the size of a flashcards-set.
   * @return  The size of a flashcards-set
   */
  public int getSize() {
    return flashcardsSet.size();
  }

  /**
   * Remove a flashcard from a flashcards-set.
   * @param flashcard  A flashcard that will be removed from the flashcards-set
   */
  public void removeFlashcard(Flashcard flashcard) {
    flashcardsSet.remove(flashcard);
  }

  /**
   * Edit the term of a flashcard.
   * @param flashcard  The flashcard, which term should be edited
   * @param newTerm  The new term of the flashcard
   */
  public void editFlashcardsTerm(Flashcard flashcard, String newTerm) {
    for (int i = 0; i < flashcardsSet.size(); i++) {
      if (flashcardsSet.get(i).equals(flashcard)) {
        flashcardsSet.get(i).setTerm(newTerm);
      }
    }
  }

  /**
   * Edit the description of a flashcard.
   * @param flashcard  The flashcard, which description should be edited
   * @param newDescription  The new description of the flashcard
   */
  public void editFlashcardsDescription(Flashcard flashcard, String newDescription) {
    for (int i = 0; i < flashcardsSet.size(); i++) {
      if (flashcardsSet.get(i).equals(flashcard)) {
        flashcardsSet.get(i).setDescription(newDescription);
      }
    }
  }

  /**
   * Get the latest created set of flashcards.
   * @return  The latest created set of flashcards
   */
  public static FlashcardsSet getLatestSets() {
    return setList.get(setList.size() - 1);
  }

  /**
   * Get a flashcard from the latest created set of flashcards.
   * @param index  The position of a flashcard in the set
   * @return  A flashcard from the latest created flashcards-set at a specified position
   */
  public Flashcard getFlashcardFromLatestSet(int index) {
    return getLatestSets().getFlashcard(index);
  }

  /**
   * Get all flashcards in a set.
   */
  public void getAllFlashcards() {
    for (int i = 0; i < flashcardsSet.size(); i++) {
      flashcardsSet.get(i).toString();
    }
  }

}
