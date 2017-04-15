package com.chaseplays.engine.screen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	
	public int width, height;
	
	public int[] pixels;
	
	public int[] alphas;
	
	/*
	
	public static final Sprite playsheet = new Sprite("/images/buttons/play.png");
	public static final Sprite pausesheet = new Sprite("/images/buttons/pause.png");
	public static final Sprite homesheet = new Sprite("/images/buttons/home.png");
	public static final Sprite settingsheet = new Sprite("/images/buttons/settings.png");
	
	public static final Sprite play1 = new Sprite(playsheet, 0, 0, 64, 64);
	public static final Sprite play2 = new Sprite(playsheet, 64, 0, 32, 32);
	public static final Sprite play3 = new Sprite(playsheet, 64, 32, 16, 16);
	public static final Sprite play4 = new Sprite(playsheet, 64, 48, 8, 8);
	
	public static final Sprite pause1 = new Sprite(pausesheet, 0, 0, 64, 64);
	public static final Sprite pause2 = new Sprite(pausesheet, 64, 0, 32, 32);
	public static final Sprite pause3 = new Sprite(pausesheet, 64, 32, 16, 16);
	public static final Sprite pause4 = new Sprite(pausesheet, 64, 48, 8, 8);
	
	public static final Sprite home1 = new Sprite(homesheet, 0, 0, 64, 64);
	public static final Sprite home2 = new Sprite(homesheet, 64, 0, 32, 32);
	public static final Sprite home3 = new Sprite(homesheet, 64, 32, 16, 16);
	public static final Sprite home4 = new Sprite(homesheet, 64, 48, 8, 8);
	
	public static final Sprite setting1 = new Sprite(settingsheet, 0, 0, 64, 64);
	public static final Sprite setting2 = new Sprite(settingsheet, 64, 0, 32, 32);
	public static final Sprite setting3 = new Sprite(settingsheet, 64, 32, 16, 16);
	public static final Sprite setting4 = new Sprite(settingsheet, 64, 48, 8, 8);
	
	*/
	
	public Sprite (int width, int height) {
		
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		alphas = new int[width * height];		
		
	}
	
	public Sprite (int color, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		alphas = new int[width * height];
		
		for (int yp = 0; yp < height; yp++) {
			
			for (int xp = 0; xp < width; xp++) {
				
				this.pixels[xp + yp * width] = color;
				
				this.alphas[xp + yp * width] = 255;
				
			}
			
		}
		
	}
	
	public Sprite (String path) {
		
		try {
			
			BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream(path));
			
			width = image.getWidth();
			height = image.getHeight();
			
			pixels = new int[width * height];
			alphas = new int[width * height];
			
			image.getRGB(0, 0, width, height, pixels, 0, width);
			
			for (int pixel = 0; pixel < width * height; pixel++) {
				
				alphas[pixel] = (pixels[pixel] >> 24) & 0xFF;
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public Sprite (Sprite sheet, int x, int y, int width, int height) {
		
		this.width = width;
		this.height = height;
		
		this.pixels = new int[width * height];
		this.alphas = new int[width * height];
		
		for (int yp = 0; yp < height; yp++) {
			
			for (int xp = 0; xp < width; xp++) {
				
				this.pixels[xp + yp * width] = sheet.pixels[(x + xp) + (y + yp) * sheet.width];
				this.alphas[xp + yp * width] = (pixels[xp + yp * width] >> 24) & 0xFF;
				
			}
			
		}
		
	}
	
	public Sprite (String path, int x, int y, int width, int height) {
		
		Sprite sheet = new Sprite(path);
		
		this.width = width;
		this.height = height;
		
		this.pixels = new int[width * height];
		this.alphas = new int[width * height];
		
		for (int yp = 0; yp < height; yp++) {
			
			for (int xp = 0; xp < width; xp++) {
				
				this.pixels[xp + yp * width] = sheet.pixels[(x + xp) + (y + yp) * sheet.width];
				alphas[xp + yp * width] = (pixels[xp + yp * width] >> 24) & 0xFF;
				
			}
			
		}
		
	}
	
	public Sprite tinted (int color, double amount) {
		
		Sprite sprite = new Sprite(this, 0, 0, width, height);
		
		for (int y = 0; y < height; y++) {
			
			for (int x = 0; x < width; x++) {

				Color original = new Color(this.pixels[x + y * width]);
				Color tint = new Color(color);
				
				Color newcolor = new Color((int) Math.min(Math.max((original.getRed() * (1 - amount) + (tint.getRed() * amount)), 0), 255),
											(int) Math.min(Math.max((original.getGreen() * (1 - amount) + (tint.getGreen() * amount)), 0), 255),
											(int) Math.min(Math.max((original.getBlue() * (1 - amount) + (tint.getBlue() * amount)), 0), 255));
				
				sprite.pixels[x + y * width] = newcolor.getRGB();
				
				}
			
		}
		
		return sprite;
		
	}
	
	public Sprite opacity (double amount) {
		
		Sprite sprite = new Sprite(this, 0, 0, width, height);
		
		for (int y = 0; y < height; y++) {
			
			for (int x = 0; x < width; x++) {

				int opacity = (int) (this.alphas[x + y * width] * amount);
				
				sprite.alphas[x + y * width] = opacity;
				
			}
			
		}
		
		return sprite;
		
	}
	
	public Sprite darkened (double amount) {
		
		return this.tinted(0xFF000000, amount);
		
	}
	
	public Sprite lightened (double amount) {
		
		return this.tinted(0xFFFFFFFF, amount);
		
	}
	
	public Sprite rotated (boolean x, boolean y) {
		
		Sprite sprite = new Sprite(this, 0, 0, width, height);
		
		for (int yp = 0; yp < height; yp++) {
			
			for (int xp = 0; xp < width; xp++) {
				
				if (x && y) sprite.pixels[(width - xp - 1) + (height - yp - 1) * width] = this.pixels[xp + yp * width];
				
				else if (x) sprite.pixels[(width - xp - 1) + yp * width] = this.pixels[xp + yp * width];
					
				else if (y) sprite.pixels[xp + (height - yp - 1) * width] = this.pixels[xp + yp * width];
				
			}
			
		}
		
		return sprite;
		
	}
	
}
