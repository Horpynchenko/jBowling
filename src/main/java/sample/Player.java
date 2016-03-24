package sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro on 23.03.2016.
 */
public class Player {
    private String name;
    private int currentGameScore = 0;
    private int championshipScore = 0;
    private int currentThrow = 0;
    private int[] allThrows = new int[21];
    private Map<Integer, Integer> allThrowsMap = new HashMap<>(21);

    public Map<Integer, Integer> getAllThrowsMap() {
        return allThrowsMap;
    }

    public void setAllThrowsMap(Map<Integer, Integer> allThrowsMap) {
        this.allThrowsMap = allThrowsMap;
    }

    public int getCurrentThrow() {
        return currentThrow;
    }

    public int[] getAllThrows() {
        return allThrows;
    }

    public void setAllThrows(int[] allThrows) {
        this.allThrows = allThrows;
    }

    public void setCurrentThrow(int currentThrow) {
        this.currentThrow = currentThrow;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentGameScore() {
        return currentGameScore;
    }
    public int getChampionshipScore() {
        return championshipScore;
    }

    public void setChampionshipScore(int championshipScore) {
        this.championshipScore = championshipScore;
    }

    public void setCurrentGameScore(int currentGameScore) {
        this.currentGameScore = currentGameScore;
    }

    public void setName(String name) {
        this.name = name;
    }
}
