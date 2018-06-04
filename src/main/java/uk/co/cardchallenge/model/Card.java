package uk.co.cardchallenge.model;

public class Card {

    private final String card;
    private final Suit suite;

    private String imagePath;

    public Card(String card, Suit suite) {
        this.card = card;
        this.suite = suite;
        this.imagePath = suite+"/"+card+".png";
    }

    public String getImagePath() { return imagePath; }

    @Override
    public String toString() {
        return "Card{" +
                "card='" + card + '\'' +
                ", suite=" + suite +
                '}';
    }
}
