package net.jumboturtle.game.entities;

import net.jumboturtle.game.Config;
import net.jumboturtle.game.Game;
import net.jumboturtle.game.InputHandler;
import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.level.Level;

public class Fire2 extends Mob{

	public static int l1platexist1, l1platexist2, l1platexist3, l1platexist4 = 0;
	public static int l2platexist1, l2platexist2, l2platexist3, l2platexist4 = 0;
	
	private int colour = Colours.get(-1, 11, 1000, 79);
	
	public Fire2(Level level, int x, int y, InputHandler input) {
		super(level, "Fire1", x, y, 1);
	}

	public boolean hasCollided(int xa, int ya) {
		
		return isMoving;
		
	}

	public void tick() {
		
	}
	
	public void tileExist()
	{
		randomizer(1, 6);
	}

	public void render(Screen screen) {
		
		if(Game.isFire2 && Config.doFire2)	
		{
			int xTile = 10;
			int yTile = 22;
			int modifier = 8 * scale;
			int xOffset = x - modifier/2;
			int yOffset = y - modifier/2-4;
			
			colour = Colours.get(-1, 11, 1000, 161);
			
			screen.render(xOffset, yOffset, xTile + yTile * 32, colour);
			screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * 32, colour);
			screen.render(xOffset, yOffset + modifier, xTile + (yTile + 1) * 32, colour);
			screen.render(xOffset + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour);
			screen.render(xOffset, yOffset + (modifier * 2), xTile + (yTile + 2) * 32, colour);
			screen.render(xOffset + modifier, yOffset + (modifier * 2), (xTile + 1) + (yTile + 2) * 32, colour);
			screen.render(xOffset, yOffset + (modifier * 3), xTile + (yTile + 3) * 32, colour);
			screen.render(xOffset + modifier, yOffset + (modifier * 3), (xTile + 1) + (yTile + 3) * 32, colour);
			screen.render(xOffset, yOffset + (modifier * 4), xTile + (yTile + 4) * 32, colour);
			screen.render(xOffset + modifier, yOffset + (modifier * 4), (xTile + 1) + (yTile + 4) * 32, colour);
			screen.render(xOffset, yOffset + (modifier * 5), xTile + (yTile + 5) * 32, colour);
			screen.render(xOffset + modifier, yOffset + (modifier * 5), (xTile + 1) + (yTile + 5) * 32, colour);
			screen.render(xOffset, yOffset + (modifier * 6), xTile + (yTile + 6) * 32, colour);
			screen.render(xOffset + modifier, yOffset + (modifier * 6), (xTile + 1) + (yTile + 6) * 32, colour);
			screen.render(xOffset, yOffset + (modifier * 7), xTile + (yTile + 7) * 32, colour);
			screen.render(xOffset + modifier, yOffset + (modifier * 7), (xTile + 1) + (yTile + 7) * 32, colour);
		}

	}
	
	public static int randomizer(int min, int max)
	{
		int random = (int) (Math.random() * ((max + 1) - min) + min);
		return random;
	}
	
}