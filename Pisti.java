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
        int playerPocketSize = 0;
        int computerPocketSize = 0;
        String lastTrickWinner = "";
        GameState gameState = new GameState(playerPocket, computerPocket, playerScore, computerScore, boardSize, playerPocketSize, computerPocketSize);
        deck.Deal(4, player, computer, board);
        
        while(!gameState.isGameOver()) {
        playerCards = player.getHand();
        computerCards = computer.getHand();
        System.out.println("The top card on the floor is: " + board.getTopCard());

            if(playerCards.length>0) {
                Cards c = newGame.getPlayerCard();
                player.removeCardFromHand(c);
                board.addCardToBoard(c);
                if(gameState.canTakeCards(c, playerCards.length)){
                    board.removeCardFromBoard(boardSize);
                    gameState.takeCards(playerPocket, playerPocketSize);
                    gameState.scoreGame(player, computer, playerCards, computerCards);
                    lastTrickWinner = "Player";
                    playerCards = player.getHand();
                }
            }

            if(computerCards.length>0) {
                Cards d = newGame.selectComputerCard();
                computer.removeCardFromHand(d);
                board.addCardToBoard(d);
                if(gameState.canTakeCards(d, computerCards.length)){
                    board.removeCardFromBoard(boardSize);
                    gameState.takeCards(computerPocket, computerPocketSize);
                    gameState.scoreGame(player, computer, playerCards, computerCards);
                    lastTrickWinner = "Computer";
                    computerCards = computer.getHand();
                }
            }
        }

        if(gameState.isGameOver()) {
            if(boardSize>0) {
                if(lastTrickWinner.equals("Player")) {
                    gameState.takeCards(playerPocket, playerPocketSize);
                    playerPocketSize = playerPocket.length;
                    board.removeCardFromBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerCards, computerCards);
                    playerScore = gameState.getPlayerScore();
                    playerCards = player.getHand();
                }
                if(lastTrickWinner.equals("Computer")) {
                    gameState.takeCards(computerPocket, computerPocketSize);
                    computerPocketSize = computerPocket.length;
                    board.removeCardFromBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerCards, computerCards);
                    computerScore = gameState.getComputerScore();
                    computerCards = computer.getHand();
                }
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