package com.example.geo.gameenginechess;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Geo on 2/4/2017.
 */

public class Game {
    private Board board;
    private Player white;
    private Player black;
    private Context mContext;
    private int round;
    private Player nextPlayer;

    public Game(Context c){
        super();
        mContext = c;
        board = new Board(c);
        white = new Player(true);
        black = new Player(false);
        nextPlayer = white;
        round =1;
    }

    public int nextRound(){
        if (nextPlayer == white) {
            nextPlayer = black;
        }else {
            nextPlayer = white;
        }
//        Toast.makeText(mContext, round, Toast.LENGTH_SHORT).show();
        round++;
        return round;
    }
    public Player getNextPlayer(){
        return nextPlayer;
    }
    public void setColorWhite(Player player){
        this.white = player;
    }
    public void setColorBlack(Player player){

    }
    public void setBoard(Board board){
        this.board = board;
    }
    public Board getBoard(){
        return this.board;
    }

    public Player getWhite(){
        return white;
    }

    public void setWhite(Player white){
        this.white = white;
    }

    public Player getBlack(){
        return black;
    }

    public void setBlack(Player black){
        this.black = black;
    }

    public boolean initializeBoardGivenPlayers(){
        if(this.black == null || this.white == null)
            return false;
        this.board = new Board(mContext);
        for (int i=0; i<black.getPieces().size(); i++){
            board.getSpot(black.getPieces().get(i).getX(), black.getPieces().get(i).getY()).occupySpot(black.getPieces().get(i));
        }
        return true;
    }



}
