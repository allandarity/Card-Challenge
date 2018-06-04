package uk.co.cardchallenge.ui;

import uk.co.cardchallenge.model.Card;
import uk.co.cardchallenge.model.Deck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainFrame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JLabel imageLabel;
    private JButton drawMultipleButton;
    private JButton newButton;

    Deck deck = new Deck();
    Icon icon;

    public MainFrame() {
        this.setTitle("Card Drawer");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Q.jpg")));
        imageLabel.setIcon(icon);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        drawMultipleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawMultiple();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newDeck();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);
    }

    public void addText(List<String> text) {
        text.forEach(s -> textArea1.append(s+"\n"));
    }

    private void onOK() {
        if (deck.cardsLeft() > 0) {
            Card card = deck.drawCard();
            addText(Arrays.asList(card.toString() + " is drawn", deck.cardsLeft() + " remain"));
            setImage(card);
        } else {
            addText(Collections.singletonList("There are no cards left"));
        }
    }

    private void onCancel() {
        deck.shuffleDeck();
        addText(Arrays.asList("Cards have been shuffled", "Head card is - " + deck.getHeadCard(), deck.cardsLeft()+ " remain"));
    }

    private void drawMultiple() {
        try {
            int no = Integer.valueOf(JOptionPane.showInputDialog(this, "Enter Number of Cards"));
            if(deck.cardsLeft() > 0) {
                List<Card> list = deck.drawMultiple(no);
                list.forEach(card -> {
                    addText(Collections.singletonList(card.toString() + " is drawn"));
                });
                setImage(list.get(list.size()-1));
                addText(Collections.singletonList(deck.cardsLeft() + " remain"));
            } else {
                addText(Collections.singletonList("There are no cards left"));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "MUST BE A NUMBER");
        }
    }

    public void setImage(Card card) {
        try {
            imageLabel.setIcon(new ImageIcon(
                    new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().
                            getResource(card.getImagePath())))
                            .getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        } catch (Exception e) {
            icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Q.jpg")));
            imageLabel.setIcon(icon);
        }
    }

    private void newDeck() {
        this.deck = new Deck();
        addText(Arrays.asList("--------", "New Deck", "---------", deck.cardsLeft() + " remain"));
    }
}
