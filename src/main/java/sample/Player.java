package sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dmytro on 23.03.2016.
 */
public class Player {

    private String name;
    private int currentGamePoints = 0;
    private int championshipPoints = 0;
    private int currentThrow = 0;
    private int[] allThrows = new int[MainGame.ALL_GAME_THROWS];
    private Map<Integer, Integer> allThrowsMap = new HashMap<Integer, Integer>(MainGame.ALL_GAME_THROWS);

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

    public int getCurrentGamePoints() {
        return currentGamePoints;
    }

    public int getChampionshipPoints() {
        return championshipPoints;
    }

    public void setChampionshipPoints(int championshipPoints) {
        this.championshipPoints = championshipPoints;
    }

    public void setCurrentGamePoints(int currentGamePoints) {
        this.currentGamePoints = currentGamePoints;
    }

    public void setName(String name) {
        this.name = name;
    }
}
