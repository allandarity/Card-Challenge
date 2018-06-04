package uk.co.cardchallenge.model;

import java.util.*;

public class Deck {

    private List<Card> deckContainer;
    private List<String> defaultCard = new LinkedList<>(
            Arrays.asList("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"));

    public Deck() {
        this.deckContainer = new ArrayList<>();
        this.buildDeck();
    }

    private void buildDeck() {
        EnumSet.allOf(Suite.class).forEach(suite -> {
            defaultCard.forEach(card -> {
                deckContainer.add(new Card(card, suite));
            });
        });
        shuffleDeck();
        System.out.println(deckContainer.size());
        System.out.println(drawCard());
        System.out.println(drawCard());
        System.out.println(drawCard());
        System.out.println(drawCard());
        System.out.println(drawCard());
        System.out.println(drawCard());
        System.out.println(deckContainer.size());
    }

    private void shuffleDeck() {
        Collections.shuffle(deckContainer);
    }

    private Card drawCard() {
        Random r = new Random();
        Card chosen = deckContainer.get(r.nextInt(defaultCard.size()));
        deckContainer.remove(chosen);
        return chosen;
    }

    public int cardsLeft() {
        return deckContainer.size();
    }

}
