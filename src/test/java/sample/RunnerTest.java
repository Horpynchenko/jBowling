package sample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmytro on 23.03.2016.
 */

public class RunnerTest {

    MainGame mainGame;

    @Before
    public void initMainGame(){
        mainGame = new MainGame();
    }

//    @Test
//    public void initMainGameSOP(){
//        // given
//        ConsoleMock console = new ConsoleMock();
//        // when
//        Main.main(new String[0]);
//        // then
//        assertEquals("Start the Game!\r\n", console.getOut());
//    }


}
