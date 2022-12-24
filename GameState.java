public class GameState {
    private Cards[] board;
    private Cards[] playerHand;
    private Cards[] computerHand;
    private int playerScore;
    private int computerScore;
    private int boardSize;
    private int playerHandSize;
    private int computerHandSize;

    public GameState(Cards[] playerHand, Cards[] computerHand, int playerScore, int computerScore, int boardSize, int playerHandSize, int computerHandSize) {
        this.board = new Cards[boardSize];
        this.playerHand = playerHand;
        this.computerHand = computerHand;
        this.playerScore = playerScore;
        this.computerScore = computerScore;
        this.boardSize = boardSize;
        this.playerHandSize = playerHandSize;
        this.computerHandSize = computerHandSize;
    }

    public void placeCard(Cards card) {
        board[boardSize] = card;
        boardSize++;
    }

    public boolean canTakeCards(Cards hand, int handSize) {
        if (boardSize == 0) {
            return false;
        }
        Cards topCard = board[boardSize - 1];
        for (int i = 0; i < handSize; i++) {
            if (hand.getRank().equals(topCard.getRank()) || hand.getRank().equals("Jack")) {
                return true;
            }
        }
        return false;
    }

    public void takeCards(Cards[] hand, int handSize) {
        for (int i = 0; i < boardSize; i++) {
            hand[handSize] = board[i];
            handSize++;
        }
    }

    public void scoreGame(Player player, Player computer, Cards[] playerCards, Cards[] computerCards) {
        if (boardSize == 1) {
            Cards topCard = board[0];
            for (int i=0; i<playerCards.length ; i++) {
                Cards c = playerCards[i];
                if (c.getRank().equals(topCard.getRank())) {
                    playerScore += 10;
                    System.out.println("Pişti made by: " + player.getName() + "!");
                    break;
                }
            }
            for (int i=0; i<computerCards.length; i++) {
                Cards c = computerCards[i];
                if (c.getRank().equals(topCard.getRank())) {
                    computerScore += 10;
                    break;
                }
            }
        }
        if (playerHandSize > computerHandSize) {
            playerScore += 3;
        } else if (computerHandSize > playerHandSize) {
            computerScore += 3;
        }
        for (int i = 0; i < playerHandSize; i++) {
            Cards c = playerHand[i];
            if (c.getRank().equals("10") && c.getSuit().equals("Diamonds")) {
                playerScore += 3;
            } else if (c.getRank().equals("2") && c.getSuit().equals("Clubs")) {
                playerScore += 2;
            } else {
                playerScore += 1;
            }
        }
        for (int i = 0; i < computerHandSize; i++) {
            Cards c = computerHand[i];
            if (c.getRank().equals("10") && c.getSuit().equals("Diamonds")) {
                computerScore += 3;
            } else if (c.getRank().equals("2") && c.getSuit().equals("Clubs")) {
                computerScore += 2;
            } else {
                computerScore += 1;
            }
        }
    }

    public boolean isGameOver() {
        if(playerHandSize+computerHandSize==52) 
            return true;
        return false;
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