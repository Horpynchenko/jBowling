package sample;

import java.io.IOException;
import java.util.*;

public class MainGame {

    private HashMap<String, Player> playersMap = new HashMap<String, Player>();

    /**
     * Method for creating single Player with defined name.
     * Created player will be put in the PlayersMap: <String, Player>
     * @param name
     */
    public void createPlayer(String name){
        Player player = new Player(name);
        playersMap.put(name, player);
    }

    /**
     * Method for creating a number of Players with default names like: Player-<Number>
     * All created players will put in the PlayersMap: <String, Player>
     * @param quantity
     */
    public void createDefaultPlayers(int quantity) {
        for (int i = 1; i <= quantity; i++) {
            Player player = new Player("Player-" + i);
            playersMap.put("Player-" + i, player);
        }
    }

    /**
     * Game start method.
     * New score getting for players depends of GameTypeInterface realization:
     * - input from console:     GameTypeInterface gti = new GameTypeConsoleInput();
     * - randomly:               GameTypeInterface gti = new GameTypeRandom();
     * - static for all players: GameTypeInterface gti = new GameTypeStatic();
     * - input from GUI:         GameTypeInterface gti = new GameTypeFromGUI(); // <- create realization
     */
    public void startGame(){
        for (int currentThrow = 1; currentThrow <= 21; currentThrow++) {
            // loop through each of player
            Iterator it = playersMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry <String, Player> playerEntry = (Map.Entry) it.next();
                Player player = playerEntry.getValue();
                Map<Integer, Integer> tmpUsersScoreMap = player.getAllThrowsMap();

                if ( isThrowAvailable(currentThrow, tmpUsersScoreMap) ){ //if player have right to throw
                    // new throw score: from input, randomly or static
                    // ... f.e. static, 4
                    GameTypeInterface gti = new GameTypeRandom(); // Change Interface realization to see difference
                    int newScore = 0;
                    try {
                        newScore = gti.getCurrentScore();
                    } catch (IOException e) {
                        System.out.print("GameTypeInterface impl IOException: " + e.getMessage());
                    }

                    // put new result on the user's score map
                    tmpUsersScoreMap.put(currentThrow, newScore);

                    //calculate current score
                    int currentPlayerScore = getCurrentPlayersScore(tmpUsersScoreMap);
                    System.out.println(playerEntry.getKey() + "'s map " + currentThrow + ": " + tmpUsersScoreMap);
                    System.out.println(playerEntry.getKey() + " "+ currentThrow + ". Current score: " + currentPlayerScore);

                    // add score
                    playerEntry.getValue().setCurrentGameScore(currentPlayerScore);
                } else {
                    tmpUsersScoreMap.put(currentThrow, 0);
                    System.out.println(playerEntry.getKey() + "'s map: " + currentThrow + ": " + tmpUsersScoreMap);
                    System.out.println(playerEntry.getKey() + " skipped " + currentThrow + " this throw. Current Score: "
                                                                                        + player.getCurrentGameScore());
                }
            }
        }

