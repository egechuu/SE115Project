import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
public class cardsmain {
    private static String[] playerHand;
    private static String[] computerHand;
    private static String cardOnTop;

    public static String[] Shuffle(String deck[], String face[], char suit[]) {
        System.out.println("Mmmh, let me shuffle this deck real quick...");
        for (int i=0; i<deck.length; i++) 
            deck[i] = face[i%13] + suit[i/13];
        for (int i=0; i<deck.length; i++) {
            Random rd = new Random();
            int index = rd.nextInt(deck.length);
            String temp = deck[i];
            deck[i] = deck[index];
            deck[index] = temp;
        }
        return deck;
    }

    public static String[] CutDeck(String deck[]) {
        System.out.println("You know what? I'll also cut it for you. Nothing is worse than a cheater in a game of fortune!");
        Random index = new Random(System.currentTimeMillis());
        int endIndex = index.nextInt(51);
        String[] cutDeck = new String[endIndex];
        for (int i=0;i<cutDeck.length;i++)
            cutDeck[i]=deck[i];
        String[] mergedDeck = new String[52];
        System.arraycopy(cutDeck, 0, mergedDeck, 0, endIndex);
        System.arraycopy(deck, endIndex+1 , mergedDeck, endIndex, 51-endIndex);
        return mergedDeck;
    }

    public static void Deal(String mergedDeck[]){
        for (int i=0; i<4; i++) {
        playerHand[i] = mergedDeck[i];
        computerHand[i] = mergedDeck[i+4];
        cardOnTop = mergedDeck[i+8];
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char spades = '♤';
        char diamonds = '♢';
        char hearts = '♡';
        char clubs = '♧';

        char[] suit = {spades, diamonds, hearts, clubs};
        String[] face = {"Ace", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] deck = new String[52];

        System.out.println("Welcome player! New here? Nevermind.. Welcome again to a game of Pisti!");
        System.out.println("Hmmm. What should I call you? Springbeans? Ted? Probably not. Why don't you give me your name?");
        String username = sc.next(); 
        System.out.println("Nice having you " + username + ", no time for chit-chat anymore. Let the game begin!");
        Shuffle(deck, face, suit);
        CutDeck(deck);
        Deal(deck);
        System.out.println(Arrays.toString(playerHand));
        System.out.println(Arrays.toString(computerHand));
        System.out.println(cardOnTop);

    }
}
