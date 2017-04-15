package com.chaseplays.engine.text;

import com.chaseplays.engine.screen.Sprite;

public class Font {
	
	public static final Font SteelPiano = new SteelPiano("/images/fonts/steelpiano.png", 3, 1, 9);
	public static final Font ComicForte = new ComicForte("/images/fonts/comicforte.png", 8, 3, 26);
	public static final Font Gamezzo = new Gamezzo("/images/fonts/gamezzo.png", 8, 3, 26);
	public static final Font Underblocks = new Underblocks("/images/fonts/underblocks.png", 8, 2, 15);
	
	public Figure[] figures = new Figure[94];
	
	public int space, gap, size;
	
	public Font (String path, int space, int gap, int size) {
		
		this.space = space;
		this.gap = gap;
		this.size = size;
		
	}
	
	public Figure getFigure (char c) {
		
		if (c == ' ') return new Figure(new Sprite(0xFFFF00FF, space, 1), ' ');
		if (c == '\n') return new Figure(new Sprite(0xFFFF00FF, space, 1), '\n');
		
		for (int f = 0; f < figures.length; f++) {
			
			if (figures[f].c == c) return new Figure(figures[f]);
			
		}
		
		return null;
		
	}
	
}
