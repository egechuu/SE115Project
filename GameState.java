public class GameState {
    private Cards[] board;
    private Cards[] playerPocket;
    private Cards[] computerPocket;
    private int playerScore;
    private int computerScore;
    
    public GameState(Cards[] playerPocket, Cards[] computerPocket, int playerScore, int computerScore, Board board) {
        this.board = board.getBoard();
        this.playerPocket = playerPocket;
        this.computerPocket = computerPocket;
        this.playerScore = playerScore;
        this.computerScore = computerScore;
    }

    public boolean canTakeCards(Cards c, Board h, Player player, int piştiCounter) {
        Cards topCard = h.getTopCard(); 
        if (topCard==null) {
            return false;
        }
        if (h.getNumOfCards()==1 && c.getRank().equals(topCard.getRank())) {
            piştiCounter++;
            System.out.println("Pişti made by: " + player);
            return true;
        }
        if (topCard!=null && h.getNumOfCards()!=1 && c.getRank().equals(topCard.getRank())){
            System.out.println("The same rank played.");
            return true;
        }
        if (topCard!=null && c.getRank().equals("Jack")) {
            System.out.println("Jack played.");
            return true;
        }
        
        return false;
    }

    public Cards[] takeCards(Cards[] pocket, int number) {
        Cards[] newDeck = new Cards[board.length];
        for (int i=0; i<board.length; i++) {
            newDeck[i] = this.board[i];
        }
        pocket = newDeck;
        System.out.println("Taking all cards.");
        return pocket;
    }

    public void scoreGame(Player player, Player computer, Cards[] playerPocket, Cards[] computerPocket, int playerPişti, int computerPişti) {
        for(int i=0; i<playerPişti; i++) {
            playerScore += 10;
        }
        for(int i=0; i<computerPişti; i++) {
            computerScore += 10;
        }
        if (playerPocket.length > computerPocket.length) 
            playerScore += 3;
        else if (computerPocket.length > playerPocket.length) 
            computerScore += 3;
        for (int i = 0; i < playerPocket.length; i++) {
            Cards c = playerPocket[i];
            if (c.getRank().equals("10") && c.getSuit().equals("Diamonds")) {
                playerScore += 3;
            } else if (c.getRank().equals("2") && c.getSuit().equals("Clubs")) {
                playerScore += 2;
            } else {
                playerScore += 1;
            }
        }
        for (int i = 0; i < computerPocket.length; i++) {
            Cards c = computerPocket[i];
            if (c.getRank().equals("10") && c.getSuit().equals("Diamonds")) {
                computerScore += 3;
            } else if (c.getRank().equals("2") && c.getSuit().equals("Clubs")) {
                computerScore += 2;
            } else {
                computerScore += 1;
            }
        }
    }

    public boolean isGameOver(Deck deck, int playerCardsLength, int computerCardsLength) {
        if(deck.isEmpty() && playerCardsLength==0 && computerCardsLength==0) 
            return true;
        return false;
    }

    public Cards[] getPlayerPocket() {
        return playerPocket;
    }

    public Cards[] getComputerPocket() {
        return computerPocket;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public String getWinner() {
        if(playerScore<computerScore)
            return "The computer won!";
        if(playerScore>computerScore)
            return "You won!";
        else
            return "It's a tie!";
    }
}