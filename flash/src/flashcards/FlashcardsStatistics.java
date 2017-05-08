package flashcards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class defines the flashcards, that were difficult to learn for the user.
 * This are the flashcards, which were marked by the user as "wrong" at most. The user than,
 * can go through this flashcards and learn these again.
 *
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
public class FlashcardsStatistics implements Statistics {

  /** The list were the most difficult to learn flashcards are stored.*/
  private ArrayList<Flashcard> difficultCards;

  /**
   * The constructor of the flashcards-statistics class.
   */
  public FlashcardsStatistics() {
    difficultCards = new ArrayList<Flashcard>();
  }

  /**
   * This method get the list with the most difficult to learn flashcards.
   * @return  The list with the most difficult to learn flashcards
   */
  public ArrayList<Flashcard> getList() {
    return difficultCards;
  }

  /**
   * Add a flashcard to the list difficult to learn flashcards.
   * @param flashcard  The flashcards, that will be added to the list difficult to learn flashcards
   */
  public void addFlashcard(Flashcard flashcard) {
    difficultCards.add(flashcard);
  }

  /**
   * This method calculates the flashcards, that were difficult to learn for the user.
   * If this set of difficult flashcards contains only 10 cards, than all the cards will be shown.
   * If the set contains more than 10 flashcards, than it will be calculated,
   * which cards are most often in the set - this are the most difficult ones,
   * as they where marked as wrong the most.
   */
  public ArrayList<Flashcard> showStatistics() {
    TreeMap<Flashcard, Integer> mapCards = new TreeMap<Flashcard, Integer>();

    if (difficultCards.size() <= 10) {
      return difficultCards;
    } else {
      for (int i = 0; i < difficultCards.size(); i++) {
        if (mapCards.get(difficultCards.get(i)) == null) {
          mapCards.put(difficultCards.get(i), 1);
        } else {
          int value = mapCards.get(difficultCards.get(i));
          mapCards.put(difficultCards.get(i), value++);
        }
      }
      difficultCards.clear();
      for (Map.Entry<Flashcard, Integer> entry : sortByValues(mapCards)) {
        if (difficultCards.size() <= 10) {
          difficultCards.add(entry.getKey());
        } else {
          break;
        }
      }
      return difficultCards;
    }
  }

  /**
   * This method sorts a map by its values.
   * @param map  Map that should be sorted by values
   * @param <K>  The key of the map
   * @param <V>  The value of the map
   * @return  Map entries sorted by value
   */
  public <K,V extends Comparable<? super V>> SortedSet<Map.Entry<K,V>> sortByValues(Map<K,V> map) {
    SortedSet<Map.Entry<K,V>> sortEntr = new TreeSet<Map.Entry<K,V>>(
        new Comparator<Map.Entry<K,V>>() {
          @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
            int result = e1.getValue().compareTo(e2.getValue());
            if (result != 0) {
              return result;
            } else {
              return result = 1;
            }
          }
        }
    );
    sortEntr.addAll(map.entrySet());
    return sortEntr;
  }

}
