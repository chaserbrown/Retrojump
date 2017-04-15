package net.jumboturtle.game.entities;

import net.jumboturtle.game.Config;
import net.jumboturtle.game.Game;
import net.jumboturtle.game.InputHandler;
import net.jumboturtle.game.Music;
import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.level.Level;

public class Player2 extends Mob{

	public static boolean isJumping;
	public static boolean isFalling;
	public static boolean isFallZone;
	public static boolean justJumped;
	public static int jumping;
	public static int lastJumped;
	public static boolean isMovable = false;
	public InputHandler input;
	public static int shield = 0;
	public static int animation = 1;
	private static int colour = Colours.get(-1, 1000, 161, 1000);
	
	public Player2(Level level, int x, int y, InputHandler input) {
		super(level, "Player2", x, y, 1);
		this.input = input;
	}

	public boolean hasCollided(int xa, int ya) {

		return false;
		
	}

	public void tick() {
		
		if(input.down2.isPressed())
		{
			shield = 4;
		}
		else if(input.shield.isPressed())
		{
			shield = 4;
		}
		else
		{
			shield = 0;
		}
		
		if(isJumping){
			if(lastJumped == 2)
			{
				if(jumping < 6)
				{
					jumping++;
					y-=2;
				}
				else if(jumping >= 6 && jumping < 10)
				{
					y-=1;
					jumping++;
				}
				else if(jumping >= 10 && jumping < 12)
				{
					jumping++;
				}
				else if(jumping >= 12 && jumping < 14)
				{
					jumping++;
				}
				else if(jumping >= 14 && jumping < 18)
				{
					y++;
					jumping++;
				}
				else if(jumping >= 18 && jumping < 24)
				{
					y+=2;
					jumping++;
				}
				else if(jumping >= 24)
				{
					y++;
					isJumping = false;
					jumping = 0;
					justJumped = true;
				}
				lastJumped = 1;
			}
			else
			{
				lastJumped++;
			}
			
		}
		else
		{
			jumping = 0;
			isJumping = false;
			justJumped = false;
		}
		
		if(Game.isSelecting2 && shield == 0)
		{
			if(input.up2.isPressed() && !isJumping && !isFalling && !isFallZone && !justJumped){
				y--;
				isJumping = true;
				isMovable = true;
				new Music("/jump.wav");
			}
		}
		
		if(Game.gamemode == 3 || Game.gamemode == 4)
		{
			int xa = 0;
			int ya = 0;
			
			if((input.up.isPressed() || input.left.isPressed() || input.right.isPressed()))
			{
				isMovable = true;
			}
			if(!input.down2.isPressed() && !input.shield.isPressed())
			{
				if(input.up2.isPressed() && !isJumping && !isFalling && !isFallZone && !justJumped){
					y--;
					isJumping = true;
					isMovable = true;
					new Music("/jump.wav");
				}
				if(input.right2.isPressed()){
					if(x == 96)
					{
						x = -8;
					}
					else
					{
						x++;
					}
					isMovable = true;
				}
				if(input.left2.isPressed()){
					if(x == -8)
					{
						x = 96;
					}
					else
					{
						x--;
					}
					isMovable = true;
				}
			}
			if(x <= 16 && Game.Tplatform1 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 24 && x > 16 && Game.Tplatform1 == false && Game.Tplatform2 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x == 24 && Game.Tplatform2 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 32 && x > 24 && Game.Tplatform2 == false && Game.Tplatform3 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x == 32 && Game.Tplatform3 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 40 && x > 32 && Game.Tplatform3 == false && Game.Tplatform4 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x == 40 && Game.Tplatform4 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 48 && x > 40 && Game.Tplatform4 == false && Game.Tplatform5 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x == 48 && Game.Tplatform5 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 56 && x > 48 && Game.Tplatform5 == false && Game.Tplatform6 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x == 56 && Game.Tplatform6 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 64 && x > 56 && Game.Tplatform6 == false && Game.Tplatform7 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x == 64 && Game.Tplatform7 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x < 72 && x > 64 && Game.Tplatform7 == false && Game.Tplatform8 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x >= 72 && Game.Tplatform8 == false && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x <= 8 && !isJumping && !isFalling)
			{
				y++;
				isFalling = true;
			}
			if(x >= 80 && !isJumping && !isFalling)
			{
				isFallZone = true;
				if(!isJumping)
				{
					y++;
					isFalling = true;
				}
			}
			if(x < Game.fire1.x + 7 && x > Game.fire1.x - 7 && Game.isFire1 && Config.doFire1)
			{
				isFalling = false;
				isFallZone = false;
				Game.player.isFalling = false;
				Game.player.isFallZone = false;
				Game.Tplatform1 = true;
				Game.Tplatform2 = true;
				Game.Tplatform3 = true;
				Game.Tplatform4 = true;
				Game.Tplatform5 = true;
				Game.Tplatform6 = true;
				Game.Tplatform7 = true;
				Game.Tplatform8 = true;
				x = 64;
				y = 84;
				Game.player.x = 24;
				Game.player.y = 84;
				jumping = 0;
				lastJumped = 0;
				isJumping = false;
				Game.player.jumping = 0;
				Game.player.lastJumped = 0;
				Game.player.isJumping = false;
				Game.scores = 0;
				Game.playerDeaths++;
				Game.difficulty = 25;
				Game.countdown = 3;
				Game.resetPlatforms();
				Game.isShowering = false;
				Game.meteor.y = -10;
				Game.isPowerup = false;
				Game.powerup.y = -10;
				Game.isTossing = false;
				Game.cannonball.x = -50;
				Game.isFire1 = false;
				Game.isFire2 = false;
				Game.lastTimer += System.currentTimeMillis() - Game.lastTimer;
			}
			if(x < Game.fire2.x + 7 && x > Game.fire2.x - 7 && Game.isFire2 && Config.doFire2)
			{
				isFalling = false;
				isFallZone = false;
				Game.player.isFalling = false;
				Game.player.isFallZone = false;
				Game.Tplatform1 = true;
				Game.Tplatform2 = true;
				Game.Tplatform3 = true;
				Game.Tplatform4 = true;
				Game.Tplatform5 = true;
				Game.Tplatform6 = true;
				Game.Tplatform7 = true;
				Game.Tplatform8 = true;
				x = 64;
				y = 84;
				Game.player.x = 24;
				Game.player.y = 84;
				jumping = 0;
				lastJumped = 0;
				isJumping = false;
				Game.player.jumping = 0;
				Game.player.lastJumped = 0;
				Game.player.isJumping = false;
				Game.scores = 0;
				Game.playerDeaths++;
				Game.difficulty = 25;
				Game.countdown = 3;
				Game.resetPlatforms();
				Game.isShowering = false;
				Game.meteor.y = -10;
				Game.isPowerup = false;
				Game.powerup.y = -10;
				Game.isTossing = false;
				Game.cannonball.x = -50;
				Game.isFire1 = false;
				Game.isFire2 = false;
				Game.lastTimer += System.currentTimeMillis() - Game.lastTimer;
			}
			if(x < Game.meteor.x + 8 && x > Game.meteor.x - 8 && y <= Game.meteor.y + 12 && y >= Game.meteor.y - 8 && Config.doMeteor)
			{
				isFalling = false;
				isFallZone = false;
				Game.player.isFalling = false;
				Game.player.isFallZone = false;
				Game.Tplatform1 = true;
				Game.Tplatform2 = true;
				Game.Tplatform3 = true;
				Game.Tplatform4 = true;
				Game.Tplatform5 = true;
				Game.Tplatform6 = true;
				Game.Tplatform7 = true;
				Game.Tplatform8 = true;
				x = 64;
				y = 84;
				Game.player.x = 24;
				Game.player.y = 84;
				jumping = 0;
				lastJumped = 0;
				isJumping = false;
				Game.player.jumping = 0;
				Game.player.lastJumped = 0;
				Game.player.isJumping = false;
				Game.scores = 0;
				Game.playerDeaths++;
				Game.difficulty = 25;
				Game.countdown = 3;
				Game.resetPlatforms();
				Game.isShowering = false;
				Game.meteor.y = -10;
				Game.isPowerup = false;
				Game.powerup.y = -10;
				Game.isTossing = false;
				Game.cannonball.x = -50;
				Game.isFire1 = false;
				Game.isFire2 = false;
				Game.lastTimer += System.currentTimeMillis() - Game.lastTimer;
			}
			if(x < Game.cannonball.x + 8 && x > Game.cannonball.x - 8 && y <= Game.cannonball.y + 12 && y >= Game.cannonball.y - 8 && shield == 4 && Config.doCannonball)
			{
				Game.isTossing = false;
				Game.cannonball.x = -50;
			}
			else if(x < Game.cannonball.x + 8 && x > Game.cannonball.x - 8 && y <= Game.cannonball.y + 12 && y >= Game.cannonball.y - 8 && !(shield == 4) && Config.doCannonball)
			{
				isFalling = false;
				isFallZone = false;
				Game.player.isFalling = false;
				Game.player.isFallZone = false;
				Game.Tplatform1 = true;
				Game.Tplatform2 = true;
				Game.Tplatform3 = true;
				Game.Tplatform4 = true;
				Game.Tplatform5 = true;
				Game.Tplatform6 = true;
				Game.Tplatform7 = true;
				Game.Tplatform8 = true;
				x = 64;
				y = 84;
				Game.player.x = 24;
				Game.player.y = 84;
				jumping = 0;
				lastJumped = 0;
				isJumping = false;
				Game.player.jumping = 0;
				Game.player.lastJumped = 0;
				Game.player.isJumping = false;
				Game.scores = 0;
				Game.playerDeaths++;
				Game.difficulty = 25;
				Game.countdown = 3;
				Game.resetPlatforms();
				Game.isShowering = false;
				Game.meteor.y = -10;
				Game.isPowerup = false;
				Game.powerup.y = -10;
				Game.isTossing = false;
				Game.cannonball.x = -50;
				Game.isFire1 = false;
				Game.isFire2 = false;
				Game.lastTimer += System.currentTimeMillis() - Game.lastTimer;
			}
			if(x < Game.powerup.x + 8 && x > Game.powerup.x - 8 && y <= Game.powerup.y + 12 && y >= Game.powerup.y - 8 && Config.doPowerup)
			{
				Game.Tplatform1 = true;
				Game.Tplatform2 = true;
				Game.Tplatform3 = true;
				Game.Tplatform4 = true;
				Game.Tplatform5 = true;
				Game.Tplatform6 = true;
				Game.Tplatform7 = true;
				Game.Tplatform8 = true;
				Game.isPowerup = false;
				Game.powerup.y = -10;
			}
			if(isFalling)
			{
				y+=2;
				isFalling = true;
			}
			if(y >= 150){
				isFalling = false;
				isFallZone = false;
				Game.player.isFalling = false;
				Game.player.isFallZone = false;
				Game.Tplatform1 = true;
				Game.Tplatform2 = true;
				Game.Tplatform3 = true;
				Game.Tplatform4 = true;
				Game.Tplatform5 = true;
				Game.Tplatform6 = true;
				Game.Tplatform7 = true;
				Game.Tplatform8 = true;
				x = 64;
				y = 84;
				Game.player.x = 24;
				Game.player.y = 84;
				jumping = 0;
				lastJumped = 0;
				isJumping = false;
				Game.player.jumping = 0;
				Game.player.lastJumped = 0;
				Game.player.isJumping = false;
				Game.scores = 0;
				Game.playerDeaths++;
				Game.difficulty = 25;
				Game.countdown = 3;
				Game.resetPlatforms();
				Game.isShowering = false;
				Game.meteor.y = -10;
				Game.isPowerup = false;
				Game.powerup.y = -10;
				Game.isTossing = false;
				Game.cannonball.x = -50;
				Game.isFire1 = false;
				Game.isFire2 = false;
				Game.lastTimer += System.currentTimeMillis() - Game.lastTimer;
			}
			if(!isFalling && justJumped)
			{
				new Music("/land.wav");
			}
			justJumped = false;
		}
	}

	public void render(Screen screen) {

		if((Game.gamestarted && (Game.gamemode == 3 || Game.gamemode == 4)) || Game.isSelecting2)
		{
			int xTile = 30 - (animation * 2) - shield;
			int yTile = (Game.player2model * 2);
			int modifier = 8 * scale;
			int xOffset = x - modifier/2;
			int yOffset = y - modifier/2-4;
			
			screen.render(xOffset, yOffset, xTile + yTile * 32, colour);
			screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * 32, colour);
			screen.render(xOffset, yOffset + modifier, xTile + (yTile + 1) * 32, colour);
			screen.render(xOffset + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour);
		}
		
	}
	
}
