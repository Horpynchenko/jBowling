package sample;

import java.io.IOException;
import java.util.*;

/**
 * Created by Dmytro on 24.03.2016.
 * In this class described main game calculation logic.
 */
public class MainGame {

    private Map<String, Player> playersMap = new LinkedHashMap<String, Player>();
    public static int ALL_GAME_THROWS = 21; // regular frames number * 2 + 1(bonus final frame):
    // 1->3, 2->5, 3->7, 4->9, 5->11, 6->13, 7->15, 8->17, 9->19, 19->21...

    public static int MAX_FRAME_POINTS = 10;

    /**
     * Game start method.
     */
    public void startGame( GameTypeInterface gti ){

        // loop through all game throws
        for (int currentThrow = 1; currentThrow <= ALL_GAME_THROWS; currentThrow++) {

            // loop through each of player
            Iterator it = playersMap.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry <String, Player> playerEntry = (Map.Entry) it.next();
                Player player = playerEntry.getValue();
                Map<Integer, Integer> tmpUsersPointsMap = player.getAllThrowsMap();

                if ( isThrowAvailable(currentThrow, tmpUsersPointsMap) ){ // if player have right to throw
                    int newPoints = 0;
                    System.out.println( currentThrow + ". " + player.getName() + " throw:" );

                    try {

                        if (currentThrow % 2 == 0){ // if current throw is second in Frame
                            // set max possible points in 2-nd throw in regular Frame:
                            newPoints = gti.getCurrentPoints( MAX_FRAME_POINTS - tmpUsersPointsMap.get( currentThrow-1 ) );
                        } else {
                            // set max points in 2-nd throw in regular Frame:
                            newPoints = gti.getCurrentPoints( MAX_FRAME_POINTS );
                        }

                    } catch (IOException e) {
                        System.out.print("GameTypeInterface impl IOException: " + e.getMessage());
                    }

                    // put new result on the user's points map
                    tmpUsersPointsMap.put( currentThrow, newPoints );

                    // calculate current points
                    int currentPlayerPoints = getCurrentPlayersPoints(tmpUsersPointsMap);
                    System.out.println( "Throws map " + currentThrow + ": " + tmpUsersPointsMap );
                    System.out.println( "Current points: " + currentPlayerPoints );

                    // add points
                    playerEntry.getValue().setCurrentGamePoints( currentPlayerPoints );

                } else {
                    tmpUsersPointsMap.put( currentThrow, 0 );
                    System.out.println( currentThrow + ". " + player.getName() + " skip current throw." );
                    System.out.println( "Throws map " + currentThrow + ": " + tmpUsersPointsMap);
                    System.out.println( "Current points: " + player.getCurrentGamePoints() );
                }
            }

        }



    }

    /**
     * Method to calculate throwing possibility for Current Player.
     * @param currentThrow
     * @param tmpUsersPointsMap
     * @return
     */
    private boolean isThrowAvailable(int currentThrow, Map<Integer, Integer> tmpUsersPointsMap) {
        boolean result = true;

        if ( currentThrow > 1 && currentThrow < ALL_GAME_THROWS-1) { // if regular (from 2 to ALL_GAME_THROWS-1)

            if (currentThrow % 2 == 0) { // if not 1-st in frame

                if (tmpUsersPointsMap.get((currentThrow - 1)) == MAX_FRAME_POINTS) { // if previous is Strike
                    result = false;
                }
            }

        } else if (currentThrow == ALL_GAME_THROWS) { // check before bonus throw

            // if FinalFrame.1 in FinalFrame.2 was Strike or Spare
            if ( tmpUsersPointsMap.get( currentThrow - 2 ) + tmpUsersPointsMap.get( currentThrow - 1 ) < MAX_FRAME_POINTS ){
                result = false;
            }

        }
        return result;
    }

    /**
     * Method for getting current Player's points.
     * @param pointsMap
     * @return
     */
    public int getCurrentPlayersPoints(Map<Integer, Integer> pointsMap) {
        int resultPoint = 0;
        int throwingCount = 1;
        Iterator it = pointsMap.entrySet().iterator();

        while ( it.hasNext() ) {
            if ( throwingCount < ALL_GAME_THROWS-1 ) { // if count < ALL_GAME_THROWS-1

                if ( throwingCount % 2 != 0 ) { // if first in Frame

                    if (pointsMap.get( throwingCount ) == MAX_FRAME_POINTS) { // if Strike (MAX_FRAME_POINTS)

                        if (pointsMap.get( throwingCount + 2 ) != null) { // if next elements are filled

                            if (pointsMap.get( throwingCount + 2 ) == MAX_FRAME_POINTS) { // if next one Strike

                                if (pointsMap.get( throwingCount + 4 )  != null) {
                                    // add to currentVal: (currentVal + count+2 + count+4 ):
                                    resultPoint += pointsMap.get( throwingCount )
                                                    + pointsMap.get( throwingCount + 2 ) + pointsMap.get( throwingCount + 4 );
                                }  else {
                                    resultPoint += pointsMap.get( throwingCount ) + pointsMap.get( throwingCount + 2 );
                                }

                            } else { // if next one not Strike

                                if (pointsMap.get( throwingCount + 3 ) != null) {
                                    // add to currentVal: (currentVal + count+2 + count+3 ):
                                    resultPoint += pointsMap.get( throwingCount + 2 ) + pointsMap.get( throwingCount + 3 );
                                } else {
                                    resultPoint += pointsMap.get( throwingCount + 2 );
                                }

                            }

                        } else { // if next elements are not filled
                            resultPoint += pointsMap.get( throwingCount );
                        }

                    } else {
                        resultPoint += pointsMap.get( throwingCount );
                    }
                } else if ( throwingCount % 2 == 0 ) { // // if second in Frame

                    if ( pointsMap.size() != 1 ) {

                        if ( pointsMap.get( throwingCount - 1 ) != MAX_FRAME_POINTS ) { //if prev not Strike

                             if ( pointsMap.get( throwingCount - 1 ) + pointsMap.get(throwingCount) >= MAX_FRAME_POINTS ) { // if Spare

                                 if ( pointsMap.get( throwingCount + 1 ) != null ) {
                                     // Fuse overflow. If points in second frame > MAX_FRAME_POINTS, correct last one.
                                     pointsMap.put( throwingCount, MAX_FRAME_POINTS - pointsMap.get( throwingCount - 1 ) );

                                     // add to currentVal: (currentVal + count+1)
                                     resultPoint += pointsMap.get( throwingCount ) + pointsMap.get( throwingCount + 1 );
                                 } else {
                                     resultPoint += pointsMap.get( throwingCount );
                                 }

                             } else {
                                 resultPoint += pointsMap.get( throwingCount ); // add current to result:
                             }

                         }

                    } else {
                        resultPoint += pointsMap.get( throwingCount );
                    }
                }

            } else { // if current >= ALL_GAME_THROWS-2

                if (throwingCount % 2 == 0) { // if second in final Frame

                    if (pointsMap.get( throwingCount - 1 ) < MAX_FRAME_POINTS // if first was Not Strike
                            && pointsMap.get( throwingCount - 1 ) + pointsMap.get(throwingCount) > MAX_FRAME_POINTS){

                        // Fuse overflow. If points in second throw of the final Frame overflowed, correct it.
                        pointsMap.put( throwingCount, MAX_FRAME_POINTS - pointsMap.get( throwingCount - 1 ) );
                    }

                } else { // if is NOT second in final Frame
                    // check if bonus:
                    resultPoint += pointsMap.get( throwingCount );
                }

            }
            throwingCount++;
            it.next();
        }
        return resultPoint;
    }

    /**
     * Method shows and calculate points for all players and print winner of the game
     */
    public void printWinner(){
        Player winner = new Player("Winner");
        List<String> winnerList = new ArrayList<String>();
        Iterator it = getPlayersMap().entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Player> playerEntry = (Map.Entry) it.next();
            Player player = playerEntry.getValue();
            int currentPlayerPoints = player.getCurrentGamePoints();
            int winnerPlayerPoints = winner.getCurrentGamePoints();

            System.out.println( player.getName() + "'s points are: " + player.getCurrentGamePoints() );

            if (currentPlayerPoints > winnerPlayerPoints) {
                winner.setCurrentGamePoints(currentPlayerPoints);
                winner.setName(player.getName());
                winnerList.clear();
                winnerList.add( winner.getName() + ", with " + winner.getCurrentGamePoints() + " points!");
            } else if (currentPlayerPoints == winnerPlayerPoints){
                winnerList.add(player.getName() + ", with " + player.getCurrentGamePoints() + " points!");
            }
        }
        System.out.println("******************************************************************************************");

        if (winnerList.size() == 1) {
            System.out.println("Winner is: " + winner.getName() + ", with " + winner.getCurrentGamePoints()
                    + " points! Congratulations!");
        } else {
            System.out.print("Winners are: ");

            for (String winnersName : winnerList) {
                System.out.print(winnersName + " ");
            }

            System.out.println("Congratulations!");
        }

        System.out.println("******************************************************************************************");
    }

    public Map<String, Player> getPlayersMap() {
        return playersMap;
    }

}


