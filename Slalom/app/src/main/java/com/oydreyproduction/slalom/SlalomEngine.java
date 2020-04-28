package com.oydreyproduction.slalom;

import android.graphics.RectF;

public class SlalomEngine {

    private Carre carre = null;
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

    private SlalomActivity slalomActivity = null;

    public SlalomEngine(SlalomActivity slalomActivity){
        this.slalomActivity = slalomActivity;
    }

    public void deplacementCarre(float x, float y){
         if(carre != null){
            RectF hitBoxCarre = carre.putXAndY(x, y);
        }
    }

    public void deplacementMissileGauche(float x){
        if(missileGauche != null){
            RectF hitBoxMissileGauche = missileGauche.putX(x);
        }
    }

    public void setPositionInitialeCarre(float x, float y){
        RectF posIni = new RectF();
        posIni.set(x, y, x + Carre.LONGUEUR_CARRE, y + Carre.LONGUEUR_CARRE);
        carre.setInitialCarre(posIni);
    }

    public void setPositionInitialMissileGauche(float x, float y){
        RectF posIni = new RectF();
        posIni.set(x, y, x + MissileGauche.LONGUEUR_MISSILE, y + MissileGauche.HAUTEUR_MISSILE);
        missileGauche.setPositionInitiale(posIni);
    }

    public void reset(){
        this.carre.reset();
        this.missileGauche.reset();
    }

}
