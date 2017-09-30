package com.example.geo.gameenginechess;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.example.geo.gameenginechess.Pieces.Bishop;
import com.example.geo.gameenginechess.Pieces.King;
import com.example.geo.gameenginechess.Pieces.Knight;
import com.example.geo.gameenginechess.Pieces.Pawn;
import com.example.geo.gameenginechess.Pieces.Piece;
import com.example.geo.gameenginechess.Pieces.Queen;
import com.example.geo.gameenginechess.Pieces.Rook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geo on 2/4/2017.
 */

public class Board {
    private static final String TAG = Board.class.getSimpleName();
    private Spot[][] spots = new Spot[8][8];
    private List<Piece> pieceList;

    public Board(Context c) {
        super();

        try{
            pieceList = new ArrayList<>();
            Rook rookBlue1 = new Rook(true,0,0);
            pieceList.add(rookBlue1);
            Knight knightBlue1 =  new Knight(true,0,1);
            pieceList.add(knightBlue1);
            Bishop bishopBlue1 = new Bishop(true,0,2);
            pieceList.add(bishopBlue1);
            King kingBlue = new King(true,0,3);
            pieceList.add(kingBlue);
            Queen queenBlue = new Queen(true,0,4);
            pieceList.add(queenBlue);
            Bishop bishopBlue2 = new Bishop(true,0,5);
            pieceList.add(bishopBlue2);
            Knight knightBlue2 =  new Knight(true,0,6);
            pieceList.add(knightBlue2);
            Rook rookBlue2 = new Rook(true,0,7);
            pieceList.add(rookBlue2);
            for (int i=0; i<8;i++){
                Pawn pawn = new Pawn(true,1,i);
                pieceList.add(pawn);
            }

            for (int i = 0; i<16;i++){
                pieceList.get(i).setColor("Black");
            }

            for (int i=0; i<8;i++){
                Pawn pawn = new Pawn(true,6,i);
                pieceList.add(pawn);
            }

            Rook rookRed1 = new Rook(true,7,0);
            pieceList.add(rookRed1);
            Knight knightRed1 =  new Knight(true,7,1);
            pieceList.add(knightRed1);
            Bishop bishopRed1 = new Bishop(true,7,2);
            pieceList.add(bishopRed1);
            King kingRed = new King(true,7,3);
            pieceList.add(kingRed);
            Queen queenRed = new Queen(true,7,4);
            pieceList.add(queenRed);
            Bishop bishopRed2 = new Bishop(true,7,5);
            pieceList.add(bishopRed2);
            Knight knightRed2 =  new Knight(true,7,6);
            pieceList.add(knightRed2);
            Rook rookRed2 = new Rook(true,7,7);
            pieceList.add(rookRed2);
            for (int i = 16; i<32;i++){
                pieceList.get(i).setColor("White");
            }


            int x=0;
            for (int i=0; i<32; i++){
                Piece p = pieceList.get(i);
                ImageView imageView = new ImageView(c);

                if (i<7){
                    if (i<=4){
                        imageView.setBackgroundResource(pieceImagesBlackIds[x]);
                        p.setImageId(x);
                        x++;
                    }else{
                        x--;
                        imageView.setBackgroundResource(pieceImagesBlackIds[x-2]);
                        p.setImageId(x-2);
                    }
                }
                else if ((i>7) && (i<16)){
                    imageView.setBackgroundResource(pieceImagesBlackIds[5]);
                    p.setImageId(5);
                }else if ((i>15) && (i<24))
                {
                    imageView.setBackgroundResource(pieceImagesWhiteIds[5]);
                    p.setImageId(5);
                    x=0;
                }
                else if((i>23) && (i<32)){
                    if (i<=28){
                        imageView.setBackgroundResource(pieceImagesWhiteIds[x]);
                        p.setImageId(x);
                        x++;
                    }else{
                        x--;
                        imageView.setBackgroundResource(pieceImagesWhiteIds[x-2]);
                        p.setImageId(x-2);
                    }
                }
                p.setPieceImage(imageView);
            }

        }catch (Exception e){
            Log.d(TAG, e.getMessage(),e );
        }
        int count=0;

        for(int x=0; x<spots.length; x++){
            for(int y=0; y<spots.length; y++){
                this.spots[x][y] = new Spot(x, y);
                if (x==0 || x==1 || x==6 || x==7){
                    this.spots[x][y].occupySpot(pieceList.get(count));
                    count++;
                }
            }
        }

        for (Piece p :pieceList){
            p.setBoard(this);
        }


    }

    public Spot getSpot(int x, int y) {
        return spots[x][y];
    }

    public List<Piece> getPieceList(){
        return pieceList;
    }

//    public void releaseSpot(int x, int y){
//        Piece releasedPiece = spots[x][y].releaseSpot();
//    }

    public Integer[] pieceImagesBlackIds = {
            R.drawable.rook_black,R.drawable.knight_black,
            R.drawable.bishop_black, R.drawable.king_black,
            R.drawable.queen_black, R.drawable.pawn_black,

    };
    public Integer[] pieceImagesWhiteIds = {
            R.drawable.rook_white,R.drawable.knight_white,
            R.drawable.bishop_white,R.drawable.king_white,
            R.drawable.queen_white,R.drawable.pawn_white
    };
}