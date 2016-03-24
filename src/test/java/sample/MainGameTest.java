package sample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dmytro on 22.03.2016.
 */
public class MainGameTest {

    MainGame mainGame;
    CreatePlayerInterface cpi;

    @Before
    public void initMainGame(){
        mainGame = new MainGame();
    }

    @Test
    public void createOneNamedPlayer(){
        cpi = new CreateNamedPlayer(mainGame, "Single Player");
        cpi.createPlayers();
        assertEquals("Single Player", mainGame.getPlayersMap().get("Single Player").getName());
    }

    @Test
    public void createManeNamedPlayers(){
        cpi = new CreateNamedPlayer(mainGame, "First", "Second", "Third", "Last");
        cpi.createPlayers();
        assertEquals("First", mainGame.getPlayersMap().get("First").getName());
        assertEquals("Second", mainGame.getPlayersMap().get("Second").getName());
        assertEquals("Third", mainGame.getPlayersMap().get("Third").getName());
        assertEquals("Last", mainGame.getPlayersMap().get("Last").getName());
    }

    @Test
    public void createNoNamedPlayer(){
        cpi = new CreateNamedPlayer(mainGame, "");
        cpi.createPlayers();
        assertEquals(0, mainGame.getPlayersMap().size());
    }

    @Test
    public void createOneDefaultPlayer(){
        cpi = new CreateDefaultPlayers(mainGame, 1);
        cpi.createPlayers();
        assertEquals(1, mainGame.getPlayersMap().size());
    }

    @Test
    public void createTenDefaultPlayers(){
        CreatePlayerInterface cpi = new CreateDefaultPlayers(mainGame, 10);
        cpi.createPlayers();
        assertEquals(10, mainGame.getPlayersMap().size());
    }

    @Test
    public void createNoDefaultPlayers(){
        cpi = new CreateDefaultPlayers(mainGame, 0);
        cpi.createPlayers();
        assertEquals(0, mainGame.getPlayersMap().size());
    }

    @Test
    public void perfectGameOfNamedPlayer(){
        cpi = new CreateNamedPlayer(mainGame, "Tester");
        cpi.createPlayers();
        mainGame.startGame( new GameTypeStatic(10) );
        assertEquals(300, mainGame.getPlayersMap().get("Tester").getCurrentGameScore());
    }

    @Test
    public void emptyGameOfNamedPlayer(){
        CreatePlayerInterface cpi = new CreateNamedPlayer(mainGame, "Tester");
        cpi.createPlayers();
        mainGame.startGame( new GameTypeStatic(0) );
        assertEquals(0, mainGame.getPlayersMap().get("Tester").getCurrentGameScore());
    }

    @Test
    public void emptyGameOfDefaultPlayer(){
        cpi = new CreateDefaultPlayers(mainGame, 1);
        cpi.createPlayers();
        mainGame.startGame( new GameTypeStatic(0) );
        assertEquals(0, mainGame.getPlayersMap().get("Player-1").getCurrentGameScore());
    }

    @Test
    public void perfectGameOfDefaultPlayer(){
        cpi = new CreateDefaultPlayers(mainGame, 1);
        cpi.createPlayers();
        mainGame.startGame( new GameTypeStatic(10) );
        assertEquals(300, mainGame.getPlayersMap().get("Player-1").getCurrentGameScore());
    }

}
