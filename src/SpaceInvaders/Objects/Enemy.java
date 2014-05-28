package SpaceInvaders.Objects;

import SpaceInvaders.GUI.SpaceInvadersGame;
import Sprite.Position;
import Sprite.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

/**
 * Enemy extends Object class.
 * @author josemiguelmelo
 */
public class Enemy extends SpaceObject {

    /** Initial enemy life default value */
    public static final int INITIAL_LIFE = 100;
    /** Normal enemy type */
    public static final int FIRESHOOTER = 1;
    /** Suicidal enemy type */
    public static final int SUICIDAL = 2;
    /** Boss enemy type */
    public static final int BOSS = 3;
    /** Destroyer enemy type */
    public static final int DESTROYER = 4;
    /** Enemy current life */
    protected int life;
    /** Represent enemy status (dead or alive) */
    protected boolean dead;
    /** Enemy type */
    protected int type;
    /** Last Movement Date */
    protected ArrayList<Integer> times;
    /** Enemy Shots */
    private ArrayList<Shot> shots;

    /**
     * Enemy default constructor. Enemy type set to its default value -> NORMAL
     * @param position Enemy position on screen.
     * @param sprite Enemy sprite representation.
     */
    public Enemy(Position position, SpriteSheet sprite) {
        super(position, sprite);
        this.life = INITIAL_LIFE;
        this.dead = false;
        this.type = DESTROYER;
        this.shots = new ArrayList<Shot>();
    }
    /**
     * Enemy constructor.
     * @param position Enemy Position on screen.
     * @param sprite Enemy sprite representation.
     * @param type Enemy type.
     */
    public Enemy(Position position, SpriteSheet sprite, int type) {
        super(position, sprite);
        this.life = INITIAL_LIFE;
        this.dead = false;
        this.type = type;
        this.times = new ArrayList<Integer> ();
        times.add(0,(int) System.currentTimeMillis()); // suicidal moves timing
        times.add(1, (int) System.currentTimeMillis()); // last shot fired by Fire Shooter
        times.add(2,(int) System.currentTimeMillis()); // last Y incremented by Fire Shooter
        this.shots = new ArrayList<Shot>();
    }

    /**
     * @param life Enemy life.
     */
    public void setLife(int life){
        this.life = life;
        if(this.life<=0){
            this.dead = true;
        }
    }
    /**
     * @return Enemy life.
     */
    public int getLife(){return this.life;}
    /**
     * @param dead Enemy status (dead or alive).
     */
    public void setDead(boolean dead){ this.dead= dead;}
    /**
     * @return Enemy status (True if dead. False if alive)
     */
    public boolean isDead(){ return this.dead;}
    /**
     * @param type Enemy type.
     */
    public void setType(int type){this.type = type;}
    /**
     * @return Enemy type.
     */
    public int getType(){ return this.type;}
    /**
     * @param damage done to the enemy by the ship
     */
    public void damageTaken(int damage) {
        this.life -= damage;
        if(this.life<=0){
            this.dead=true;
        }
    }

    public void damageDone(SpaceShip spaceShip) {
        switch(type) {
            case DESTROYER:{
                spaceShip.damageShip(100);
            }
            break;
            case SUICIDAL:{
                spaceShip.damageShip(100);
            }
            break;
            case FIRESHOOTER:{
                spaceShip.damageShip(10);
            }
            break;

        }
    }

    public void addShot() {
        shots.add(new Shot(new Position(position.getX(), position.getY()),
                new SpriteSheet(Shot.LOCATION, new Dimension(Shot.SPRITE_DIMENSION, Shot.SPRITE_DIMENSION),1,1),
                10,1,true));
    }

    public ArrayList<Shot> getShots() {return this.shots;}

    /**
     * Move the enemy depending of their type
     */
    public void move(SpaceShip spaceShip) {
        switch (type) {
            case DESTROYER:{
                getPosition().setY(getPosition().getY()+1);
            }
                break;
            case SUICIDAL:{
                getPosition().setY(getPosition().getY()+1);
                if(getPosition().getY()<spaceShip.getPosition().getY()) {
                    int newTime = (int) System.currentTimeMillis();
                    if(newTime-times.get(0) > 5){
                        if(spaceShip.getPosition().getX() > getPosition().getX()){
                            getPosition().setX(getPosition().getX()+1);
                            times.set(0,(int) System.currentTimeMillis());
                        }
                        else if(spaceShip.getPosition().getX() < getPosition().getX()) {
                            getPosition().setX(getPosition().getX()-1);
                            times.set(0,(int) System.currentTimeMillis());
                        }
                    }
                }
            }
                break;
            case FIRESHOOTER:{
                if((int)System.currentTimeMillis() - times.get(1) > 800){
                    if(!isDead()){
                        addShot();
                        times.set(1,(int)System.currentTimeMillis());
                    }
                }
                if((int) System.currentTimeMillis() - times.get(2) > 50) {
                    getPosition().setY(getPosition().getY()+1);
                    times.set(2,(int) System.currentTimeMillis());
                }
                if(getPosition().getY()<spaceShip.getPosition().getY()) {
                    int newTime = (int) System.currentTimeMillis();
                    if(newTime-times.get(0) > 10){
                        if(spaceShip.getPosition().getX() > getPosition().getX()){
                            getPosition().setX(getPosition().getX()+1);
                            times.set(0,(int) System.currentTimeMillis());
                        }
                        else if(spaceShip.getPosition().getX() < getPosition().getX()) {
                            getPosition().setX(getPosition().getX()-1);
                            times.set(0,(int) System.currentTimeMillis());
                        }
                    }
                }
            }
            break;
            case BOSS:{

            }
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(!isDead())
            super.draw(g);
        for(int i = 0; i< shots.size(); i++){
            if(shots.get(i).getPosition().getY() > SpaceInvadersGame.HEIGHT) {
                shots.remove(i);
            } else
                shots.get(i).draw(g);
        }
    }
}
