package com.example.geo.gameenginechess.Pieces;

import android.graphics.Color;
import android.widget.ImageView;

import com.example.geo.gameenginechess.Board;

/**
 * Created by Geo on 2/4/2017.
 */

public class Piece {
    private boolean available;
    private int x;
    private int y;
    private ImageView pieceImage;
    private String color;
    private int imageId;
    private String pieceName;
    private Board board;

    public Piece(boolean available, int x, int y) {
        super();
        this.available = available;
        this.x = x;
        this.y = y;
    }

    public void setPieceImage(ImageView img){
        this.pieceImage = img;
    }
    public ImageView getPieceImage(){
        return this.pieceImage;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean isValid(int fromX, int fromY, int toX, int toY){
        if(toX == fromX && toY == fromY)
            return false; //cannot move nothing
        if(toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY <0 || fromY > 7)
            return false;
        return true;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getColor() {
        return color;
    }

    public String getPieceName() {
        return pieceName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}