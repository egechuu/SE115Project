public class Pisti {
    public static void main(String[] args) {
        Player player = new Player();
        Player computer = new Player();
        Player[] players = {player, computer};
        Board board = new Board();
        Game newGame = new Game(players, board);
        Deck deck =  new Deck();
        Cards[] playerCards = new Cards[0];
        Cards[] computerCards = new Cards[0];
        Cards[] playerPocket = new Cards[0];
        Cards[] computerPocket = new Cards[0];
        int playerScore = 0;
        int computerScore = 0;
        int boardSize = board.getNumOfCards();
        int playerCardsSize = 0;
        int computerCardsSize = 0;
        int playerPocketSize = 0;
        int computerPocketSize = 0;
        GameState gameState = new GameState(playerPocket, computerPocket, playerScore, computerScore, boardSize, playerPocketSize, computerPocketSize);
        boolean gameOver = gameState.isGameOver();
        deck.Deal(4, player, computer, board);

        while(gameOver==false) {
            System.out.println("The card on top is: " + board.getTopCard());
            if(playerCards!=null) {
                Cards c = newGame.getPlayerCard();
                player.removeCardFromHand(c);
                board.addCardToBoard(c);
                if(gameState.canTakeCards(c, playerCardsSize)==true){
                    board.removeCardFromBoard(boardSize);
                    gameState.takeCards(playerPocket, playerPocketSize);
                    gameState.scoreGame(player, computer, playerCards, computerCards);
                }
            }
            if(computerCards!=null) {
                Cards d = newGame.selectComputerCard();
                computer.removeCardFromHand(d);
                board.addCardToBoard(d);
                if(gameState.canTakeCards(d, computerCardsSize)==true){
                    computer.removeCardFromHand(d);
                    board.removeCardFromBoard(boardSize);
                    gameState.takeCards(computerPocket, computerPocketSize);
                    gameState.scoreGame(player, computer, playerCards, computerCards);
                }
            }

            if(playerCards==null&&computerCards==null) {
                deck.Deal(4, player, computer, board);
            }
        }
        if(gameOver) {
            if(boardSize>0) {
                board.removeCardFromBoard(boardSize);
            }
            newGame.closeScanner();
            System.out.println("The game is over!");
            playerScore = gameState.getPlayerScore();
            computerScore = gameState.getComputerScore();
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            System.out.println(gameState.getWinner());
        }
    }
}