        // calculate and show score or each player
        // loop through each of player
        Iterator it = getPlayersMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + "'s score is: " + ((Player)pair.getValue()).getCurrentGameScore() );
        }
    }

    /**
     * Method to calculate throwing possibility for Current Player.
     * @param currentThrow
     * @param tmpUsersScoreMap
     * @return
     */
    private boolean isThrowAvailable(int currentThrow, Map<Integer, Integer> tmpUsersScoreMap) {
        boolean result = true;
        if ( currentThrow > 1 && currentThrow < 20) { // if regular (2 - 19)
            if (currentThrow % 2 == 0) { // if not 1-st in frame
                if (tmpUsersScoreMap.get(currentThrow - 1) == 10) { // if previous is Strike
                    result = false;
                }
            }
        } else if (currentThrow == 20 && tmpUsersScoreMap.get(currentThrow - 1) < 10) { // if 10.1 is NOT Strike
            result = false;
        } else if (currentThrow == 21) {
            if (tmpUsersScoreMap.get(currentThrow - 2) < 10 ||    // if 10.1 is NOT strike
                    tmpUsersScoreMap.get(currentThrow - 2) + tmpUsersScoreMap.get(currentThrow - 1) < 10 ){ // if 10.1 + 10.2 = is NOT Spare

                result = false;
            }
        }
        return result;
    }

    /**
     * Method for getting current Player's score.
     * @param scoreMap
     * @return
     */
    public int getCurrentPlayersScore(Map<Integer, Integer> scoreMap) {
        int resultScore = 0;
        int throwingCount = 1;
        Iterator it = scoreMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> scoreEntry = (Map.Entry) it.next();

            if (throwingCount < 19) { //if count < 19

                if (throwingCount % 2 != 0) { // if first in Frame

                    if (scoreMap.get(throwingCount) == 10) { // if Strike (10)

                        if (scoreMap.get(throwingCount + 2) != null) { // if next elements are filled

                            if (scoreMap.get(throwingCount + 2) == 10) { // if next one Strike

                                if (scoreMap.get(throwingCount + 4) != null) {
                                    // add to currentVal: (currentVal + count+2 + count+4 ):
                                    resultScore += scoreMap.get(throwingCount)
                                                    + scoreMap.get(throwingCount + 2) + scoreMap.get(throwingCount + 4);
                                }  else {
                                    resultScore += scoreMap.get(throwingCount) + scoreMap.get(throwingCount + 2);
                                }
                            } else { // if next one not Strike
                                if (scoreMap.get(throwingCount + 3) != null) {
                                    // add to currentVal: (currentVal + count+2 + count+3 ):
                                    resultScore += scoreMap.get(throwingCount + 2) + scoreMap.get(throwingCount + 3);
                                } else {
                                    resultScore += scoreMap.get(throwingCount + 2);
                                }
                            }
                            throwingCount = throwingCount + 1;

                        } else { // if next elements are not filled
                            resultScore += scoreMap.get(throwingCount);
                            throwingCount = throwingCount + 1;
                        }

                    } else {
                        resultScore += scoreMap.get(throwingCount);
                        throwingCount++;
                    }
                } else

                // if second in Frame
                if (throwingCount % 2 == 0) { // if Spare

                    if (scoreMap.size() != 1) {
                         if (scoreMap.get(throwingCount - 1) != 10) { //if prev not Strike
                             if (scoreMap.get(throwingCount - 1) + scoreMap.get(throwingCount) == 10) { // if Spare
                                 if (scoreMap.get(throwingCount + 1) != null) {
                                     // add to currentVal: (currentVal + count+1)
                                     resultScore += scoreMap.get(throwingCount) + scoreMap.get(throwingCount + 1);
                                 } else {
                                     resultScore += scoreMap.get(throwingCount);
                                 }
                             } else { // else
                                 // add current to result:
                                 resultScore += scoreMap.get(throwingCount);
                             }
                         }
                        throwingCount++;
                    } else {
                        resultScore += scoreMap.get(throwingCount);
                        throwingCount++;
                    }
                }

            } else { // if current >= 19
                resultScore += scoreMap.get(throwingCount);
                throwingCount++;
            }
        }
        return resultScore;
    }

    /**
     * Method calculate and print winner of the game
     */
    public void printWinner(){
        Player winner = new Player("Winner");
        List<String> winnerList = new ArrayList<String>();
        Iterator it = getPlayersMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Player> playerEntry = (Map.Entry) it.next();
            Player player = playerEntry.getValue();
            int currentPlayerScore = player.getCurrentGameScore();
            int winnerPlayerScore = winner.getCurrentGameScore();
            if (currentPlayerScore > winnerPlayerScore) {
                winner.setCurrentGameScore(currentPlayerScore);
                winner.setName(player.getName());
                winnerList.clear();
                winnerList.add( winner.getName() + ", with " + winner.getCurrentGameScore() + " points!");
            } else if (currentPlayerScore == winnerPlayerScore){
                winnerList.add(player.getName() + ", with " + player.getCurrentGameScore() + " points!");
            }
        }
        System.out.println("******************************************************************************************");
        if (winnerList.size() == 1) {
            System.out.println("Winner is: " + winner.getName() + ", with " + winner.getCurrentGameScore()
                    + " points! Congratulations!");
        } else {
            System.out.println("Winners are: " + winnerList + " Congratulations!");
        }
        System.out.println("******************************************************************************************");
    }

    public Map<String, Player> getPlayersMap() {
        return playersMap;
    }

}


