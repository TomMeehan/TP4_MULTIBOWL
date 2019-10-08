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
        if(playerName == null || playerName.length == 0) throw new Exception("Aucun joueur dans la partie");
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
        if (this.players == null) throw new Exception("La partie n'a pas encore démarée");
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
        if(p == null) throw new Exception("Joueur introuvable");
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

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
}
