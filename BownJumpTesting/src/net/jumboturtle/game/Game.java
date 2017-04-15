package net.jumboturtle.game;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.jumboturtle.game.entities.*;
import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Font;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.gfx.SpriteSheet;
import net.jumboturtle.game.level.Level;
import net.jumboturtle.game.level.tiles.BasicTile;
import net.jumboturtle.game.level.tiles.Tile;

public class Game extends Canvas implements Runnable{ //Lots of credit to DesignsByZephyr! I couldn't do this without his Youtube tutorials!

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 96;
	public static final int HEIGHT = 120;
	public static final int SCALE = 5;
	public static final String NAME = "The Bown Jump Game";
	
	public static final Dimension DIMENSIONS = new Dimension(WIDTH*SCALE,HEIGHT*SCALE);
	
	public static int countdown = 3;
	public static int countdownagain = 2;
	public static int playerDeaths = 0;
	public static int scores = 0;
	public static int highScore = 0;
	
	public static int highgame1;
	public static int highgame2;
	public static int highgame3;
	public static int highgame4;
	
	public static int deathgame1;
	public static int deathgame2;
	public static int deathgame3;
	public static int deathgame4;
	
	public static int player1model = 0;
	public static int player2model = 0;
	
	public static int playermodelmax = 7;
	
	public static int gamemode = 0;
	
	public static int fakerandom = 3;
	public static int fakerandomizer = 0;
	
	public static boolean isShowering;
	public static boolean isPowerup;
	public static boolean isTossing;
	public static boolean isFire1;
	public static boolean isFire2;
	
	public static boolean isSelecting1;
	public static boolean isSelecting2;
	
	public static int lrcannon;
	
	public static int plevel;
	
	public static boolean gamestarted = false;
	
	public static long lastTimer = System.currentTimeMillis();
	public static long animeTimer = System.currentTimeMillis();
	public static long platformTimer = System.currentTimeMillis();
	public static long firstTimer = System.currentTimeMillis();
	public static long audioTimer = System.currentTimeMillis();
	public static long switchTimer = System.currentTimeMillis();
	public static long hubTimer = System.currentTimeMillis();
	public static long charTimer = System.currentTimeMillis();
	
	JFrame frame;
	
	private Thread thread;
	
	public boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[36 * 6];
	
	private Screen screen;
	public InputHandler input;
	
	public Level level;
	
	public static Player player;
	public static Player2 player2;
	
	public static Platform1 platform1;
	public static Platform2 platform2;
	public static Platform3 platform3;
	public static Platform4 platform4;
	public static Platform5 platform5;
	public static Platform6 platform6;
	public static Platform7 platform7;
	public static Platform8 platform8;
	public static Platform9 platform9;
	public static Platform10 platform10;
	public static Platform11 platform11;
	public static Platform12 platform12;
	public static Platform13 platform13;
	public static Platform14 platform14;
	public static Platform15 platform15;
	public static Platform16 platform16;
	public static Meteor meteor;
	public static Powerup powerup;
	public static Cannonball cannonball;
	public static Fire1 fire1;
	public static Fire2 fire2;
	
	public static boolean Tplatform1 = true, Tplatform2 = true, Tplatform3 = true, Tplatform4 = true, Tplatform5 = true, Tplatform6 = true, Tplatform7 = true, Tplatform8 = true; //Platform Toggles (Existent or Nonexistent)
	public static boolean Tplatform9 = false, Tplatform10 = false, Tplatform11 = false, Tplatform12 = false, Tplatform13 = false, Tplatform14 = false, Tplatform15 = false, Tplatform16 = false; //Platform Toggles (Existent or Nonexistent)
	
	public boolean debug = true;
	
	public static int randomTempo = 0;
	
	public static int difficulty = 25;
	
