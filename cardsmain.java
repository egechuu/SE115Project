import java.util.Random;
public class cardsmain {
    
    public static void main(String[] args) {
        char spades = '♤';
        char diamonds = '♢';
        char hearts = '♡';
        char clubs = '♧';

        char[] suit = {spades, diamonds, hearts, clubs};
        String[] face = {"Ace", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] deck = new String[52];

        for (int i=0; i<deck.length; i++) 
            deck[i] = face[i%13] + suit[i/13];

        for (int i=0; i<deck.length; i++) {
            Random rd = new Random();
            int index = rd.nextInt(deck.length);
            String temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }

        for (String x: deck)
            System.out.println(x);
    }
}
