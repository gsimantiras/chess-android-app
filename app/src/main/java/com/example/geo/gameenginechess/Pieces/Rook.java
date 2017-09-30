package com.example.geo.gameenginechess.Pieces;

import android.util.Log;

import com.example.geo.gameenginechess.Board;

/**
 * Created by Geo on 2/4/2017.
 */

public class Rook extends Piece{

    public Rook(boolean available, int x, int y) {
        super(available, x, y);
        // TODO Auto-generated constructor stub

        super.setPieceName("Rook");
    }


    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if(super.isValid( fromX, fromY, toX, toY) == false)
            return false;
        if((toX == fromX) || (toY == fromY)) {
            if (fromX > toX){
                for(int x=fromX-1; x > toX; x--) {
                    if (getBoard().getSpot(x, fromY).isOccupied()){
                        Log.d("ROOK",x +","+ fromY + " is occupied");
                        return false;
                    }
                }
            }
            else if(fromX<toX){
                for(int x=fromX+1; x < toX; x++) {
                    if (getBoard().getSpot(x, fromY).isOccupied()) {
                        Log.d("ROOK",x + ","+ fromY + " is occupied");
                        return false;
                    }
                }
            }
            if (fromY>toY){
                for(int y=fromY-1; y > toY; y--){
                    Log.d("ROOK", fromX +","+y + " is occupied");
                    if (getBoard().getSpot(fromX,y).isOccupied()) {
                        Log.d("ROOK", fromX +","+y + " is occupied");
                        return false;
                    }
                }
            }else if(fromY<toY){
                for(int y=fromY+1; y < toY; y++ ){
                    if (getBoard().getSpot(fromX,y).isOccupied()) {
                        Log.d("ROOK", fromX +","+y + " is occupied");
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isValidKill(int fromX,int fromY, int toX, int toY){
        if(super.isValid( fromX, fromY, toX, toY) == false)
            return false;
        if((toX == fromX) || (toY == fromY)) {
            return true;
        }
        return false;
    }
}