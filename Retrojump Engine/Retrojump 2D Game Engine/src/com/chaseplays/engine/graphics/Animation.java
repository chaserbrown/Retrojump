package com.chaseplays.engine.graphics;

import java.util.ArrayList;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.action.Action;
import com.chaseplays.engine.screen.Sprite;

public class Animation {
	
	public ArrayList<Frame> frames = new ArrayList<Frame>();
	
	public Action frameLength;
	
	public int frame = 0;
	
	public boolean finished;
	
	public boolean loop;
	
	public Animation(Object... frames) {
		
		for (int x = 0; x < frames.length / 2; x++) {
			
			this.frames.add(new Frame((String) frames[x * 2], (int) frames[(x * 2) + 1]));
			
		}
		
	}
	
	public void start (boolean loop) {
		
		frameLength = new Action(frames.get(0).duration, 0);
		
		System.out.println(frames.get(0).duration);
		
		frameLength.start();
		
		frame = 0;
		
		finished = false;
		
		this.loop = loop;
		
	}
	
	public void startSimilar (boolean loop) {
		
		finished = false;
		
		this.loop = loop;
		
	}
	
	public void update (Engine e) {
		
		if (frame < frames.size() - 1 && frameLength.able()) {
			
			frame++;
			
			frameLength = new Action(frames.get(frame).duration, 0);
			
			frameLength.start();
			
		} else if (frame == frames.size() - 1 && !loop && frameLength.able()) {
			
			finished = true;
			
			frameLength.stop();
			
			frame = 0;
			
		} else if (frame == frames.size() - 1 && loop && frameLength.able()) {
			
			frame = 0;
			
			frameLength = new Action(frames.get(frame).duration, 0);
			
			frameLength.start();
			
		}
		
	}
	
	public Sprite getSprite (Engine e) {
		
		return this.frames.get(frame).sprite;
		
	}
	
	public ArrayList<Frame> getFramesClone () {
		
		ArrayList<Frame> result = new ArrayList<Frame>();
		
		for (int x = 0; x < frames.size(); x++) result.add(frames.get(x));
		
		return result;
		
	}
	
	public Animation getCopy () {
		
		Animation copy = new Animation();
		
		copy.frames = this.getFramesClone();
		
		copy.frame = this.frame;
		
		copy.finished = this.finished;
		
		copy.loop = this.loop;
		
		return copy;
		
	}
	
}
