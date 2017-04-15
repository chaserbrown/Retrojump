package net.jumboturtle.game.entities;

import net.jumboturtle.game.level.Level;

public abstract class Mob extends Entity{

	protected String name;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1; 
	
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name = name;
		this.speed = speed;
		this.x = x;
		this.y = y;
	}
	
	public void move(int xa, int ya){
		numSteps++;
		if(!hasCollided(xa, ya)){
			if(ya < 0){
				movingDir = 0;
			}
			if(ya > 0){
				movingDir = 1;
			}
			if(xa < 0){
				movingDir = 2;
			}
			if(xa > 0){
				movingDir = 3;
			}
			x += xa * speed;
			y += ya * speed;
		}
	}
	
	public abstract boolean hasCollided(int xa, int ya);
	
	public String getName(){
		return name;
	}
	
}
