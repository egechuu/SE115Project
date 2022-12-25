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
        Cards[] playerPocket = new Cards[1];
        Cards[] computerPocket = new Cards[1];
        int playerScore = 0;
        int computerScore = 0;
        int playerPocketSize = 0;
        int computerPocketSize = 0;
        String lastTrickWinner = "";
        deck.Deal(4, player, computer, board);
        int boardSize = board.getNumOfCards();
        GameState gameState = new GameState(playerPocket, computerPocket, playerScore, computerScore, boardSize, board);

        while(!gameState.isGameOver(deck, playerCards.length, computerCards.length)) {
            playerCards = player.getHand();
            computerCards = computer.getHand();
            board.getBoard();
            boardSize = board.getNumOfCards();
            deck.isEmpty();
            System.out.println("The top card on the floor is: " + board.getTopCard());

            if(gameState.isGameOver(deck, playerCards.length, computerCards.length))
                break;

            if(playerCards.length==0 && computerCards.length==0) {
                deck.Deal(4, player, computer, board);
                playerCards = player.getHand();
                computerCards = computer.getHand();
            }

            if(playerCards.length>0) {
                Cards c = newGame.getPlayerCard();
                if(gameState.canTakeCards(c, boardSize)){
                    gameState.PlayerPişti(player, c);
                    player.removeCardFromHand(c);
                    board.addCardToBoard(c);
                    boardSize++;
                    gameState.takeCards(playerPocket, boardSize); 
                    playerPocket = gameState.getPlayerPocket();
                    playerPocketSize = playerPocket.length;
                    board.emptyBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerPocket, computerPocket);
                    playerScore = gameState.getPlayerScore();
                    lastTrickWinner = "Player";
                    playerCards = player.getHand();
                } else {
                    player.removeCardFromHand(c);
                    board.addCardToBoard(c);
                    boardSize++;
                }
            }

            if(computerCards.length>0) {
                Cards d = newGame.selectComputerCard();
                if(gameState.canTakeCards(d, boardSize)) {
                    gameState.ComputerPişti(d);
                    computer.removeCardFromHand(d);
                    board.addCardToBoard(d);
                    boardSize++;
                    gameState.takeCards(computerPocket, boardSize);
                    computerPocket = gameState.getComputerPocket();
                    computerPocketSize = computerPocket.length;
                    board.emptyBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerPocket, computerPocket);
                    computerScore = gameState.getComputerScore();
                    lastTrickWinner = "Computer";
                    computerCards = computer.getHand();
                } else {
                    computer.removeCardFromHand(d);
                    board.addCardToBoard(d);
                    boardSize++;
                }
                
            }
        }

        if(gameState.isGameOver(deck, playerCards.length, computerCards.length)) {
            if(boardSize>0) {
                if(lastTrickWinner.equals("Player")) {
                    gameState.takeCards(playerPocket, playerPocketSize);
                    playerPocket = gameState.getPlayerPocket();
                    playerPocketSize = playerPocket.length;
                    board.emptyBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerPocket, computerPocket);
                    playerScore = gameState.getPlayerScore();
                } else if (lastTrickWinner.equals("Computer")) {
                    gameState.takeCards(computerPocket, computerPocketSize);
                    computerPocket = gameState.getComputerPocket();
                    computerPocketSize = computerPocket.length;
                    board.emptyBoard(boardSize);
                    boardSize = board.getNumOfCards();
                    gameState.scoreGame(player, computer, playerPocket, computerPocket);
                    computerScore = gameState.getComputerScore();
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