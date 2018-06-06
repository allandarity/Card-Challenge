package uk.co.cardchallenge.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeckTest {

    Deck deck;

    @Before
    public void setUp() throws Exception {
        deck = new Deck();
    }

    @After
    public void tearDown() throws Exception {
        deck = null;
    }

    @Test
    public void shuffleDeck() {
        Card headBefore = deck.getHeadCard();
        deck.shuffleDeck();
        Card headAfter = deck.getHeadCard();
        System.out.println(headBefore + " - " + headAfter);
        try {
            Assert.assertNotEquals(headBefore, headAfter);
        } catch (AssertionError e) {
            Assert.fail("Deck not shuffled");
        }
    }

    @Test
    public void drawCard() {
        int deckSizeBefore = deck.cardsLeft();
        Card card = deck.drawCard();
        int deckSizeAfter = deck.cardsLeft();
        System.out.println(deckSizeBefore + " - " + deckSizeAfter);
        try {
            Assert.assertFalse(deck.deckContains(card));
        } catch (Exception e) {
            Assert.fail("Deck still contains the card");
        }
        try {
            Assert.assertNotSame(deckSizeBefore, deckSizeAfter);
        } catch (Exception e) {
            Assert.fail("Deck size didn't go down");
        }
    }

    @Test
    public void drawMultiple() {
        int deckSizeBefore = deck.cardsLeft();
        List<Card> container = deck.drawMultiple(10);
        int deckSizeAfter = deck.cardsLeft();
        Assert.assertTrue((deckSizeBefore - 10) == deckSizeAfter);
    }

    @Test
    public void drawMultipleJustOverTheLimit() {
        int deckSizeBefore = deck.cardsLeft();
        List<Card> container = deck.drawMultiple(53);
        int deckSizeAfter = deck.cardsLeft();
        System.out.println(deckSizeBefore);
        System.out.println(deckSizeAfter);
        Assert.assertTrue(deckSizeBefore == deckSizeAfter);
    }

    @Test
    public void drawMultipleOnTheLimit() {
        int deckSizeBefore = deck.cardsLeft();
        List<Card> container = deck.drawMultiple(52);
        int deckSizeAfter = deck.cardsLeft();
        System.out.println(deckSizeBefore);
        System.out.println(deckSizeAfter);
        Assert.assertTrue(deckSizeAfter == 0);
        Assert.assertTrue(container.size() == 52);
    }
    
    @Test
    public void getHeadCard() {
        Card card = deck.getHeadCard();
        try {
            Assert.assertNotNull(card);
        } catch (Exception e) {
            Assert.fail("Head card is null");
        }
    }

    @Test
    public void cardsLeft() {
        try {
            Assert.assertTrue(deck.cardsLeft() >= 0);
        } catch (Exception e) {
            Assert.fail("Deck was never filled out");
        }
        int deckSizeAfter = deck.cardsLeft();
        System.out.println(deckSizeAfter);
        Assert.assertTrue(deckSizeAfter == 52);
    }
}