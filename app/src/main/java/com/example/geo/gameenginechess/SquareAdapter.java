package com.example.geo.gameenginechess;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.geo.gameenginechess.Pieces.Piece;

import java.util.List;


/**
 * Created by Geo on 27/3/2017.
 */

public class SquareAdapter extends BaseAdapter {
    private Board mBoard;
    private Context mContext;
    private boolean switcher=false;
    private static final String TAG = SquareAdapter.class.getSimpleName();


    public SquareAdapter(Context c, Board board) {
        mContext = c;
        mBoard = board;
    }

    public Object getItem(int position){
//        return this.pieceList.get(position);
        return null;

    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        try{
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(125, 125));
                if (position % 8 ==0 ){
                    switcher= !switcher;
                }
                if (switcher) {
                    imageView.setBackgroundColor(Color.WHITE);
                }else{
                    imageView.setBackgroundColor(Color.LTGRAY);
                }
                switcher=!switcher;

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(0,0,0,0);
            } else {
                imageView = (ImageView) convertView;
            }
            List<Piece> pieceList =  mBoard.getPieceList();
            if (position<16){
                imageView.setImageResource(pieceImagesBlackIds[pieceList.get(position).getImageId()]);
                imageView.setId(pieceList.get(position).getImageId());
            }else if (position>=48){
                imageView.setImageResource(pieceImagesWhiteIds[pieceList.get(position-32).getImageId()]);
                imageView.setId(pieceList.get(position-32).getImageId());
            }else{
                imageView.setId(-1);
            }

            return imageView;
        }catch (Exception e){
            Log.d(TAG,e.getMessage());
        }
        return null;
    }

    public int getCount() {
        return 64;
    }

    private Integer[] pieceImagesBlackIds = {
            R.drawable.rook_black,R.drawable.knight_black,
            R.drawable.bishop_black, R.drawable.king_black,
            R.drawable.queen_black, R.drawable.pawn_black,

    };
    private Integer[] pieceImagesWhiteIds = {
            R.drawable.rook_white,R.drawable.knight_white,
            R.drawable.bishop_white,R.drawable.king_white,
            R.drawable.queen_white,R.drawable.pawn_white
    };
    public Integer[] getPieceImagesBlackIds(){
        return pieceImagesBlackIds;
    }


    public Integer[] getPieceImagesWhiteIds() {
        return pieceImagesWhiteIds;
    }
}
