package Day04;

public class Card {

    public int id;

    public int copies;

    public int matchingNumbers;

    public Card(int id, int copies, int matchingNumbers) {
        this.id = id;
        this.copies = copies;
        this.matchingNumbers = matchingNumbers;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchingNumbers() {
        return matchingNumbers;
    }

    public void setMatchingNumbers(int matchingNumbers) {
        this.matchingNumbers = matchingNumbers;
    }
}
