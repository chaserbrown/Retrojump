package net.jumboturtle.game.entities;

import net.jumboturtle.game.Game;
import net.jumboturtle.game.InputHandler;
import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.level.Level;

public class Platform15 extends Mob{

	public static int l1platexist1, l1platexist2, l1platexist3, l1platexist4 = 0;
	public static int l2platexist1, l2platexist2, l2platexist3, l2platexist4 = 0;
	
	private int colour = Colours.get(-1, 111, 190, 400);
	
	public Platform15(Level level, int x, int y, InputHandler input) {
		super(level, "Platform", x, y, 1);
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
		if(Game.Tplatform15 == true)
		{
			int xTile = 0;
			int yTile = 26;
			int modifier = 8 * scale;
			int xOffset = x - modifier/2;
			int yOffset = y - modifier/2-4;
			
			screen.render(xOffset, yOffset, xTile + yTile * 32, colour);
			screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * 32, colour);
		}
	}
	
	public static int randomizer(int min, int max)
	{
		int random = (int) (Math.random() * ((max + 1) - min) + min);
		return random;
	}
	
}