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
    
    
    
    MultiBowling game;
    String[] players = { "John", "Paul", "Georges", "Ringo" };
    
    public TestMultiBowling() {
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
    
    //Test tour simple
    @Test
    public void simpleTurnTest() throws Exception{
        game.startNewGame(this.players);
        assertEquals("Prochain tir : joueur John, tour n° 1, boule n° 2",game.lancer(5));
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1",game.lancer(3));
    }
    
    //Test un lancer simple
    @Test
    public void nextPlayerTest() throws Exception{
        game.startNewGame(this.players);
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1",game.lancer(10));
    }
    
    //One full round test
    @Test
    public void fullRoundTest() throws Exception{
        game.startNewGame(this.players);
        assertEquals("Prochain tir : joueur John, tour n° 1, boule n° 2",game.lancer(5));
        assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1",game.lancer(3));
        assertEquals("Prochain tir : joueur Georges, tour n° 1, boule n° 1",game.lancer(10));
        assertEquals("Prochain tir : joueur Georges, tour n° 1, boule n° 2",game.lancer(3));
        assertEquals("Prochain tir : joueur Ringo, tour n° 1, boule n° 1",game.lancer(5));
        assertEquals("Prochain tir : joueur John, tour n° 2, boule n° 1",game.lancer(10));
        
    }
    
    //Test score simple
    @Test
    public void simpleScoreTest() throws Exception{
        game.startNewGame(this.players);
        game.lancer(8);
        game.lancer(1);
        assertEquals(9,game.scoreFor("John"));
    }
    
    //Test full game all strike
    @Test
    public void fullGameAllStrike() throws Exception{
        game.startNewGame(this.players);
        
        rollMany(this.players.length*12,10);
        
        for(Player player : game.getPlayers()){
            assertEquals(300,game.scoreFor(player.getName()));
        }
        
    }
    
    //Test full game all spare
    @Test
    public void fullGameAllSpare() throws Exception{
        game.startNewGame(this.players);

        for (int i = 0; i < this.players.length*9; i++){
            rollSpare();
        }
        
        for (int i = 0; i < this.players.length; i++){
            rollSpare();
            game.lancer(3);
        }
        
        for(Player player : game.getPlayers()){
            assertEquals(157,game.scoreFor(player.getName()));
        }
        
    }
    
    //Test full game all 1
    @Test
    public void fullGameAllone() throws Exception{
        game.startNewGame(this.players);
        rollMany(this.players.length*20,1);
        
        for(Player player : game.getPlayers()){
            assertEquals(20,game.scoreFor(player.getName()));
        }
         
    }
    
    //Test partie Terminée
    @Test
    public void fullGameEndPrint() throws Exception{
        
        game.startNewGame(this.players);
        
        rollMany(this.players.length*12 - 1 ,10);
        
        assertEquals("Partie Terminée",game.lancer(10));
    }
    
    //Test tableau vide startNewGame
    @Test(expected = Exception.class)  
    public void startNewGameException() throws Exception {
        game.startNewGame(null);
    }
    
    //Test lancer sans startNewGame
    @Test(expected = Exception.class) 
    public void lancerWithoutStart() throws Exception {
        game.lancer(10);
    }
    
    //Test score joueur introuvable
    @Test(expected = Exception.class)
    public void scorePlayerUnknown() throws Exception {
        game.startNewGame(this.players);
        game.scoreFor("Tom");
    }

    
    //Utils
    private void rollStrike() throws Exception{
        game.lancer(10);
    }
    
    private void rollSpare() throws Exception{
        game.lancer(6);
        game.lancer(4);
    }
    
    private void rollMany(int n, int pins) throws Exception{
        for (int i = 0; i < n; i++){
            game.lancer(pins);
	}
    }
}