	public static boolean platformhub1 = false;
	public static boolean platformhub2 = false;
	public static boolean platformhub3 = false;
	public static boolean platformhub4 = false;
	public static boolean platformhub5 = false;
	public static boolean platformhub6 = false;
	public static boolean platformhub7 = false;
	public static boolean platformhub8 = false;
	public static boolean platformhub9 = false;
	public static boolean platformhub10 = false;
	public static boolean platformhub11 = false;
	public static boolean platformhub12 = false;
	public static boolean platformhub13 = false;
	public static boolean platformhub14 = false;
	public static boolean platformhub15 = false;
	public static boolean platformhub16 = false;
	
	public static String[] name = new String[100000];
	
	public void init(){
		int index = 0;
		for(int r = 0; r < 6; r++){
			for(int g = 0; g < 6; g++){
				for(int b = 0; b < 6; b++){
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);
					
					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet("/spritesheet.png"));
		input = new InputHandler(this);
		level = new Level(12, 15);
		
		player = new Player(level, 44, 54, input);
		player2 = new Player2(level, 64, 84, input);
		
		meteor = new Meteor(level, 16, -10, input);
		powerup = new Powerup(level, 16, -10, input);
		platform1 = new Platform1(level, 16, 100, input);
		platform2 = new Platform2(level, 24, 100, input);
		platform3 = new Platform3(level, 32, 100, input);
		platform4 = new Platform4(level, 40, 100, input);
		platform5 = new Platform5(level, 48, 100, input);
		platform6 = new Platform6(level, 56, 100, input);
		platform7 = new Platform7(level, 64, 100, input);
		platform8 = new Platform8(level, 72, 100, input);
		
		platform9 = new Platform9(level, 16, 10, input);
		platform10 = new Platform10(level, 24, 10, input);
		platform11 = new Platform11(level, 32, 10, input);
		platform12 = new Platform12(level, 40, 10, input);
		platform13 = new Platform13(level, 48, 10, input);
		platform14 = new Platform14(level, 56, 10, input);
		platform15 = new Platform15(level, 64, 10, input);
		platform16 = new Platform16(level, 72, 10, input);
		
		cannonball = new Cannonball(level, 90, -20, input);
		fire1 = new Fire1(level, 4, 75, input);
		fire2 = new Fire2(level, 84, 75, input);
		
		level.addEntity(platform9);
		level.addEntity(platform10);
		level.addEntity(platform11);
		level.addEntity(platform12);
		level.addEntity(platform13);
		level.addEntity(platform14);
		level.addEntity(platform15);
		level.addEntity(platform16);
		level.addEntity(platform1);
		level.addEntity(platform2);
		level.addEntity(platform3);
		level.addEntity(platform4);
		level.addEntity(platform5);
		level.addEntity(platform6);
		level.addEntity(platform7);
		level.addEntity(platform8);
		level.addEntity(player);
		level.addEntity(player2);
		level.addEntity(meteor);
		level.addEntity(powerup);
		level.addEntity(cannonball);
		level.addEntity(fire1);
		level.addEntity(fire2);
		
		name[0] = "BOWN";
		name[1] = "GHOST";
		name[2] = "ORB";
		name[3] = "GUMDROP GUY";
		name[4] = "TASEY";
		name[5] = "DIGLETT";
		name[6] = "TURTLE BOX";
		name[7] = "BLOXORZ";
		
		resetHub(false);
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, NAME+"_main");
		randomTempo = randomTempo();
		thread.start();
	}
	
	public synchronized void stop() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60;
		int ticks = 0;
		int frames = 0;
		
		lastTimer = System.currentTimeMillis();
		animeTimer = System.currentTimeMillis();
		platformTimer = System.currentTimeMillis();
		firstTimer = System.currentTimeMillis();
		audioTimer = System.currentTimeMillis();
		switchTimer = System.currentTimeMillis();
		hubTimer = System.currentTimeMillis();
		double delta = 0;

		init();

