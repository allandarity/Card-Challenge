package uk.co.cardchallenge.model;

public class Card {

    private final String card;
    private final Suite suite;

    public Card(String card, Suite suite) {
        this.card = card;
        this.suite = suite;
    }

    @Override
    public String toString() {
        return "Card{" +
                "card='" + card + '\'' +
                ", suite=" + suite +
                '}';
    }
}
