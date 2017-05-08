package flashcards;

/**
 * This UseCase demonstrates some possibilities of the Flashcards Application.
 *
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
public class UseCase {
  /**
   * The main method to invoke the methods for Use Case.
   * @param args  The possible arguments
   */
  public static void main(String[] args) {

    //You can test most of the possibilities of the flashcards application
    //with the graphical user interface...

    FlashcardsSetInterface flashcardsSet = new FlashcardsSet();
    Statistics stats = new FlashcardsStatistics();

    Flashcard one = new Flashcard("FirstTerm", "FirstDescrp");
    Flashcard two = new Flashcard("two", "two");
    Flashcard three = new Flashcard("three", "three");
    Flashcard four = new Flashcard("four", "four");
    Flashcard five = new Flashcard("five", "five");
    Flashcard six = new Flashcard("six", "six");
    Flashcard seven = new Flashcard("seven", "seven");
    Flashcard eight = new Flashcard("eight", "eight");
    Flashcard nine = new Flashcard("nine", "nine");
    Flashcard ten = new Flashcard("ten", "ten");
    Flashcard eleven = new Flashcard("eleven", "eleven");
    Flashcard twelve = new Flashcard("twelve", "twelve");

    flashcardsSet.addFlashcard(one);
    flashcardsSet.addFlashcard(two);
    flashcardsSet.addFlashcard(three);
    flashcardsSet.addFlashcard(four);
    flashcardsSet.addFlashcard(five);
    flashcardsSet.addFlashcard(six);
    flashcardsSet.addFlashcard(seven);
    flashcardsSet.addFlashcard(eight);
    flashcardsSet.addFlashcard(nine);
    flashcardsSet.addFlashcard(ten);
    flashcardsSet.addFlashcard(eleven);
    flashcardsSet.addFlashcard(twelve);

    System.out.println("Size: " + flashcardsSet.getFlashcardsSet().size());

    flashcardsSet.removeFlashcard(seven);
    System.out.println("Remove: " + flashcardsSet.getFlashcardsSet().size());

    flashcardsSet.editFlashcardsDescription(eight, "newEight");
    System.out.println("New Description: " + flashcardsSet.getFlashcard(6));

    /*
    Test statistics via user interface...

    stats.addFlashcard(one);
    stats.addFlashcard(two);
    stats.addFlashcard(three);
    stats.addFlashcard(four);
    stats.addFlashcard(five);
    stats.addFlashcard(six);
    stats.addFlashcard(seven);
    stats.addFlashcard(eight);
    stats.addFlashcard(nine);
    stats.addFlashcard(ten);
    stats.addFlashcard(eleven);
    stats.addFlashcard(twelve);
     */

  }
}