			while(running)
			{
				long now = System.nanoTime();
				delta += (now - lastTime) / nsPerTick;
				lastTime = now;
				boolean shouldRender = true;
				
				while(delta >= 1){
					ticks++;
					tick();
					delta -= 1;
					shouldRender = true;
				}
				
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					
				}
				if(shouldRender){
					frames++;
					render();
				}
				if(System.currentTimeMillis() - charTimer >= 250)
				{
					if(isSelecting1)
					{
						if(input.right.isPressed() || input.right2.isPressed())
						{
							if(player1model < playermodelmax)
							{
								player1model++;
							}
							else
							{
								player1model = 0;
							}
							
						}
						else if(input.left.isPressed() || input.left2.isPressed())
						{
							if(player1model > 0)
							{
								player1model--;
							}
							else
							{
								player1model = playermodelmax;
							}
						}
					}
					if(isSelecting2)
					{
						if(input.right.isPressed() || input.right2.isPressed())
						{
							if(player2model < playermodelmax)
							{
								player2model++;
							}
							else
							{
								player2model = 0;
							}
							
						}
						else if(input.left.isPressed() || input.left2.isPressed())
						{
							if(player2model > 0)
							{
								player2model--;
							}
							else
							{
								player2model = playermodelmax;
							}
						}
					}
					charTimer+=250;
				}
				if(System.currentTimeMillis() - audioTimer >= 75) {
					if(!gamestarted)
					{
						if(platform1.y == 91)
						{
							platformhub1 = true;
						}
						else if(platform1.y == 70)
						{
							platformhub1 = false;
						}
						
						if(platform2.y == 91)
						{
							platformhub2 = true;
						}
						else if(platform2.y == 70)
						{
							platformhub2 = false;
						}
						
						if(platform3.y == 91)
						{
							platformhub3 = true;
						}
						else if(platform3.y == 70)
						{
							platformhub3 = false;
						}
						
						if(platform4.y == 91)
						{
							platformhub4 = true;
						}
						else if(platform4.y == 70)
						{
							platformhub4 = false;
						}
						
						if(platform5.y == 91)
						{
							platformhub5 = true;
						}
						else if(platform5.y == 70)
						{
							platformhub5 = false;
						}
						
						if(platform6.y == 91)
						{
							platformhub6 = true;
						}
						else if(platform6.y == 70)
						{
							platformhub6 = false;
						}
						
						if(platform7.y == 91)
						{
							platformhub7 = true;
						}
						else if(platform7.y == 70)
						{
							platformhub7 = false;
						}
						
						if(platform8.y == 91)
						{
							platformhub8 = true;
						}
						else if(platform8.y == 70)
						{
							platformhub8 = false;
						}
						
						if(platform9.y == 91)
						{
							platformhub9 = true;
						}
						else if(platform9.y == 70)
						{
							platformhub9 = false;
						}
						
						if(platform10.y == 91)
						{
							platformhub10 = true;
						}
						else if(platform10.y == 70)
						{
							platformhub10 = false;
						}
						
						if(platform11.y == 91)
						{
							platformhub11 = true;
						}
						else if(platform11.y == 70)
						{
							platformhub11 = false;
						}
						
						if(platform12.y == 91)
						{
							platformhub12 = true;
						}
						else if(platform12.y == 70)
						{
							platformhub12 = false;
						}
						
						if(platform13.y == 91)
						{
							platformhub13 = true;
						}
						else if(platform13.y == 70)
						{
							platformhub13 = false;
						}
						
						if(platform14.y == 91)
						{
							platformhub14 = true;
						}
						else if(platform14.y == 70)
						{
							platformhub14 = false;
						}
						
						if(platform15.y == 91)
						{
							platformhub15 = true;
						}
						else if(platform15.y == 70)
						{
							platformhub15 = false;
						}
						
						if(platform16.y == 91)
						{
							platformhub16 = true;
						}
						else if(platform16.y == 70)
						{
							platformhub16 = false;
						}
						
						//Section 2
						
						if(platformhub9 == true)
						{
							platform9.y-=3;
						}
						if(platformhub9 == false)
						{
							platform9.y+=3;
						}
						
						if(platformhub10 == true)
						{
							platform10.y-=3;
						}
						if(platformhub10 == false)
						{
							platform10.y+=3;
						}
						
						if(platformhub11 == true)
						{
							platform11.y-=3;
						}
						if(platformhub11 == false)
						{
							platform11.y+=3;
						}
						
						if(platformhub12 == true)
						{
							platform12.y-=3;
						}
						if(platformhub12 == false)
						{
							platform12.y+=3;
						}
						
						if(platformhub13 == true)
						{
							platform13.y-=3;
						}
						if(platformhub13 == false)
						{
							platform13.y+=3;
						}
						
						if(platformhub14 == true)
						{
							platform14.y-=3;
						}
						if(platformhub14 == false)
						{
							platform14.y+=3;
						}
						
						if(platformhub15 == true)
						{
							platform15.y-=3;
						}
						if(platformhub15 == false)
						{
							platform15.y+=3;
						}
						
						if(platformhub16 == true)
						{
							platform16.y-=3;
						}
						if(platformhub16 == false)
						{
							platform16.y+=3;
						}
						
						if(platformhub8 == true)
						{
							platform8.y-=3;
						}
						if(platformhub8 == false)
						{
							platform8.y+=3;
						}
						
						if(platformhub7 == true)
						{
							platform7.y-=3;
						}
						if(platformhub7 == false)
						{
							platform7.y+=3;
						}
						
						if(platformhub6 == true)
						{
							platform6.y-=3;
						}
						if(platformhub6 == false)
						{
							platform6.y+=3;
						}
						
						if(platformhub5 == true)
						{
							platform5.y-=3;
						}
						if(platformhub5 == false)
						{
							platform5.y+=3;
						}
						
						if(platformhub4 == true)
						{
							platform4.y-=3;
						}
						if(platformhub4 == false)
						{
							platform4.y+=3;
						}
						
						if(platformhub3 == true)
						{
							platform3.y-=3;
						}
						if(platformhub3 == false)
						{
							platform3.y+=3;
						}
						
						if(platformhub2 == true)
						{
							platform2.y-=3;
						}
						if(platformhub2 == false)
						{
							platform2.y+=3;
						}
						
						if(platformhub1 == true)
						{
							platform1.y-=3;
						}
						if(platformhub1 == false)
						{
							platform1.y+=3;
						}
					}
					audioTimer += 75;
				}
				if(System.currentTimeMillis() - animeTimer >= 250) {
					player.animation = 1;
					player2.animation = 0;
				}
				if(System.currentTimeMillis() - animeTimer >= 500) {
					player.animation = 0;
					player2.animation = 1;
					if(gamestarted)
					{
						if(fakerandomizer <= 500000)
						{
						fakerandom+=fakerandomizer;
						fakerandomizer+=3;
						}
						else if(fakerandomizer > 5000)
						{
							fakerandomizer=27;
							fakerandom=3;
							fakerandom+=fakerandomizer;
							fakerandomizer+=3;
						}
					}
					animeTimer += 500;
				}
				if(gamestarted)
				{
				if(System.currentTimeMillis() - audioTimer >= 2000) {
					new Music("/neut.wav");
					audioTimer += 2000;
				}
				if(System.currentTimeMillis() - platformTimer >= difficulty) {
					platformTimer+=difficulty;
					platform9.y++;
					platform10.y++;
					platform11.y++;
					platform12.y++;
					platform13.y++;
					platform14.y++;
					platform15.y++;
					platform16.y++;
					if(isTossing)
					{
						cannonball.x+=lrcannon * 3;
						cannonball.toss++;
					}
					if(isPowerup)
					{
						powerup.y+=2;
					}
					if(isShowering)
					{
						meteor.y+=2;
					}
				}
				if(System.currentTimeMillis() - lastTimer >= difficulty * 30) {
					lastTimer += difficulty * 30;

					if(countdown == 3)
					{
						countdown--;
					}
					else if(countdown == 2)
					{
						countdown--;
					}
					else if(countdown == 1)
					{
						isFire1 = false;
						isFire2 = false;
						scores++;
						if(scores >= highScore)
						{
							highScore = scores;
							if(gamemode == 1)
							{
								highgame1 = highScore;
							}
							if(gamemode == 2)
							{
								highgame2 = highScore;
							}
							if(gamemode == 3)
							{
								highgame3 = highScore;
							}
							if(gamemode == 4)
							{
								highgame4 = highScore;
							}
						}
						countdown = 3;
						if(countdownagain > 1)
						{
							countdownagain--;
						}
						else if(countdownagain == 1)
						{
							if(difficulty > 10)
							{
							difficulty--;
							}
							countdownagain = 2;
						}
						resetPlatforms();
						platformSwap(false);
						if(gamemode == 2 || gamemode == 4)
						{
							if(meteorshower(2, 1) == 1)
							{
								meteor.y = -10;
								meteor.x = (meteorshower(7, 1) * 8) + 16;
								isShowering = true;
							}
							else
							{
								powerup.y = -10;
								powerup.x = (meteorshower(7, 1) * 8) + 16;
								isPowerup = true;
							}
							if(meteorshower(2, 1) == 1)
							{
								cannonball.toss = 0;
								cannonball.y = 70;
								int random = meteorshower(2, 1);
								if(random == 1)
								{
									lrcannon = 1;
									cannonball.x = -8;
								}
								else if(random == 2)
								{
									lrcannon = -1;
									cannonball.x = 128;
								}
								isTossing = true;
							}
							if(meteorshower(2, 1) == 1)
							{
								isFire1 = true;
							}
							if(meteorshower(2, 1) == 1)
							{
								isFire2 = true;
							}
						}
					}
					frames = 0;
					ticks = 0;
				}
			}
			else if(!gamestarted && !isSelecting1 && !isSelecting2)
			{
				
				if(input.one.isPressed())
				{
					gamemode = 1;
					gamestarted = true;
					resetTimers();
					resetPlatforms();
					player2.y = 84;
					player.y = 84;
					platformSwap(true);
					highScore = highgame1;
					playerDeaths = deathgame1;
				}
				else if(input.two.isPressed())
				{
					gamemode = 2;
					gamestarted = true;
					resetTimers();
					resetPlatforms();
					player2.y = 84;
					player.y = 84;
					platformSwap(true);
					highScore = highgame2;
					playerDeaths = deathgame2;
				}
				else if(input.three.isPressed())
				{
					gamemode = 3;
					gamestarted = true;
					resetTimers();
					resetPlatforms();
					player2.y = 84;
					player.y = 84;
					platformSwap(true);
					player2.x = 64;
					player.x = 24;
					highScore = highgame3;
					playerDeaths = deathgame3;
				}
				else if(input.four.isPressed())
				{
					gamemode = 4;
					gamestarted = true;
					resetTimers();
					resetPlatforms();
					player2.y = 84;
					player.y = 84;
					platformSwap(true);
					player2.x = 64;
					player.x = 24;
					highScore = highgame4;
					playerDeaths = deathgame4;
				}
				else if(input.char1.isPressed())
				{
					isSelecting1 = true;
					player.x = 44;
					player.y = 44;
				}
				else if(input.char2.isPressed())
				{
					isSelecting2 = true;
					player2.x = 44;
					player2.y = 44;
				}
				
			}
		}
	}
	
	public void tick() {
		tickCount++;
		
		level.tick();
	}
	
	public static void resetPlatforms(){
		platform1.y = 100;
		platform2.y = 100;
		platform3.y = 100;
		platform4.y = 100;
		platform5.y = 100;
		platform6.y = 100;
		platform7.y = 100;
		platform8.y = 100;
		platform9.y = 10;
		platform10.y = 10;
		platform11.y = 10;
		platform12.y = 10;
		platform13.y = 10;
		platform14.y = 10;
		platform15.y = 10;
		platform16.y = 10;
		Game.Tplatform1 = true;
		Game.Tplatform2 = true;
		Game.Tplatform3 = true;
		Game.Tplatform4 = true;
		Game.Tplatform5 = true;
		Game.Tplatform6 = true;
		Game.Tplatform7 = true;
		Game.Tplatform8 = true;
	}
	
	public static void resetWorld()
	{
		Tplatform9 = false;
		Tplatform10 = false;
		Tplatform11 = false;
		Tplatform12 = false;
		Tplatform13 = false;
		Tplatform14 = false;
		Tplatform15 = false;
		Tplatform16 = false;
	}
	
	public static void resetHub(boolean doingAgain)
	{
		Game.Tplatform1 = true;
		Game.Tplatform2 = true;
		Game.Tplatform3 = true;
		Game.Tplatform4 = true;
		Game.Tplatform5 = true;
		Game.Tplatform6 = true;
		Game.Tplatform7 = true;
		Game.Tplatform8 = true;
		Game.Tplatform9 = true;
		Game.Tplatform10 = true;
		Game.Tplatform11 = true;
		Game.Tplatform12 = true;
		Game.Tplatform13 = true;
		Game.Tplatform14 = true;
		Game.Tplatform15 = true;
		Game.Tplatform16 = true;
		if(!doingAgain)
		{
			platform1.y = 70;
			platform2.y = 73;
			platform3.y = 76;
			platform4.y = 79;
			platform5.y = 82;
			platform6.y = 85;
			platform7.y = 88;
			platform8.y = 91;
			platform16.y = 70;
			platform15.y = 73;
			platform14.y = 76;
			platform13.y = 79;
			platform12.y = 82;
			platform11.y = 85;
			platform10.y = 88;
			platform9.y = 91;
		}
		platformhub1 = false;
		platformhub2 = false;
		platformhub3 = false;
		platformhub4 = false;
		platformhub5 = false;
		platformhub6 = false;
		platformhub7 = false;
		platformhub8 = false;
		platformhub9 = true;
		platformhub10 = true;
		platformhub11 = true;
		platformhub12 = true;
		platformhub13 = true;
		platformhub14 = true;
		platformhub15 = true;
		platformhub16 = true;
	}
	
	public void resetTimers(){
		
		lastTimer += System.currentTimeMillis() - lastTimer;
		animeTimer += System.currentTimeMillis() - animeTimer;
		platformTimer += System.currentTimeMillis() - platformTimer;
		firstTimer += System.currentTimeMillis() - firstTimer;
		switchTimer += System.currentTimeMillis() - switchTimer;
		audioTimer += System.currentTimeMillis() - audioTimer;
		hubTimer += System.currentTimeMillis() - hubTimer;
		
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		
		int xOffset = player.x - (screen.width / 2);
		int yOffset = player.y - (screen.height / 2);
		
		level.renderTiles(screen, xOffset, yOffset);
		
		level.renderEntities(screen);
		
		if(gamestarted){
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("S-" + scores, screen, 0, 1, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("D-" + playerDeaths, screen, 0, 9, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("H-" + highScore, screen, 0, 17, colour);
			}
		}
		else if(!gamestarted && !isSelecting1 && !isSelecting2)
		{
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("1-EASY SINGLE", screen, 5, 2, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("2-HARD SINGLE", screen, 5, 13, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("3-EASY DOUBLE", screen, 5, 24, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render("4-HARD DOUBLE", screen, 5, 35, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 1);
				Font.render("BOWNJUMP V7", screen, 14, 95, colour); //72
			}
		}
		else if(isSelecting1)
		{
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render(name[player1model], screen, 48 - (name[player1model].length() * 3) - 1, 15, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 1000);
				Font.render("PLAYER 1 SELECT", screen, 48 - ("PLAYER 1 SELECT".length() * 3) - 1, 2, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 1);
				Font.render("BOWNJUMP V7", screen, 14, 95, colour); //72
			}
		}
		else if(isSelecting2)
		{
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 50);
				Font.render(name[player2model], screen, 48 - (name[player2model].length() * 3) - 1, 15, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 1000);
				Font.render("PLAYER 2 SELECT", screen, 48 - ("PLAYER 2 SELECT".length() * 3) - 1, 2, colour);
			}
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 1);
				Font.render("BOWNJUMP V7", screen, 14, 95, colour); //72
			}
		}
		else
		{
			for(int x = 0; x < level.width; x++){
				int colour = Colours.get(-1, -1, -1, 1);
				Font.render("BOWNJUMP V7", screen, 14, 95, colour); //72
			}
		}

		for(int y = 0; y < screen.height; y++){
			for(int x = 0; x < screen.width; x++){
				int colourCode = screen.pixels[x + y * screen.width];
				if(colourCode < 255) pixels[x + y * WIDTH] = colours[colourCode];
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();
	}
	
	public static int meteorshower(int max, int min)
	{
		int random = (int) (Math.random() * ((max + 1) - min) + min);
		return random;
		
	}
	
	public static void platformSwap(boolean swappingAgain)
	{
		if(!swappingAgain)
		{
			Tplatform1 = Tplatform9;
			Tplatform2 = Tplatform10;
			Tplatform3 = Tplatform11;
			Tplatform4 = Tplatform12;
			Tplatform5 = Tplatform13;
			Tplatform6 = Tplatform14;
			Tplatform7 = Tplatform15;
			Tplatform8 = Tplatform16;
		}
		
		int random1 = (int) (Math.random() * ((1 + 7) - 0) + 0);
		int random2 = 8;
		if(gamemode == 3 || gamemode == 4)
		{
			random2 = (int) (Math.random() * ((1 + 7) - 0) + 0);
		}
		
		Tplatform9 = false;
		Tplatform10 = false;
		Tplatform11 = false;
		Tplatform12 = false;
		Tplatform13 = false;
		Tplatform14 = false;
		Tplatform15 = false;
		Tplatform16 = false;
		
		if(random1 == 0 || random2 == 0)
		{
			Tplatform9 = true;
		}
		if(random1 == 1 || random2 == 1)
		{
			Tplatform10 = true;
		}
		if(random1 == 2 || random2 == 2)
		{
			Tplatform11 = true;
		}
		if(random1 == 3 || random2 == 3)
		{
			Tplatform12 = true;
		}
		if(random1 == 4 || random2 == 4)
		{
			Tplatform13 = true;
		}
		if(random1 == 5 || random2 == 5)
		{
			Tplatform14 = true;
		}
		if(random1 == 6 || random2 == 6)
		{
			Tplatform15 = true;
		}
		if(random1 == 7 || random2 == 7)
		{
			Tplatform16 = true;
		}
		
		if(random1 == random2)
		{
			platformSwap(true);
		}
	}
	
	public void debug(DebugLevel level, String msg){
		switch(level){
		
		case INFO:
		if(debug){
			System.out.println("[" + NAME + "] " + msg);
		}
		break;
		case WARNING:
			System.out.println("[" + NAME + "] [WARNING] " + msg);
			break;
		case SEVERE:
			System.out.println("[" + NAME + "] [SEVERE] " + msg);
			break;
		}
	}
	
	public static enum DebugLevel{
		INFO, WARNING, SEVERE;
	}
	
	public static int randomTempo(){
		int speed = (int) (Math.random() * ((1 + 1) - 0) + 0);
		return speed;
	}
}
