package com.example.geo.gameenginechess.Pieces;

import android.util.Log;

/**
 * Created by Geo on 2/4/2017.
 */

public class Queen extends Piece{

    public Queen(boolean available, int x, int y) {
        super(available, x, y);
        super.setPieceName("Queen");
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if(super.isValid(fromX, fromY, toX, toY) == false)
            return false;
        //diagonal
        if(Math.abs(toX - fromX )== Math.abs(toY - fromY)) {
            if ((fromX < toX) && (fromY < toY)) {
                int y = fromY + 1;
                for (int x = fromX + 1; x < toX; x++) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Queen", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y++;
                }
            } else if ((fromX > toX) && (fromY < toY)) {
                int y = fromY + 1;
                for (int x = fromX - 1; x > toX; x--) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Queen", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y++;
                }
            } else if ((fromX > toX) && (fromY > toY)) {
                int y = fromY - 1;
                for (int x = fromX - 1; x > toX; x--) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Queen", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y--;
                }
            } else if ((fromX < toX) && (fromY > toY)) {
                int y = fromY - 1;
                for (int x = fromX + 1; x < toX; x++) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Queen", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y--;
                }
            }
            return true;
        }
        if((toX == fromX) || (toY ==fromY)){
            if (fromX > toX){
                for(int x=fromX-1; x > toX; x--) {
                    if (getBoard().getSpot(x, fromY).isOccupied()){
                        Log.d("Queen", "occupied at (" + x + "," + fromY + ")");
                        return false;
                    }
                }
            }
            else if(fromX<toX){
                for(int x=fromX+1; x < toX; x++) {
                    if (getBoard().getSpot(x, fromY).isOccupied()) {
                        Log.d("Queen", "occupied at (" + x + "," + fromY + ")");
                        return false;
                    }
                }
            }
            if (fromY>toY){
                for(int y=fromY-1; y > toY; y--){
                    if (getBoard().getSpot(fromX,y).isOccupied()) {
                        Log.d("Queen", "occupied at (" + fromX + "," + y + ")");
                        return false;
                    }
                }
            }else if(fromY<toY){
                for(int y=fromY+1; y < toY; y++ ){
                    if (getBoard().getSpot(fromX,y).isOccupied()) {
                        Log.d("Queen", "occupied at (" + fromX + "," + y + ")");
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

}