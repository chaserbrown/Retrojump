package com.chaseplays.test.test1;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.action.Action;
import com.chaseplays.engine.graphics.Animation;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.engine.single.*;

public class Player extends rEntity {
	
	public int direction = 1; // -1 is left, 1 is right
	
	public String type = "wasd"; // wasd or arrow
	
	public Action walking = new Action(150, 0);
	
	public Action move = new Action(10, 0);
	
	public static final Animation runLeft = new Animation("/images/sprites/characters/16x16/aaronRunLeft1.png", 200, "/images/sprites/characters/16x16/aaronRunLeft2.png", 200, "/images/sprites/characters/16x16/aaronRunLeft3.png", 200, "/images/sprites/characters/16x16/aaronRunLeft4.png", 200);
	public static final Animation runRight = new Animation("/images/sprites/characters/16x16/aaronRunRight1.png", 200, "/images/sprites/characters/16x16/aaronRunRight2.png", 200, "/images/sprites/characters/16x16/aaronRunRight3.png", 200, "/images/sprites/characters/16x16/aaronRunRight4.png", 200);
	
	public static final Animation still = new Animation("/images/sprites/characters/16x16/aaronRunRight1.png", 1000);
	
	public void setup (Engine e) {
		
		walking.start();
		move.start();
		
		setDefaultAnimation(still);
		
		this.x = 0;
		this.y = 50;
		
	}
	
	public void update (Engine e) {
		
		if (move.able()) {
			
			if ((type.equals("wasd") && e.key.a.pressed) || ((type.equals("arrow")) && e.key.left.pressed)) {
				
				x--;
				
			}
			
			if ((type.equals("wasd") && e.key.d.pressed) || ((type.equals("arrow")) && e.key.right.pressed)) {
				
				x++;
				
			}
			
			if ((type.equals("wasd") && e.key.w.pressed) || ((type.equals("arrow")) && e.key.up.pressed)) {
				
				y--;
				
			}
			
			if ((type.equals("wasd") && e.key.s.pressed) || ((type.equals("arrow")) && e.key.down.pressed)) {
				
				y++;
				
			}
			
			move.gather();
			
		}
		
		if ((type.equals("wasd") && e.key.left.pressed) || ((type.equals("arrow")) && e.key.a.pressed)) {
			
			direction = -1;
			
			if (this.animation.frames.equals(runRight.frames)) playSimilar(runLeft, true);
			else play(runLeft, true);
			
		} else if ((type.equals("wasd") && e.key.right.pressed) || ((type.equals("arrow")) && e.key.d.pressed)) {
			
			direction = 1;
			
			if (this.animation.frames.equals(runLeft.frames)) playSimilar(runRight, true);
			else play(runRight, true);
			
		} else {
			
			resortIfEquals(runRight);
			resortIfEquals(runLeft);
			
		}
		
	}
	
}
