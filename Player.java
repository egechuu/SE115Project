import java.util.Scanner;
public class Player {
    private String name;
    private Cards[] hand;

    public Player() {
        this.hand = new Cards[0];
    }

    public void addCardtoHand(Cards card) {
        Cards[] newHand = new Cards[this.hand.length+1];
        for(int i=0; i<this.hand.length; i++)
            newHand[i] = this.hand[i];
        newHand[newHand.length-1] = card;
        this.hand = newHand;
    }

    public void removeCardFromHand(Cards card) {
        int cardIndex = -1;
        for(int i=0; i<this.hand.length; i++) {
            if(this.hand[i].equals(card)) {
                cardIndex = i;
                break;
            }
        }
        if(cardIndex == -1) 
            return;
        Cards[] newHand = new Cards[this.hand.length-1];
        int newHandIndex = 0;
        for(int i=0; i<this.hand.length; i++) {
            if(i==cardIndex)
                continue;
            newHand[newHandIndex] = this.hand[i];
            newHandIndex++;  
        }
        this.hand = newHand;
    }

    public Cards[] getHand() {
        return hand;
    }

    public String getName() {
        Scanner sc = new Scanner(System.in); 
        String userName = sc.nextLine();
        name = userName;
        sc.close();
        return name;
    }
}