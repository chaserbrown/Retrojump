package net.jumboturtle.game.entities;

import net.jumboturtle.game.Config;
import net.jumboturtle.game.Game;
import net.jumboturtle.game.InputHandler;
import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.level.Level;

public class Cannonball extends Mob{
	
	private int colour = Colours.get(-1, 1000, 1000, 1000);
	
	public int toss = 0;
	
	public Cannonball(Level level, int x, int y, InputHandler input) {
		super(level, "Meteor", x, y, 1);
	}

	public boolean hasCollided(int xa, int ya) {
		
		return isMoving;
		
	}

	public void tick() {
		if(Game.isTossing)
		{
			if(toss == 4)
			{
				y--;
			}
			if(toss == 8)
			{
				y--;
			}
			if(toss == 12)
			{
				y--;
			}
			if(toss == 17)
			{
				y--;
			}
			if(toss == 18)
			{
				y--;
			}
			if(toss == 24)
			{
				y--;
			}
			if(toss == 36)
			{
				y++;
			}
			if(toss == 43)
			{
				y++;
			}
			if(toss == 43)
			{
				y++;
			}
			if(toss == 52)
			{
				y++;
			}
			if(toss == 57)
			{
				y++;
			}
			if(toss == 60)
			{
				y++;
			}
			if(toss == 78)
			{
				y++;
				Game.isTossing = false;
				toss = 0;
				Game.cannonball.x = -50;
			}
		}
		if(y >= 150)
		{
			Game.isShowering = false;
			y = -20;
		}
	}
	
	public void tileExist()
	{
		randomizer(1, 6);
	}

	public void render(Screen screen) {
		if(Config.doCannonball)
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