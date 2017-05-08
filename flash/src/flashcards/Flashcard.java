package flashcards;

/**
 * This class represents a flashcard.
 * @author Marina Schubernel, Dimitri Dobler
 * @version 04-15-2017
 */
public class Flashcard {

  /**The term of the flashcard.*/
  private String term;
  /**The description of the flashcard.*/
  private String description;

  /**
   * The constructor to create a flashcard.
   * @param term  The term of the flashcard.
   * @param description  The description of the flashcard
   */
  public Flashcard(String term, String description) {
    this.term = term;
    this.description = description;
  }

  /**
   * Getter method for the term of the flashcard.
   * @return  The term of the flashcard
   */
  public String getTerm() {
    return term;
  }

  /**
   * Setter method for the term of the flashcard.
   */
  public void setTerm(String term) {
    this.term = term;
  }

  /**
   * Getter method for the description of the flashcard.
   * @return  The description of the flashcard
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setter method for the term of the flashcard.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * A method that shows the term and description of the flashcard.
   * @return  The term and description of the flashcard
   */
  public String toString() {
    return term + "     " + description;
  }

}
