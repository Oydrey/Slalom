package com.oydreyproduction.slalom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SlalomView extends SurfaceView implements SurfaceHolder.Callback {

    Carre carre;
    public Carre getCarre(){
        return this.carre;
    }
    public void setCarre(Carre carre){
        this.carre = carre;
    }

    MissileGauche missileGauche;
    public MissileGauche getMissileGauche(){
        return this.missileGauche;
    }
    public void setMissileGauche(MissileGauche missileGauche){
        this.missileGauche = missileGauche;
    }

    SurfaceHolder surfaceHolder;
    DrawingThread thread;

    Paint  paint;

    public SlalomView(Context context){
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.thread = new DrawingThread();

        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);

        this.carre = new Carre();
        this.missileGauche = new MissileGauche();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.WHITE);

        if(this.carre != null){
            this.paint.setColor(this.carre.getCouleur());
            canvas.drawRect(this.carre.getX(), this.carre.getY(), this.carre.getX() + Carre.LONGUEUR_CARRE, this.carre.getY() + Carre.LONGUEUR_CARRE, this.paint);
        }

        if(this.missileGauche != null){
            this.paint.setColor(this.missileGauche.getCouleur());
            canvas.drawRect(this.missileGauche.getX(), this.missileGauche.getY(), this.missileGauche.getX() + MissileGauche.LONGUEUR_MISSILE, this.missileGauche.getY() + MissileGauche.HAUTEUR_MISSILE, this.paint);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){
        this.thread.keepDrawing = true;
        this.thread = new DrawingThread();
        this.thread.start();
        if(this.carre != null){
            this.carre.setHeight(getHeight());
            this.carre.setWidth(getWidth());
        }
        if(this.missileGauche != null){
            this.missileGauche.setWidth(getWidth());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        this.thread.keepDrawing = false;
        boolean retry = true;
        while(retry){
            try{
                this.thread.join();
                retry = false;
            } catch (InterruptedException e){}
        }
    }

    private class DrawingThread extends Thread {

        boolean keepDrawing = true;

        @SuppressLint("WrongCall")
        @Override
        public void run(){
            Canvas canvas;
            while(keepDrawing){
                canvas = null;

                try{
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder){
                        onDraw(canvas);
                    }
                } finally {
                    if(canvas != null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

                try{
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
            }
        }

    }

}
