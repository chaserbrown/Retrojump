package net.jumboturtle.game;

import sun.audio.*;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Music {
	
	public Music(String directory)
	{
			AudioStream BGM;
		
		try{
		InputStream test = this.getClass().getResourceAsStream(directory);
		BGM = new AudioStream(test);
		AudioPlayer.player.start(BGM);
		}catch(Exception error){
			System.out.println(error);
		}
	}
}
