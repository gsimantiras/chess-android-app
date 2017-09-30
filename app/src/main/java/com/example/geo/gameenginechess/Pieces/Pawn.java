package com.example.geo.gameenginechess.Pieces;

import android.nfc.Tag;
import android.util.Log;

import com.example.geo.gameenginechess.Board;

/**
 * Created by Geo on 2/4/2017.
 */

public class Pawn extends Piece{
    private boolean firstMove = true;
    private int moveSpace = 2;
    public Pawn(boolean available, int x, int y) {
        super(available, x, y);
        super.setPieceName("Pawn");
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if(super.isValid(fromX, fromY, toX, toY) == false){
            Log.d("Pawn:","super movement is invalid");
            return false;
        }
        if (this.getColor().equals("Black")){
            if(((toX == fromX + moveSpace)||(toX == fromX +1))&& (toY == fromY )){
                if (moveSpace==2){
                    if (getBoard().getSpot(fromX+1, fromY).isOccupied()) {
                        Log.d("Pawn","occupied at:" +(fromX+1) + "," + fromY);
                        return false;
                    }
                    else {
                        Log.d("Pawn:","pawn movement is valid");
                        moveSpace = 1;
                        return true;
                    }
                }
            }
        }else{
            if(((toX == fromX - moveSpace)||(toX == fromX - 1))&& (toY == fromY )){
                if (moveSpace==2){
                    if (getBoard().getSpot(fromX-1, fromY).isOccupied()) {
                        Log.d("Pawn","occupied at:" + (fromX-1) + "," + fromY);
                        return false;
                    }
                    else {
                        Log.d("Pawn:","pawn movement is valid");
                        moveSpace = 1;
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean isValidToKill(int fromX, int fromY, int toX, int toY) {
        if(super.isValid(fromX, fromY, toX, toY) == false){
            Log.d("Pawn:","super movement is invalid");
            return false;
        }
        if (this.getColor().equals("Black")){
            if((toX == fromX + 1) && ((toY == fromY + 1) || (toY == fromY - 1))){
                Log.d("Pawn:","pawn movement is valid");
                moveSpace = 1;
                return true;
            }
        }else{
            if((toX == fromX - 1) && ((toY == fromY - 1) || (toY == fromY + 1))){
                Log.d("Pawn:","pawn movement is valid");
                moveSpace = 1;
                return true;
            }
        }

        return false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }
}
