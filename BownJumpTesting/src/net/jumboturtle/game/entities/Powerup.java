package net.jumboturtle.game.entities;

import net.jumboturtle.game.Config;
import net.jumboturtle.game.Game;
import net.jumboturtle.game.InputHandler;
import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.level.Level;

public class Powerup extends Mob{
	
	private int colour = Colours.get(-1, 41, 66, 4);
	
	public Powerup(Level level, int x, int y, InputHandler input) {
		super(level, "Powerup", x, y, 1);
	}

	public boolean hasCollided(int xa, int ya) {
		
		return isMoving;
		
	}

	public void tick() {
		if(y >= 150)
		{
			Game.isPowerup = false;
			y = -20;
		}
	}
	
	public void tileExist()
	{
		randomizer(1, 6);
	}

	public void render(Screen screen) {
		if(Config.doPowerup)
		{
			int xTile = 0;
			int yTile = 28;
			int modifier = 8 * scale;
			int xOffset = x - modifier/2;
			int yOffset = y - modifier/2-4;
			
			screen.render(xOffset, yOffset, xTile + yTile * 32, colour);
			screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * 32, colour);
			screen.render(xOffset, yOffset + modifier, xTile + (yTile + 1) * 32, colour);
			screen.render(xOffset + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour);
		}

	}
	
	public static int randomizer(int min, int max)
	{
		int random = (int) (Math.random() * ((max + 1) - min) + min);
		return random;
	}
	
}