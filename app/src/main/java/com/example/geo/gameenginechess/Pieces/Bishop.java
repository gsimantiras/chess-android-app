package com.example.geo.gameenginechess.Pieces;

import android.util.Log;

import com.example.geo.gameenginechess.Board;

/**
 * Created by Geo on 2/4/2017.
 */

public class Bishop extends Piece{
    public Bishop(boolean available, int x, int y) {
        super(available, x, y);
        // TODO Auto-generated constructor stub
        super.setPieceName("Bishop");
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if(super.isValid(fromX, fromY, toX, toY) == false)
            return false;
        if(Math.abs(toX - fromX )== Math.abs(toY - fromY)) {
            if ((fromX < toX) && (fromY < toY)) {
                int y = fromY + 1;
                for (int x = fromX +1; x < toX; x++) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Bishop", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y++;
                }
            } else if ((fromX > toX) && (fromY < toY)) {
                int y = fromY +1;
                for (int x = fromX - 1; x > toX; x--) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Bishop", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y++;
                }
            } else if ((fromX > toX) && (fromY > toY)) {
                int y = fromY -1;
                for (int x = fromX -1; x > toX; x--) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Bishop", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y--;
                }
            } else if ((fromX < toX) && (fromY > toY)) {
                int y = fromY -1;
                for (int x = fromX + 1; x < toX; x++) {
                    if (getBoard().getSpot(x, y).isOccupied()) {
                        Log.d("Bishop", "occupied at (" + x + "," + y + ")");
                        return false;
                    }
                    y--;
                }
            }
            return true;
        }
        return false;
    }

}
