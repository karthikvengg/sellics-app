package com.selics.model;

/**
 * This class implements the model required to store the response JSON
 *
 * @author Karthikeyan Varadarajan
 */
public class Score {

    /**
     * Input keyword
     */
    private String keyword;

    /**
     * Output score as per the algorithm
     */
    private int score;

    /**
     * Class constructor
     *
     * @param keyword Input keyword
     * @param score Output score
     */
    public Score(String keyword, int score) {
        this.keyword = keyword;
        this.score = score;
    }

    /**
     * Getter method for keyword
     *
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Setter method for keyword
     *
     * @param keyword keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Getter method for score
     *
     * @return score of the input keyword
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter method for the score
     *
     * @param score score as per the algorithm
     */
    public void setScore(int score) {
        this.score = score;
    }
}
