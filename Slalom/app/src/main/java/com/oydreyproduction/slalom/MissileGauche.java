package com.oydreyproduction.slalom;

import android.graphics.Color;
import android.graphics.RectF;

public class MissileGauche {

    public static final int LONGUEUR_MISSILE = 30;
    public static final int HAUTEUR_MISSILE = 10;

    private int couleur = Color.RED;
    public int getCouleur(){
        return this.couleur;
    }

    private float vitesse = 10.00f;
    public float getVitesse(){
        return this.vitesse;
    }
    public void setVitesse(float vitesse){
        this.vitesse = vitesse;
    }

    private RectF positionInitiale = null;

    public void setPositionInitiale(RectF positionInitiale){
        this.positionInitiale = positionInitiale;
        this.x = positionInitiale.left + LONGUEUR_MISSILE;
        this.y = positionInitiale.top + HAUTEUR_MISSILE;
    }

    private RectF missileGauche = null;

    private float x;
    public float getX(){
        return this.x;
    }
    public void setPosX(float posX){
        this.x = posX;
    }

    private float y;
    public float getY(){
        return this.y;
    }
    public void setPosY(float posY){
        this.y = posY;
    }

    private int width = -1;
    public void setWidth(int width){
        this.width = width;
    }
    public int getWidth(){
        return this.width;
    }

    public MissileGauche(){
        this.missileGauche = new RectF();
    }

    public RectF putX(float x){
        this.vitesse += x;
        this.setPosX(this.x + this.vitesse);
        this.missileGauche.set(this.x - LONGUEUR_MISSILE, this.y - HAUTEUR_MISSILE, this.x + LONGUEUR_MISSILE, this.y + HAUTEUR_MISSILE);
        return this.missileGauche;
    }

    public void reset(){
        this.vitesse = 0.00f;
        this.x = positionInitiale.left + LONGUEUR_MISSILE;
        this.y = positionInitiale.top + HAUTEUR_MISSILE;
    }

}
