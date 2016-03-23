package sample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmytro on 22.03.2016.
 */
public class MainGameTest {

    MainGame mainGame;

    @Test
    public void initMainGameSOP(){
        // given
        ConsoleMock console = new ConsoleMock();
        // when
        MainGame.main(new String[0]);
        // then
        assertEquals("Start the Game!\r\n", console.getOut());
    }

    @Before
    public void initMainGame(){
        mainGame = new MainGame();
    }

    @Test
    public void emptyParty(){
        manyThrowings(0, 20);
        assertEquals(0, mainGame.getGameScore());
    }

    @Test
    public  void oneShotOneHit(){
        manyThrowings(1, 20);
        assertEquals(20, mainGame.getGameScore());
    }

    @Test
    public void typicalEpample(){
        mainGame.throwingTheBall(1); //1
        mainGame.throwingTheBall(4);
        mainGame.throwingTheBall(4); //2
        mainGame.throwingTheBall(5);
        mainGame.throwingTheBall(6); //3
        mainGame.throwingTheBall(4);
        mainGame.throwingTheBall(5); //4
        mainGame.throwingTheBall(5);
        mainGame.throwingTheBall(10); //5
        mainGame.throwingTheBall(0); //6
        mainGame.throwingTheBall(1);
        mainGame.throwingTheBall(7); //7
        mainGame.throwingTheBall(3);
        mainGame.throwingTheBall(6); //8
        mainGame.throwingTheBall(4);
        mainGame.throwingTheBall(10); //9
        mainGame.throwingTheBall(2); //10
        mainGame.throwingTheBall(8);
        mainGame.throwingTheBall(6);
        assertEquals(133, mainGame.getGameScore());
    }

    @Test
    public void spareEpample(){
        mainGame.throwingTheBall(2);
        mainGame.throwingTheBall(8);
        mainGame.throwingTheBall(5);
        mainGame.throwingTheBall(1);
        mainGame.throwingTheBall(2); //23
        manyThrowings(0, 15);
        assertEquals(23, mainGame.getGameScore());
    }

    @Test
    public void strikeExample(){
        mainGame.throwingTheBall(10);
        mainGame.throwingTheBall(10);
        mainGame.throwingTheBall(1);
        mainGame.throwingTheBall(1);
        manyThrowings(0, 14);
        assertEquals(35,  mainGame.getGameScore());
    }

    @Test
    public void clearGameEpample(){
        manyThrowings(10, 12);
        assertEquals(300, mainGame.getGameScore());
    }

    private void manyThrowings(int pins, int throwing){
        for (int i = 0; i < throwing; i++) {
            mainGame.throwingTheBall(pins);
        }
    }

}
