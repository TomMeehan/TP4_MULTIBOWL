/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi;

import bowling.MultiPlayerGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedago
 */
public class TestMultiBowling {
    
    MultiPlayerGame game;
    String[] players = { "John", "Paul", "Georges", "Ringo" };
    
    public TestMultiBowling() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.game = new MultiBowling();
    }
    
    @After
    public void tearDown() {
    }

    //Test lancement de partie 
    @Test
    public void newGameTest() throws Exception{
        assertEquals("Prochain tir : joueur John, tour n° 1, boule n° 1",game.startNewGame(this.players));
    }
    
    //Test un lancer simple
    @Test
    public void nextPlayerTest() throws Exception{
        System.out.println(game.startNewGame(this.players));
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1",game.lancer(10));
    }
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
