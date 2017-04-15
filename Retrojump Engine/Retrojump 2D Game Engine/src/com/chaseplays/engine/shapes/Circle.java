package com.chaseplays.engine.shapes;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.screen.Sprite;

public class Circle {
	
	public int x, y;
	
	public int diameter;
	
	public Sprite sprite;
	
	public Circle (int x, int y, int diameter) {
		
		this.x = x;
		this.y = y;
		
		this.diameter = diameter;
		
		generateSprite();
		
	}
	
	public void generateSprite() {
		
		this.sprite = new Sprite(diameter, diameter);
		
		if (diameter % 2 == 0) {

			for (int x = 0; x < diameter; x++) {

				int height = (int) (Math.sqrt(Math.pow(diameter / 2, 2) - Math.pow(x - (diameter / 2), 2)) + 1);

				for (int y = 0; y < diameter; y++) {

					if ((y - (diameter / 2)) == 0 || (x - (diameter / 2)) == 0)
						this.sprite.pixels[(y * diameter) + x] = 0xFFFF0000;
					else if (((int) Math.abs(y - (diameter / 2)) < (height)))
						this.sprite.pixels[(y * diameter) + x] = 0xFF555555;
					else
						this.sprite.pixels[(y * diameter) + x] = 0xFF333333;

				}

			}
		
		} else {
			
			for (int x = 0; x < diameter; x++) {
				
				int height;
				
				if (x < (diameter / 2)) height = (int) (Math.sqrt(Math.pow(diameter / 2, 2) - Math.pow(x - (diameter / 2), 2)) + 1);
				
				else height = (int) (Math.sqrt(Math.pow(diameter / 2, 2) - Math.pow((x+1) - (diameter / 2), 2)) + 1);
				
				for (int y = 0; y < diameter; y++) {
					
					if (y < 0) {
						
						//if ((y - (diameter / 2)) == 0 || (x - (diameter / 2)) == 0) this.sprite.pixels[(y * diameter) + x] = 0xFFFF0000;
						if (((int) Math.abs(y - (diameter / 2)) < (height))) this.sprite.pixels[(y * diameter) + x] = 0xFF555555;
						else this.sprite.pixels[(y * diameter) + x] = 0xFF333333;
						
					} else {

						//if ((y - (diameter / 2)) == 0 || (y - (diameter / 2)) == -1 || (x - (diameter / 2)) == 0 || (x - (diameter / 2)) == -1) this.sprite.pixels[(y * diameter) + x] = 0xFFFF0000;
						if (((int) Math.abs((y) - (diameter / 2)) < (height))) this.sprite.pixels[(y * diameter) + x] = 0xFF555555;
						else this.sprite.pixels[(y * diameter) + x] = 0xFF333333;
						
					}
					
				}

			}
			
			for (int x = -(diameter / 2); x < (diameter / 2); x++) {
				
				if (x == 0) x++;
				
				int height;
				
				height = (int) (Math.sqrt(Math.pow((diameter * 1.1) / 2, 2) - Math.pow(x, 2)) + 1);
				
				for (int y = -(diameter / 2); y < (diameter / 2); y++) {
					
					if (y == 0) y++;
					
					if (y < 0 && x < 0) {
						
						if (((int) Math.abs(y) < height)) this.sprite.pixels[((y + (diameter / 2)) * diameter) + (x + (diameter / 2))] = 0xFF555555;
						else this.sprite.pixels[(((y + (diameter / 2))) * diameter) + (x + (diameter / 2))] = 0xFF333333;
						
					}
					
					else if (y > 0 && x < 0) {
						
						if (((int) Math.abs(y) < height)) this.sprite.pixels[((y + (diameter / 2) - 1) * diameter) + (x + (diameter / 2))] = 0xFF555555;
						else this.sprite.pixels[(((y + (diameter / 2) - 1)) * diameter) + (x + (diameter / 2))] = 0xFF333333;
						
					}
					
					else if (y < 0 && x > 0) {
						
						if (((int) Math.abs(y) < height)) this.sprite.pixels[((y + (diameter / 2)) * diameter) + (x + (diameter / 2) - 1)] = 0xFF555555;
						else this.sprite.pixels[(((y + (diameter / 2))) * diameter) + (x + (diameter / 2) - 1)] = 0xFF333333;
						
					}
					
					else if (y > 0 && x > 0) {
						
						if (((int) Math.abs(y) < height)) this.sprite.pixels[((y + (diameter / 2) - 1) * diameter) + (x + (diameter / 2) - 1)] = 0xFF555555;
						else this.sprite.pixels[(((y + (diameter / 2) - 1)) * diameter) + (x + (diameter / 2) - 1)] = 0xFF333333;
						
					}
					
				}

			}

		}
		
		for (int x = 0; x < diameter; x++) {
			
			for (int y = 0; y < diameter; y++) {
				
				this.sprite.alphas[(y * diameter) + x] = 255;
				
			}
			
		}
		
	}
	
	public void render(Engine e) {
		
		e.screen.in.render(x, y, sprite);
		
	}
	
}
