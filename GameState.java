public class GameState {
    private Cards[] board;
    private Cards[] playerPocket;
    private Cards[] computerPocket;
    private int playerScore;
    private int computerScore;
    private int boardSize;
    

    public GameState(Cards[] playerPocket, Cards[] computerPocket, int playerScore, int computerScore, int boardSize, Board board) {
        this.board = board.getBoard();
        this.playerPocket = playerPocket;
        this.computerPocket = computerPocket;
        this.playerScore = playerScore;
        this.computerScore = computerScore;
        this.boardSize = boardSize;
    }

    public boolean canTakeCards(Cards c, int boardSize) {
        if (boardSize == 0) {
            return false;
        }
        Cards topCard = board[boardSize - 1];
        if (c.getRank().equals(topCard.getRank())){
            return true;
        }
        if (c.getRank().equals("Jack")) {
            return true;
        }
        return false;
    }

    public void takeCards(Cards[] Userpocket, int boardSize) {
        Cards[] newDeck = new Cards[boardSize];
        for (int i=0; i<boardSize; i++) {
            newDeck[i] = this.board[i];
            boardSize--;
        }
        Userpocket = newDeck;
        System.out.println("The floor is now empty.");
    }

    public void PlayerPişti(Player player, Cards c) {
        if (boardSize == 1) {
            Cards topCard = board[0];
            if (c.getRank().equals(topCard.getRank())) {
                playerScore += 10;
                System.out.println("Pişti made by: " + player.getName() + "!");
            }
        }
    }

    public void ComputerPişti(Cards d) {
        if (boardSize == 1) {
            Cards topCard = board[0];
            if (d.getRank().equals(topCard.getRank())) {
                computerScore += 10;
                System.out.println("Pişti made by: computer!");
            }
        }
    }

    public void scoreGame(Player player, Player computer, Cards[] playerPocket, Cards[] computerPocket) {
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