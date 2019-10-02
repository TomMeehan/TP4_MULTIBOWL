package multi;


import bowling.SinglePlayerGame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedago
 */
public class Player {
    
    private String name;
    private SinglePlayerGame game;

    public Player(String name, SinglePlayerGame game){
        this.name = name;
        this.game = game;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SinglePlayerGame getGame() {
        return game;
    }

    public void setGame(SinglePlayerGame game) {
        this.game = game;
    }
    
    
    @Override
    public String toString(){

        return this.name + 
                ", tour n° " + this.game.getFrameNumber() +
                ", boule n° " + this.game.getNextBallNumber();
    }
    
}
