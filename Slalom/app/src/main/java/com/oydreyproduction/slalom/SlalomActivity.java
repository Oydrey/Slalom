package com.oydreyproduction.slalom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;

import androidx.core.view.GestureDetectorCompat;

import java.util.Random;

public class SlalomActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener, Runnable {

    private SlalomView slalomView = null;
    private SlalomEngine slalomEngine = null;

    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        slalomView = new SlalomView(this);
        setContentView(slalomView);

        slalomEngine = new SlalomEngine(this);

        Random randomGenerator = new Random();
        float randomInt = randomGenerator.nextInt(1000);

        Carre carre = new Carre();
        slalomView.setCarre(carre);
        slalomEngine.setCarre(carre);
        slalomEngine.setPositionInitialeCarre(Carre.X_INITIAL, Carre.Y_INITIAL);

        MissileGauche missileGauche = new MissileGauche();
        slalomView.setMissileGauche(missileGauche);
        slalomEngine.setMissileGauche(missileGauche);
        slalomEngine.setPositionInitialMissileGauche(0.00f, randomInt);

        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onPause(){
        super.onStop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (this.mDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent event) {

    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        this.slalomEngine.deplacementCarre(-distanceX, -distanceY);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        return false;
    }

    @Override
    public void run(){
        try
        {
            while(true)
            {
                Thread.sleep(20);
                this.slalomEngine.deplacementMissileGauche(MissileGauche.LONGUEUR_MISSILE);
            }
        }
        catch(InterruptedException ie) {}
    }

}
