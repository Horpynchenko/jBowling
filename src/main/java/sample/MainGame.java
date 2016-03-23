package sample;

public class MainGame {
    public static void main(String[] args) {
        System.out.println("Start the Game!");
        //// TODO: 23.03.2016 create possibility of manual input of scores
        //// TODO: 23.03.2016 create possibility a number players
        //// TODO: 23.03.2016 create Start and End of the game
        //// TODO: 23.03.2016 create javaFX gui
    }

    private int currentThrow = 0;
    private int[] allThrows = new int[21];

    public int getGameScore(){
        int resultScore = 0;
        int throwingCount = 0;
        for (int i = 0; i < 10; i++) {
            if (isaStrike(allThrows[throwingCount])){ // strike score counting
                resultScore += getStrikeScore(throwingCount);
                throwingCount++;
            } else if (isaSpare(throwingCount)){ // spare score counting
                resultScore += getSpareScore(throwingCount);
                throwingCount += 2;
            } else { // regular score counting
                resultScore += getRegularScore(throwingCount);
                throwingCount += 2;
            }
        }
        return resultScore;
    }

    public void throwingTheBall(int pins) {
        allThrows[currentThrow++] = pins;
    }

    private boolean isaSpare(int throwingCount) {
        return isaStrike(allThrows[throwingCount] + allThrows[throwingCount+1]);
    }

    private boolean isaStrike(int throwResult) {
        return throwResult == 10;
    }

    private int getStrikeScore(int throwingCount) {
        return allThrows[throwingCount] + allThrows[throwingCount+1] + allThrows[throwingCount + 2];
    }

    private int getSpareScore(int throwingCount) {
        return allThrows[throwingCount] + allThrows[throwingCount+1] + allThrows[throwingCount + 2];
    }

    private int getRegularScore(int throwingCount) {
        return allThrows[throwingCount] + allThrows[throwingCount+1];
    }

}


