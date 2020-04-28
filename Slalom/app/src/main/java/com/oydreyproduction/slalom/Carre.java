package com.oydreyproduction.slalom;

import android.graphics.Color;
import android.graphics.RectF;

public class Carre {

    public static final int LONGUEUR_CARRE = 60;

    public static final float X_INITIAL = 400.00f;
    public static final float Y_INITIAL = 400.00f;

    private int couleur = Color.BLUE;
    public int getCouleur(){
        return this.couleur;
    }

    private static final float VITESSE_MAX = 20.00f;

    private static final float COMPENSATEUR = 10.00f;

    private static final float REBOND = 1.75f;

    private RectF initialCarre = null;

    public void setInitialCarre(RectF initialCarre){
        this.initialCarre = initialCarre;
        this.x = initialCarre.left + LONGUEUR_CARRE;
        this.y = initialCarre.top + LONGUEUR_CARRE;
    }

    private RectF carre = null;

    private float x;
    public float getX(){
        return this.x;
    }
    public void setPosX(float posX){
        this.x = posX;

        if(this.x < 0.00f){
            this.x = 0.00f;
            this.vitesseY = -this.vitesseY / REBOND;
        } else if(this.x > width - LONGUEUR_CARRE){
            this.x = width - LONGUEUR_CARRE;
            this.vitesseY = -this.vitesseY / REBOND;
        }
    }

    private float y;
    public float getY(){
        return this.y;
    }
    public void setPosY(float posY){
        this.y = posY;

        if(this.y < 0.00f){
            this.y = 0.00f;
            this.vitesseX = -this.vitesseX / REBOND;
        } else if(this.y > height - LONGUEUR_CARRE){
            this.y = height - LONGUEUR_CARRE;
            this.vitesseX = -this.vitesseX / REBOND;
        }
    }

    private float vitesseX = 0.00f;
    public void changeVitesseX(){
        this.vitesseX = -this.vitesseX;
    }

    private float vitesseY = 0.00f;
    public void changeVitesseY(){
        this.vitesseY = -this.vitesseY;
    }

    private int height = -1;
    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return this.height;
    }

    private int width = -1;
    public void setWidth(int width){
        this.width = width;
    }
    public int getWidth(){
        return this.width;
    }

    public Carre(){
        this.carre = new RectF();
    }

    public RectF putXAndY(float x, float y){
        this.vitesseX += x / COMPENSATEUR;
        if(this.vitesseX > VITESSE_MAX){
            this.vitesseX = VITESSE_MAX;
        }
        if(this.vitesseX < -VITESSE_MAX){
            this.vitesseX = -VITESSE_MAX;
        }

        this.vitesseY += y / COMPENSATEUR;
        if(this.vitesseY > VITESSE_MAX){
            this.vitesseY = VITESSE_MAX;
        }
        if(vitesseY < -VITESSE_MAX){
            this.vitesseY = -VITESSE_MAX;
        }

        this.setPosX(this.x + this.vitesseX);
        this.setPosY(this.y + this.vitesseY);

        this.carre.set(this.x - LONGUEUR_CARRE, this.y - LONGUEUR_CARRE, this.x + LONGUEUR_CARRE, this.y + LONGUEUR_CARRE);

        return this.carre;
    }

    public void reset(){
        this.vitesseX = 0.00f;
        this.vitesseY = 0.00f;
        this.x = initialCarre.left + LONGUEUR_CARRE;
        this.y = initialCarre.top + LONGUEUR_CARRE;
    }

}
