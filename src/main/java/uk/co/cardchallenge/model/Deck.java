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
        EnumSet.allOf(Suit.class).forEach(suite -> {
            defaultCard.forEach(card -> {
                deckContainer.add(new Card(card, suite));
            });
        });
        shuffleDeck();
        System.out.println(deckContainer.size());
        System.out.println(drawCard());
        System.out.println(deckContainer.size());
    }

    public void shuffleDeck() {
        Collections.shuffle(deckContainer);
    }

    public Card drawCard() {
        if(cardsLeft() <= 0) {
            return new Card("There are no cards left", null);
        }
        Random r = new Random();
        Card chosen = deckContainer.get(r.nextInt(cardsLeft()));
        deckContainer.remove(chosen);
        return chosen;
    }

    public List<Card> drawMultiple(int no) {
        List<Card> container = new LinkedList<>();
        for(int i = 0; i < no; i++) {
            container.add(drawCard());
        }
        return container;
    }

    public Card getHeadCard() {
        return deckContainer.get(0);
    }

    public int cardsLeft() {
        return deckContainer.size();
    }

}
