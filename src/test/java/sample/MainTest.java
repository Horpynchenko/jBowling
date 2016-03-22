package sample;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmytro on 22.03.2016.
 */
public class MainTest {

    @Test
    public void test(){
        // given
        ConsoleMock console = new ConsoleMock();

        // when
        Main.main(new String[0]);

        // then
        assertEquals("Start the Game!\r\n", console.getOut());
    }

}
