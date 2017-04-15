package net.jumboturtle.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener{

	public InputHandler(Game game){
		game.addKeyListener(this);
	}
	
	public class Key{
		private int numTimesPressed = 0;
		private boolean pressed = false;
		
		public boolean isPressed;
		
		public int getNumadadTimesPrsesed(){
			return numTimesPressed;
		}
		
		public boolean isPressed(){
			return pressed;
		}
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if(isPressed) numTimesPressed++;
		}
	}
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	public Key up2 = new Key();
	public Key down2 = new Key();
	public Key left2 = new Key();
	public Key right2 = new Key();
	
	public Key shield = new Key();
	
	public Key one = new Key();
	public Key two = new Key();
	public Key three = new Key();
	public Key four = new Key();
	
	public Key restart = new Key();
	
	public Key char1 = new Key();
	public Key char2 = new Key();
	
	public Key charrestart = new Key();
	
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}
	
	public void keyTyped(KeyEvent e) {

	}
	
	public void toggleKey(int keyCode, boolean isPressed){
		if(keyCode == KeyEvent.VK_UP)
		{up2.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_LEFT)
		{left2.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_DOWN)
		{down2.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_RIGHT)
		{right2.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_W)
		{up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_A)
		{left.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S)
		{down.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D)
		{right.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_SHIFT)
		{shield.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_SPACE)
		{up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_1)
		{one.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_2)
		{two.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_3)
		{three.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_4)
		{four.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_ESCAPE)
		{restart.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_PAGE_UP)
		{char1.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_PAGE_DOWN)
		{char2.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_ENTER)
		{charrestart.toggle(isPressed);
		}
	}
}
