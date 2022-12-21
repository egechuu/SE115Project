import java.util.Scanner;

public class Game {
    private Player[] players;
    private Board board;
    private int currentPlayer;
    private Scanner sc;

    public Game(Player[] players, Board board) {
        this.players = players;
        this.board = board;
        this.currentPlayer = 0;
        this.sc = new Scanner(System.in);
    }

    public void playCard() {
        Cards playerCard = getPlayerCard();
        board.addCardToBoard(playerCard);
        players[0].removeCardFromHand(playerCard);
        Cards computerCard = selectComputerCard();
        board.addCardToBoard(computerCard);
        currentPlayer = (currentPlayer+1) % players.length;
    }

    private Cards getPlayerCard() {
        System.out.println("Your hand: ");
        Cards[] hand = players[0].getHand();
        for(int i=0; i<hand.length; i++) 
            System.out.println(i+1 + ": " + hand[i]);
        System.out.println("Choose a card: ");
        return hand[sc.nextInt()-1];
    }

    private Cards selectComputerCard() {
        Cards topCard = board.getTopCard();
        Cards[] hand = players[1].getHand();

        for(Cards card : hand) {
            if(card.getRank().equals("Jack")) {
                return card;
            }
        }
        
        if(topCard.getRank().equals("Jack")) {
            return hand[hand.length-1];
        }

        for(Cards card : hand) {
            if(card.getSuit().equals(topCard.getSuit()) || card.getRank().equals(topCard.getRank())) 
                return card;
        }

        return hand[0];
    }

    public void closeScanner() {
        sc.close();
    }
}
