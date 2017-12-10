import java.util.*;
import java.io.*;

public class HighscoreManager {
    // An arraylist of the type "score" used to work with the scores inside the class
    private ArrayList<Score> scores;

    // The name of the file where the highscores will be saved
    private static final String HIGHSCORE_FILE = "scores.dat";

    //Initialise an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighscoreManager() {
        //initialise the scores arraylist
        scores = new ArrayList<Score>();
    }


    //loads scores from score file
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    //Sorts the scores highest to lowest
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }

    //Adds new score to file
    public void addScore(int score) {
        loadScoreFile();
        scores.add(new Score(score));
        updateScoreFile();
    }

    //Loads the score file and gives appropriate warnings for File Not Found or IO Errors
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            //System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }


    //Updates score file and gives warnings on file not found or IO errors
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("FNF Error: " + e.getMessage() + ",the program will try to make a new file");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    //This function will display the highscore as a string
    public String getHighscoreString() {
        String highscoreString = "";
        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > 1) {
            x = 1;
        }
        while (i < x) {
            highscoreString += scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }

}

