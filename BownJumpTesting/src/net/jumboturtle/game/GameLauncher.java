package net.jumboturtle.game;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GameLauncher extends Applet{

	public static Game game = new Game();
	public static final boolean DEBUG = false;
	
	public static String age;
	
	public static boolean inputAge = true;
	
	@Override
	public void init(){
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
		setMaximumSize(Game.DIMENSIONS);
		setMinimumSize(Game.DIMENSIONS);
		setPreferredSize(Game.DIMENSIONS);
	}
	
	@Override
	public void start(){
		game.start();
	}
	
	@Override
	public void stop(){
		game.stop();
	}
	
	public static void main(String[] args)
	{
		game.setMinimumSize(Game.DIMENSIONS);
		game.setMaximumSize(Game.DIMENSIONS);
		game.setPreferredSize(Game.DIMENSIONS);
		
		game.frame = new JFrame(Game.NAME);
		
		try
		{
			game.frame.setIconImage(new ImageIcon("res/BownJump.png").getImage());
		}
		catch(NullPointerException e)
		{
			
		}
		
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLayout(new BorderLayout());
		
		game.frame.add(game, BorderLayout.CENTER);
		game.frame.pack();
		
		game.frame.setResizable(false);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.debug = DEBUG;
		new Music("/neut.wav");
		
		game.start();
	}
	
}
