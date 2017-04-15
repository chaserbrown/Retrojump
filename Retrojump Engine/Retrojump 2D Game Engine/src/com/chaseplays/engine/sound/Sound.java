package com.chaseplays.engine.sound;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	public Clip clip;
	
	public URL url;
	
	public Sound (String soundFile) {
		
		try {
			
			url = this.getClass().getClassLoader().getResource(soundFile);
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			
			clip = AudioSystem.getClip();
			
			clip.open(audioInputStream);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public Sound (Sound sound) {
		
		try {
			
			this.url = sound.url;
			
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			
			clip = AudioSystem.getClip();
			
			clip.open(audioInputStream);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void play() {
		
		if (clip.isRunning()) clip.stop();
		
		clip.setFramePosition(0);
		clip.start();
		
	}
	
	public void loop() {
		
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
	
	public void stop(){
		clip.stop();
	}
	
}
