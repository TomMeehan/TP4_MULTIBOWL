package multi;


import bowling.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedago
 */
public class MultiBowling implements MultiPlayerGame {
    
    private Player[] players;
    private int currentPlayerIndex;
    
    public MultiBowling(){

    }

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        this.players = new Player[playerName.length];
        for (int i = 0; i < playerName.length; i++){
            Player p = new Player(playerName[i],new SinglePlayerGame());
            players[i] = p;
        }
        this.currentPlayerIndex = 0;
        return "Prochain tir : joueur " + players[0];
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        
        Player currentPlayer = players[currentPlayerIndex%players.length];
        
        currentPlayer.getGame().lancer(nombreDeQuillesAbattues);
        
        if (currentPlayer.getGame().isFinished() || currentPlayer.getGame().hasCompletedFrame())
            currentPlayerIndex++;
        
        if(this.isFinished())
            return "Partie Terminée";
        else
            return "Prochain tir : joueur " + players[currentPlayerIndex%players.length].toString();
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        Player p = null;
        for(int i = 0; i < this.players.length; i++){
            if (this.players[i].getName() == playerName){
                p = this.players[i];
            }
        }
        
        return p.getGame().score();
    }

    private boolean isFinished() {
        for (Player p : players){
            if(!p.getGame().isFinished()){
                return false;
            }
        }
        return true;
    }
    
    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }
    
}
