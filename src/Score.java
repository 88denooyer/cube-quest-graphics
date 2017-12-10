import java.io.Serializable;

//Returns score for use by other functions
public class Score  implements Serializable {
    private int score;

    public int getScore() {
        return score;
    }

    public Score(int score) {
        this.score = score;
    }
}