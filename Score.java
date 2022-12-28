import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.BufferedReader;
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
            writer.println("-----TOP SCORES LIST-----");
            for (int i = 0; i < topScores.length; i++) {
                if (topScores[i] != null) 
                    writer.println(i+1 + ") " + topScores[i].getPlayerName() + " : " + topScores[i].getScore());
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("Cannot write to the file.");
        }
    }

    public void readScore() {
        try {
            Scanner fileReader = new Scanner(new File("Scores.txt"));
            int i = 0;
            fileReader.nextLine();
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(" ");
                String name = parts[1];
                int score = Integer.parseInt(parts[parts.length-1]);
                topScores[i] = new Score(name, score);
                i++;
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found.");
        }
    }

    public void PrintTopScoreList() {
        try {
            FileReader list = new FileReader("Scores.txt");
            BufferedReader br = new BufferedReader(list);
            String line;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }   
        br.close();
        list.close();
        } catch(IOException e) {
            System.out.println("Cannot print Top Score List.");
        }

    }
}


