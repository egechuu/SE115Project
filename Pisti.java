public class Pisti {
    public static void main(String[] args) {
        //bismillahirahmanirrahim
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
        int playerPocketSize = 0;
        int computerPocketSize = 0;
        int playerPişti = 0;
        int computerPişti = 0;
        String lastTrickWinner = "";
        deck.Deal(4, player, computer, board);
        int boardSize = board.getNumOfCards();
        GameState gameState = new GameState(playerPocket, computerPocket, playerScore, computerScore, board);

        while(!gameState.isGameOver(deck, playerCards.length, computerCards.length)) {
            playerCards = player.getHand();
            computerCards = computer.getHand();
            board.getBoard();
            boardSize = board.getNumOfCards();
            deck.isEmpty();
            System.out.println("The top card on the floor is: " + board.getTopCard());

            if(gameState.isGameOver(deck, playerCards.length, computerCards.length))
                break;

            if(playerCards.length==0 && computerCards.length==0 && !deck.isEmpty()) {
                deck.Deal(4, player, computer, board);
                playerCards = player.getHand();
                computerCards = computer.getHand();
            }

            if(playerCards.length>0) {
                Cards c = newGame.getPlayerCard();
                if(gameState.canTakeCards(c, board, player, playerScore)){
                    player.removeCardFromHand(c);
                    board.addCardToBoard(c);
                    boardSize = board.getNumOfCards();
                    playerPocket = gameState.takeCards(playerPocket, boardSize); 
                    playerPocketSize = playerPocket.length;
                    board.emptyBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerPocket, computerPocket, playerPişti, computerPişti);
                    playerScore = gameState.getPlayerScore();
                    lastTrickWinner = "Player";
                    playerCards = player.getHand();
                } else {
                    player.removeCardFromHand(c);
                    board.addCardToBoard(c);
                    boardSize = board.getNumOfCards();
                }
            }

            if(computerCards.length>0) {
                Cards d = newGame.selectComputerCard();
                if(gameState.canTakeCards(d, board, computer, computerScore)) {
                    computer.removeCardFromHand(d);
                    board.addCardToBoard(d);
                    boardSize = board.getNumOfCards();
                    computerPocket = gameState.takeCards(computerPocket, boardSize);
                    computerPocketSize = computerPocket.length;
                    board.emptyBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerPocket, computerPocket, playerPişti, computerPişti);
                    computerScore = gameState.getComputerScore();
                    lastTrickWinner = "Computer";
                    computerCards = computer.getHand();
                } else {
                    computer.removeCardFromHand(d);
                    board.addCardToBoard(d);
                    boardSize = board.getNumOfCards();
                }
                
            }
        }

        if(gameState.isGameOver(deck, playerCards.length, computerCards.length)) {
            if(boardSize>0) {
                if(lastTrickWinner.equals("Player")) {
                    gameState.takeCards(playerPocket, playerPocketSize);
                    playerPocket = gameState.getPlayerPocket();
                    playerPocketSize = playerPocket.length;
                    gameState.scoreGame(player, computer, playerPocket, computerPocket, playerPişti, computerPişti);
                    playerScore = gameState.getPlayerScore();
                } else if (lastTrickWinner.equals("Computer")) {
                    gameState.takeCards(computerPocket, computerPocketSize);
                    computerPocket = gameState.getComputerPocket();
                    computerPocketSize = computerPocket.length;
                    gameState.scoreGame(player, computer, playerPocket, computerPocket, playerPişti, computerPişti);
                    computerScore = gameState.getComputerScore();
                }
                board.emptyBoard(boardSize);
                boardSize = board.getNumOfCards(); 
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