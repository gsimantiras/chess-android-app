package com.example.geo.gameenginechess;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.PersistableBundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geo.gameenginechess.Pieces.Bishop;
import com.example.geo.gameenginechess.Pieces.King;
import com.example.geo.gameenginechess.Pieces.Knight;
import com.example.geo.gameenginechess.Pieces.Pawn;
import com.example.geo.gameenginechess.Pieces.Piece;
import com.example.geo.gameenginechess.Pieces.Queen;
import com.example.geo.gameenginechess.Pieces.Rook;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView chessBoard;
    private SquareAdapter squareAdapter;
    private Game myGame;
    private Piece firstPieceSelected = null;
    private Piece secondPieceSelected = null;
    private int firstPosition = -1;
    private ImageView firstV;
    private Spot firstSpotSelected= null;
    private Button unSelectSoldierBtn;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGame = new Game(this);

        squareAdapter = new SquareAdapter(this, myGame.getBoard());

        chessBoard = (GridView) findViewById(R.id.chessBoard);
        chessBoard.setAdapter(squareAdapter);
        chessBoard.setOnItemClickListener(this);

        unSelectSoldierBtn = (Button) findViewById(R.id.unSelectSoldierBtn);
        unSelectSoldierBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unSelectSoldier();
            }
        });
        Toast.makeText(this, "White Player first", Toast.LENGTH_SHORT).show();
    }

    private void unSelectSoldier() {
        firstPieceSelected = null;
        firstPosition= -1;
        unSelectSoldierBtn.setVisibility(View.GONE);
    }

    private boolean isEnemy(Piece from, Piece to){
        if(from.getColor().equals(to.getColor())) return false;
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {

        int x = position / 8;
        int y = position % 8;
        Spot selectedSpot = myGame.getBoard().getSpot(x, y);
        String nextPlayer;
        if (myGame.getNextPlayer().white){
            //white playing
            nextPlayer = "White";
        }else{
            //black playing
            nextPlayer = "Black";
        }

        try{

            if (firstPieceSelected == null) {
                //select first piece
                if (selectedSpot.isOccupied() && selectedSpot.piece.getColor().equals(nextPlayer)) {
                    firstPieceSelected = selectedSpot.piece;
                    firstPosition = position;
                    firstV = (ImageView) v;
                    firstSpotSelected = selectedSpot;

                    Log.d(TAG, "piece selected:" + selectedSpot.piece.getColor() + "-" + selectedSpot.piece.getImageId()
                            + "-(" + selectedSpot.piece.getX() + "," + selectedSpot.piece.getY() + ")");
                    unSelectSoldierBtn.setVisibility(View.VISIBLE);
                }else{
                    //empty spot, cant do anything
                    Log.d(TAG, "spot is not occupied by a piece:"+ selectedSpot.x + "," + selectedSpot.y );
                }
            }else {
                // first piece is selected, check if valid movement
                // check if its enemy, kill, else check if it can move there
                ImageView toView= (ImageView) v;
                if (selectedSpot.isOccupied()) {
                    secondPieceSelected = selectedSpot.piece;

                    if (isEnemy(firstPieceSelected, secondPieceSelected)) {
                        if (firstPieceSelected.getPieceName().equals("Pawn")) {
                            Pawn pawn = (Pawn) firstPieceSelected;
                            if (pawn.isValidToKill(pawn.getX(), pawn.getY(), selectedSpot.x, selectedSpot.y)) {
                                //visual Kill by Pawn, take place
                                switchPositions(selectedSpot, toView,nextPlayer);
                            }else{
                                Log.d(TAG,"not a valid kill move for Pawn");
                            }
                        } else {
                            // visual Kill, take place
                            if (firstPieceSelected.isValid(firstPieceSelected.getX(), firstPieceSelected.getY(), selectedSpot.x, selectedSpot.y)) {
                                switchPositions(selectedSpot,toView, nextPlayer);
                            }else{
                                Log.d(TAG,"2nd not a valid kill move for" + firstPieceSelected.getPieceName());
                            }
                        }
                    } else {
                        //can't kill friendly piece
                        Toast.makeText(this, "This " + secondPieceSelected.getPieceName() +
                                " is a friendly piece.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // visual move to empty spot
                    if (firstPieceSelected.isValid(firstPieceSelected.getX(), firstPieceSelected.getY(), selectedSpot.x, selectedSpot.y)) {
                        switchPositions(selectedSpot,toView, nextPlayer);

                    } else {
                        //not a valid movement
                        Toast.makeText(this, "not a valid move at (" + selectedSpot.x + "," + selectedSpot.y +
                                ") for this piece: " + firstPieceSelected.getPieceName() + "(" + firstPieceSelected.getX() + ","
                                + firstPieceSelected.getY() + ")", Toast.LENGTH_LONG).show();
                        Log.d(TAG, "not a valid move at (" + selectedSpot.x + "," + selectedSpot.y +
                                ") for this piece: " + firstPieceSelected.getPieceName() + "(" + firstPieceSelected.getX() + ","
                                + firstPieceSelected.getY() + ")");
                    }
                }
            }
        }catch (Exception e){
            Log.d(TAG,"move error"+e.getMessage());
        }
//            ImageView selectedPosition = (ImageView) v;
//        Log.d(TAG,"id: "+selectedPosition.getId());
//        if (selectedPosition.getDrawable() == null ){
//                //selecting an empty block
//            if (fromCell == null ) {
//                    //selecting an empty block, please select soldier first
//                Toast.makeText(MainActivity.this, "Select a soldier first.", Toast.LENGTH_SHORT).show();
//            } else {
//                try{
//                    switch (selectedPosition.getId()){
//                        case -1:
//                            selectedPiece =null;
//                            break;
//                        case 0:
//                            selectedPiece = (Rook) chessBoard.getAdapter().getItem(position);
//                            break;
//                        case 1:
//                            selectedPiece = (Knight) chessBoard.getAdapter().getItem(position);
//                            break;
//                        case 2:
//                            selectedPiece = (Bishop) chessBoard.getAdapter().getItem(position);
//                            break;
//                        case 3:
//                            selectedPiece = (King) chessBoard.getAdapter().getItem(position);
//                            break;
//                        case 4:
//                            selectedPiece = (Queen) chessBoard.getAdapter().getItem(position);
//                            break;
//                        case 5:
//                            selectedPiece = (Pawn) chessBoard.getAdapter().getItem(position);
//                            break;
//                        default:
//                            selectedPiece = null;
//                            break;
//                        }
//                    Log.d(TAG,"selectedPiece:" + selectedPiece.getImageId());
//                    if (fromPosition != -1){
//                            //check if i can move to the empty block
//                            //working old pawn move
//    //                        if (position == fromPosition + 8 || position == fromPosition - 8) {
//    //                            Toast.makeText(MainActivity.this, "moving soldier from " + fromPosition+
//    //                                    " to" + position, Toast.LENGTH_SHORT).show();
//    //
//    //                            switchPositions(selectedPosition);
//    //                            unSelectSoldier();
//
//                        Log.d(TAG,"move from: " + fromX +"/"+fromY + "to: " + toX +"/"+ toY);
//                            if(selectedPiece.isValid(fromX,fromY,toX,toY)){
//                                switchPositions(selectedPosition);
//                                unSelectSoldier();
//                            } else {
//                                Toast.makeText(MainActivity.this, "can't move to that position.", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }catch (Exception e){
//                        Log.d(TAG,"selectedPiece error:" +e.getMessage());
//                    }
//                }
//            }else{
//                if (fromCell == null){
//                    //check if it's your round
//                    fromCell = selectedPosition;
//                    fromPosition = position;
//                    unSelectSoldierBtn.setVisibility(View.VISIBLE);
////                        Toast.makeText(MainActivity.this, "selected soldier at: " + position, Toast.LENGTH_SHORT).show();
//                }else{
//                    //check positions and kill soldier
//                    if (fromPosition != -1){
////                        if (position == fromPosition + 7 ||
////                                position == fromPosition + 9 ||
////                                position == fromPosition - 7 ||
////                                position == fromPosition - 9) {
////                            if(isEnemy(fromCell.getDrawable(), selectedPosition.getDrawable())){
////                                switchPositions(selectedPosition);
//////                                    Toast.makeText(MainActivity.this, "killing soldier at: " + position, Toast.LENGTH_SHORT).show();
////                            }else{
////                                Toast.makeText(MainActivity.this, "friendly soldier." , Toast.LENGTH_SHORT).show();
////                            }
////                        }else if(fromCell == selectedPosition){
////                            unSelectSoldier();
//////                                Toast.makeText(MainActivity.this, "unselected soldier at: " + position, Toast.LENGTH_SHORT).show();
////                        }
////                        else{
////                            Toast.makeText(MainActivity.this, "position occuppied.", Toast.LENGTH_SHORT).show();
////                        }
//                    }
//                }
//            }
//        }
    }

    private void switchPositions(Spot selectedSpot, ImageView toView, String nextPlayer) {

        Piece releasedSpotPiece = firstSpotSelected.releaseSpot();
        selectedSpot.occupySpot(releasedSpotPiece);

        firstPieceSelected.setX(selectedSpot.x);
        firstPieceSelected.setY(selectedSpot.y);


        if(nextPlayer.equals("White")){
            toView.setImageResource(squareAdapter.getPieceImagesWhiteIds()[firstPieceSelected.getImageId()]);
        }else{
            toView.setImageResource(squareAdapter.getPieceImagesBlackIds()[firstPieceSelected.getImageId()]);
        }
        firstV.setImageDrawable(null);
        firstV.setImageResource(0);
        unSelectSoldier();

        int round = myGame.nextRound();
        Log.d(TAG,"move previous spot: "+ firstSpotSelected.x + ","+firstSpotSelected.y + " - "+ firstSpotSelected.isOccupied());
        Log.d(TAG,"move new spot: "+ selectedSpot.x + ","+selectedSpot.y + " - "+ selectedSpot.isOccupied());
        Toast.makeText(this, round + ": " +myGame.getNextPlayer().playerColor + "'s move.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            })
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.super.onBackPressed();
                }
            }).setMessage("Are you sure you want to close the app?")
            .setTitle("Leaving Game")
            .show();

    }

}

