package flashcards;

import java.util.ArrayList;

/** The FlashcardsService manages all flashcards-sets.
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
public class FlashcardsService {

  /**An ArrayList that contains all existing flashcards-sets.*/
  private static ArrayList<FlashcardsSet> setList = new ArrayList<FlashcardsSet>();

  public FlashcardsService(){
  }

  /**
   * Gets the set list with all flashcards sets.
   * @return  The set list with all flashcards sets
   */
  public ArrayList<FlashcardsSet> getSetList() {
    return setList;
  }
}
