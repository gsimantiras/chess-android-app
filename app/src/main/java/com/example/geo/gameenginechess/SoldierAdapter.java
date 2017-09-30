package com.example.geo.gameenginechess;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.geo.gameenginechess.Pieces.Bishop;
import com.example.geo.gameenginechess.Pieces.King;
import com.example.geo.gameenginechess.Pieces.Knight;
import com.example.geo.gameenginechess.Pieces.Pawn;
import com.example.geo.gameenginechess.Pieces.Piece;
import com.example.geo.gameenginechess.Pieces.Queen;
import com.example.geo.gameenginechess.Pieces.Rook;

import java.util.ArrayList;

/**
 * Created by Geo on 27/3/2017.
 */


    public class SoldierAdapter extends BaseAdapter {
        private ArrayList<Piece> pieceList;
        private static final String TAG = "";
        private Context mContext;

        public SoldierAdapter(Context c,SquareAdapter squareAdapter) {
            mContext = c;

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
                    Pawn pawn = new Pawn(true,0,i);
                    pieceList.add(pawn);
                }
                for (int i=0; i<8;i++){
                    Pawn pawn = new Pawn(true,7,i);
                    pieceList.add(pawn);
                }

                Rook rookRed1 = new Rook(true,7,0);
                pieceList.add(rookRed1);
                Knight knightRed1 =  new Knight(true,7,1);
                pieceList.add(knightRed1);
                Bishop bishopRed1 = new Bishop(true,7,2);
                pieceList.add(bishopRed1);
                King kingRed = new King(true,7,4);
                pieceList.add(kingRed);
                Queen queenRed = new Queen(true,7,3);
                pieceList.add(queenRed);
                Bishop bishopRed2 = new Bishop(true,7,5);
                pieceList.add(bishopRed2);
                Knight knightRed2 =  new Knight(true,7,6);
                pieceList.add(knightRed2);
                Rook rookRed2 = new Rook(true,7,7);
                pieceList.add(rookRed2);



                int x=0;
                for (int i=0; i<32; i++){
                    Piece p = pieceList.get(i);
                    ImageView imageView = new ImageView(c);
                    Log.d(TAG,x + "");
                    if (i<7){
                        if (i<=4){
                            imageView.setBackgroundResource(pieceImagesBlackIds[x]);
                            p.setPieceImage(imageView);
                            x++;
                        }else{
                            x-=2;
                            imageView.setBackgroundResource(pieceImagesBlackIds[i-x]);
                            p.setPieceImage(imageView);
                        }
                    }
                    else if ((i>7) && (i<16)){
                        imageView.setBackgroundResource(pieceImagesBlackIds[5]);
                        p.setPieceImage(imageView);
                    }else if ((i>15) && (i<24))
                    {
                        imageView.setBackgroundResource(pieceImagesWhiteIds[5]);
                        p.setPieceImage(imageView);
                        x=0;
                    }
                    else if((i>23) && (i<32)){
                        if (i<=28){
                            imageView.setBackgroundResource(pieceImagesWhiteIds[x]);
                            p.setPieceImage(imageView);
                            x++;
                        }else{
                            x--;
                            imageView.setBackgroundResource(pieceImagesWhiteIds[x-2]);
//                            imageView.setBackgroundResource(pieceImagesWhiteIds[5]);
                            p.setPieceImage(imageView);
                        }
                    }

                }

                for(int i=0; i<32;i++){
                    if(i<16){
                        //black

                    }else{
                        //white
                    }
                }
            }catch (Exception e){
                Log.d(TAG, e.getMessage(),e );
            }
        }

        public Object getItem(int position){
            return pieceList.get(position);
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
//                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(125, 125));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0,0,0,0);
            } else {
                imageView = (ImageView) convertView;
            }

//            if (position < 8 ) {
//                imageView.setImageResource(mThumbIds[0]);
//            }else if ( position >= 24){
//                imageView.setImageResource(mThumbIds[1]);
//            }
            return imageView;
        }

        public int getCount() {
            return 32;
        }

//        private Integer[] mThumbIds = {
//                R.drawable.soldier_blue, R.drawable.soldier_red,
//        };

    private Integer[] pieceImagesBlackIds = {
            R.drawable.rook_black,R.drawable.knight_black,
            R.drawable.bishop_black, R.drawable.king_black,
            R.drawable.queen_black, R.drawable.pawn_black,

    };
    private Integer[] pieceImagesWhiteIds = {
            R.drawable.rook_white,R.drawable.knight_white,
            R.drawable.bishop_white,R.drawable.queen_white,
            R.drawable.king_white,R.drawable.pawn_white
    };

    }

