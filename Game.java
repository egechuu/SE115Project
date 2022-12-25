import java.util.Scanner;

public class Game {
    private Player[] players;
    private Board board;
    private Scanner sc;

    public Game(Player[] players, Board board) {
        this.players = players;
        this.board = board;
        this.sc = new Scanner(System.in);
    }

    public Cards getPlayerCard() {
        Cards[] hand = players[0].getHand();
        System.out.println("Your hand: ");
        for(int i=0; i<hand.length; i++) 
            System.out.println(i+1 + ": " + hand[i].toString());
        while (true) {
            System.out.println("Choose a card: ");
            int index = sc.nextInt() - 1;
            if (index >= 0 && index < hand.length && hand[index] != null) {
                return hand[index];
            }
            System.out.println("Please choose a valid card index.");
        }
    }

    public Cards selectComputerCard() {
        Cards topCard = board.getTopCard();
        Cards[] hand = players[1].getHand();
        for(Cards card : hand) {
            if(card.getRank().equals("Jack")) {
                System.out.println("The computer played " + card);
                return card;
            }
            if(card.getSuit().equals(topCard.getSuit()) || card.getRank().equals(topCard.getRank())) {
                System.out.println("The computer played " + card);
                return card;
            } 
        }

        System.out.println("The computer played " + hand[0]);
        return hand[0];
            
    }

    public void closeScanner() {
        sc.close();
    }
}
