package com.example.geo.gameenginechess.Pieces;

import android.widget.Toast;

import com.example.geo.gameenginechess.Board;

/**
 * Created by Geo on 2/4/2017.
 */

public class Knight extends Piece{

    public Knight(boolean available, int x, int y) {
        super(available, x, y);
        super.setPieceName("Knight");
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if(super.isValid(fromX, fromY, toX, toY) == false)
            return false;

        if((Math.abs(toX-fromX)==1 && Math.abs(toY-fromY)==2) || ((Math.abs(toY-fromY)==1 && Math.abs(toX-fromX)==2)))
        {
//            if (board.kingIsSafe(this)){
                return true;
//            }
//            else{
//                //TODO king is not safe there
//                return false;
//            }
        }
//        if(toY != fromY - 2 && toY != fromY + 2 && toY != fromY - 1 && toY != fromY + 1)
//            return false;

        return false;
    }


}