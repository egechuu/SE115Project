public class Board {
    private Cards[] cards;

    public Board() {
        this.cards = new Cards[0];
    }

    public void addCardToBoard(Cards card) {
        Cards[] newCards = new Cards[this.cards.length+1];
        for(int i=0;i<this.cards.length;i++)
            newCards[i] = this.cards[i];
        newCards[newCards.length-1] = card;
        this.cards = newCards;
    }

    public void emptyBoard(int boardSize) {
        Cards[] newBoard = new Cards[boardSize];
        for(int i=0; i<boardSize; i++)
            this.cards[i] = newBoard[i];
        this.cards = newBoard;
        boardSize = 0;
        System.out.println("The board is now empty.");
    }

    public int getNumOfCards() {
        return this.cards.length;
    }

    public Cards[] getBoard(){
        return this.cards;
    }

    public Cards getTopCard() {
        return this.cards[this.cards.length-1];
    }
}

