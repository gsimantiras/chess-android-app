package com.example.geo.gameenginechess.Pieces;

/**
 * Created by Geo on 2/4/2017.
 */
public class King extends Piece{


    public King(boolean available, int x, int y) {
        super(available, x, y);
        // TODO Auto-generated constructor stub
        super.setPieceName("King");
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if(super.isValid(fromX, fromY, toX, toY) == false)
            return false;
//        if(Math.sqrt(Math.pow(Math.abs((toX - fromX)),2)) + Math.pow(Math.abs((toY - fromY)), 2) != Math.sqrt(2)){
//            return false;
//        }
        if((Math.abs(toX - fromX )== Math.abs(toY - fromY)) && (Math.abs(toY - fromY) == 1))
            return true;
        if((toX == fromX) && (Math.abs(toY-fromY)==1))
            return true;
        if((toY == fromY) && (Math.abs(toX-fromX)==1))
            return true;
        return false;
    }


}