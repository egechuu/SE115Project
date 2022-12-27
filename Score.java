import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;

public class Score {
    private String playerName;
    private int score;
    Score [] topScores = new Score[10];

    public Score(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        Scanner sc = new Scanner(System.in);
        this.playerName = sc.nextLine();
        sc.close();
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public void addScore(String playerName, int newScore) {
        readScore();
        for(int i=0; i<topScores.length; i++) {
            if(topScores[i] == null || newScore > topScores[i].getScore()) {
                for(int j=topScores.length-1; j>i; j--) {
                    topScores[j] = topScores[j-i];
                }
                topScores[i] = new Score(playerName, newScore);
                break;
            }
        }
        try {
            PrintWriter writer = new PrintWriter("Scores.txt");
            for (int i = 0; i < topScores.length; i++) {
                if (topScores[i] != null) 
                    writer.println(topScores[i].getPlayerName() + " : " + topScores[i].getScore());
            }
        }catch(IOException e){
            System.out.println("Cannot write to the file.");
        }
    }

    public void readScore() {
        try {
            Scanner fileReader = new Scanner(new File("Scores.txt"));
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(",");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
        }
    }
}


