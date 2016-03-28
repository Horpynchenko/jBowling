package sample;

import java.io.IOException;
import java.util.*;

/**
 * Created by Dmytro on 24.03.2016.
 * In this class described all calculation logic.
 */
public class MainGame {

    private HashMap<String, Player> playersMap = new HashMap<String, Player>();
    public static int ALL_GAME_THROWS = 21; // regular frames number * 2 + 1(bonus final frame):
    // 1->3, 2->5, 3->7, 4->9, 5->11, 6->13, 7->15, 8->17, 9->19, 19->21 11->23, 12->25...

    public static int MAX_FRAME_POINTS = 10;

    /**
     * Game start method.
     */
    public void startGame( GameTypeInterface gti ){

        for (int currentThrow = 1; currentThrow <= ALL_GAME_THROWS; currentThrow++) {
            // loop through each of player
            Iterator it = playersMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry <String, Player> playerEntry = (Map.Entry) it.next();
                Player player = playerEntry.getValue();
                Map<Integer, Integer> tmpUsersScoreMap = player.getAllThrowsMap();

                if ( isThrowAvailable(currentThrow, tmpUsersScoreMap) ){ //if player have right to throw
                    // new throw score: from input, randomly or static
                    // ... f.e. static, 4

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
                    System.out.println(playerEntry.getKey() + ": "+ currentThrow + ". Current score: " + currentPlayerScore);

                    // add score
                    playerEntry.getValue().setCurrentGameScore(currentPlayerScore);
                } else {
                    tmpUsersScoreMap.put(currentThrow, 0);
                    System.out.println(playerEntry.getKey() + "'s map: " + currentThrow + ": " + tmpUsersScoreMap);
                    System.out.println(playerEntry.getKey() + ": skipped " + currentThrow + " this throw. Current Score: "
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
        if ( currentThrow > 1 && currentThrow < ALL_GAME_THROWS-1) { // if regular (2 - 19)
            if (currentThrow % 2 == 0) { // if not 1-st in frame
                if (tmpUsersScoreMap.get(currentThrow - 1) == MAX_FRAME_POINTS) { // if previous is Strike
                    result = false;
                }
            }
        } else if (currentThrow == ALL_GAME_THROWS-1 && tmpUsersScoreMap.get(currentThrow - 1) < MAX_FRAME_POINTS) { // if 10.1 is NOT Strike
            result = false;
        } else if (currentThrow == ALL_GAME_THROWS) {
            if (tmpUsersScoreMap.get(currentThrow - 2) < MAX_FRAME_POINTS ||    // if 10.1 is NOT strike
                    tmpUsersScoreMap.get(currentThrow - 2) + tmpUsersScoreMap.get(currentThrow - 1) < MAX_FRAME_POINTS ){ // if 10.1 + 10.2 = is NOT Spare

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
            if (throwingCount < ALL_GAME_THROWS-2) { //if count < ALL_GAME_THROWS-2

                if (throwingCount % 2 != 0) { // if first in Frame

                    if (scoreMap.get(throwingCount) == MAX_FRAME_POINTS) { // if Strike (MAX_FRAME_POINTS)

                        if (scoreMap.get(throwingCount + 2) != null) { // if next elements are filled

                            if (scoreMap.get(throwingCount + 2) == MAX_FRAME_POINTS) { // if next one Strike

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

                        } else { // if next elements are not filled
                            resultScore += scoreMap.get(throwingCount);
                        }

                    } else {
                        resultScore += scoreMap.get(throwingCount);
                    }
                } else

                // if second in Frame
                if (throwingCount % 2 == 0) { // if Spare

                    if (scoreMap.size() != 1) {
                         if (scoreMap.get(throwingCount - 1) != MAX_FRAME_POINTS) { //if prev not Strike
                             if (scoreMap.get(throwingCount - 1) + scoreMap.get(throwingCount) == MAX_FRAME_POINTS) { // if Spare
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
                    } else {
                        resultScore += scoreMap.get(throwingCount);
                    }
                }

            } else { // if current >= ALL_GAME_THROWS-2
                resultScore += scoreMap.get(throwingCount);
            }
            throwingCount++;
            it.next();
        }
        return resultScore;
    }

    /**
     * Method calculate scores fo all players and print winner of the game
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
            System.out.print("Winners are: ");
            for (String winnersName : winnerList) {
                System.out.print(winnersName + ", ");
            }
            System.out.println("Congratulations!");
        }
        System.out.println("******************************************************************************************");
    }

    public Map<String, Player> getPlayersMap() {
        return playersMap;
    }

}


