package com.chaseplays.engine.shapes;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.engine.single.rEntity;

/**
 * 
 * The class responsible for drawing a line between two places
 * 
 * @author Chase "Bown" Peterson
 *
 */

public class Line {
	
	public double degrees;
	
	public double size, length, height;
	
	public int ox, oy;
	
	public int color = 0xFFFFFFFF;
	
	public int thickness = 2;
	
	public Line (rEntity e1, rEntity e2) {
		
	}
	
	public Line (int x1, int y1, int x2, int y2) {
		
		int a = 0, b = 0;
		
		if (x1 > x2) {
			
			a = x1 - x2;
			b = y2 - y1;
			
			ox = x2;
			oy = y2;
			
		}
		
		if (x1 <= x2) {

			a = x2 - x1;
			b = y1 - y2;
			
			ox = x1;
			oy = y1;
			
		}
		
		if (x1 == x2) {
			
			ox = x1;
			oy = Math.max(y1, y2);
			
		}
		
		double c = Math.sqrt((a * a) + (b * b));
		
		size = c;
		
		length = a;
		height = b;
		
		if(a != 0) degrees = Math.toDegrees(Math.atan((double) b / (double) a));
		else degrees = 90;
	}
	
	public void draw (Engine e) {
		
		Sprite spot = new Sprite(color, thickness, thickness);
		
		double radians = Math.toRadians(degrees);
		
		double slope = (1 / (1 / Math.tan(radians))) * -1;
		
		degrees %= 360;
		
		while (degrees < 0) degrees += 360;
		
		height = size * Math.sin(radians);
		length = size * Math.cos(radians);
		
		for (int x = 0; x < Math.max(Math.abs(length), Math.abs(height)); x++) {
			
			if ((degrees < 44.5) || (degrees > 315.5)) e.screen.in.render(x + ox - (thickness / 2), (int) (slope * x) + oy - (thickness / 2), spot);
			else if ((degrees >= 44.5) && (degrees <= 45.5)) e.screen.in.render(x + ox - (thickness / 2), -x + oy - (thickness / 2), spot);
			else if ((degrees > 45.5) && (degrees <= 90)) e.screen.in.render(ox - (int) ((1 / slope) * x) - (thickness / 2), -x + oy - (thickness / 2), spot);
			else if ((degrees > 90) && (degrees <= 134.5)) e.screen.in.render(ox - (int) ((1 / slope) * x) - (thickness / 2), -x + oy - (thickness / 2), spot);
			else if ((degrees >= 134.5) && (degrees <= 135.5)) e.screen.in.render(-x + ox - (thickness / 2), -x + oy - (thickness / 2), spot);
			else if ((degrees > 135.5) && (degrees <= 224.5)) e.screen.in.render(ox - x - (thickness / 2), oy - (int) (slope * x) - (thickness / 2), spot);
			else if ((degrees >= 224.5) && (degrees <= 225.5)) e.screen.in.render(-x + ox - (thickness / 2), x + oy - (thickness / 2), spot);
			else if ((degrees > 225.5) && (degrees <= 314.5)) e.screen.in.render(ox + (int) ((1 / slope) * x) - (thickness / 2), oy + x - (thickness / 2), spot);
			else if ((degrees >= 314.5) && (degrees <= 315.5)) e.screen.in.render(x + ox - (thickness / 2), x + oy - (thickness / 2), spot);
			
		}
		
	}
	
	public void setColor (int color) {
		
		this.color = color;
		
	}
	
	public void setThickness (int thickness) {
		
		this.thickness = thickness;
		
	}
	
}
