package com.chaseplays.test.test1;

import java.util.Random;

import com.chaseplays.engine.Engine;
import com.chaseplays.engine.Game;
import com.chaseplays.engine.action.Action;
import com.chaseplays.engine.graphics.Background;
import com.chaseplays.engine.screen.Sprite;
import com.chaseplays.engine.shapes.Circle;
import com.chaseplays.engine.shapes.Line;
import com.chaseplays.engine.sound.Sound;

// test2

public class Test extends Game {
	
	public static Test game = new Test();
	
	public static Player player1 = new Player();
	public static Player player2 = new Player();
	
	public static int intensity = 1;
	
	//public Line line0 = new Line(0);	
	//public Line line1 = new Line(15);
	public Line line2 = new Line(100, 100, 135, 135);
	//public Line line3 = new Line(45);
	//public Line line4 = new Line(60);
	//public Line line5 = new Line(75);
	//public Line line6 = new Line(90);
	
	Sound song1 = new Sound("sounds/music/Grasslands/The Gardener.wav");
	Sound song2 = new Sound("sounds/music/Grasslands/Two to the Fourth Power Plus One.wav");
	Sound song3 = new Sound("sounds/music/Grasslands/The Gardener.wav");
	Sound flap = new Sound("sounds/flap.wav");
	Sound slither = new Sound("sounds/slither.wav");
	
	Circle circle = new Circle(1, 1, 9);
	
	Sprite sprite = new Sprite("/images/map.png");
	
	Action pan = new Action(20, 0);
	
	//Background back = new Background();
	
	int frames = 0;
	
	Action second = new Action(1000, 0);
	
	public static void main (String[] args) {
		
		//e.setFullscreen();
		
		game.setDimensions(150, 150, 5);
		
		game.start();
		
	}
	
	public void setupGame () {
		
		player1.setup(this);
		player2.setup(this);
		
		player2.y = 75;
		
		player1.x = 75;
		
		player2.type = "arrow";
		
		pan.start();
		
		second.start();
		
		/*back.addLayer("/images/layers/parallax-forest-back-trees.png", 3);
		back.addLayer("/images/layers/parallax-forest-lights.png", 0);
		back.addLayer("/images/layers/parallax-forest-middle-trees.png", 2);
		back.addLayer("/images/layers/parallax-forest-front-trees.png", 1);*/
		
	}
	
	public void update() {
		
		//if (e.key.left) line2.degrees -= 0.05;
		//if (e.key.right) line2.degrees += 0.05;
		
		if (key.o.typed) intensity++;
		if (key.p.typed && intensity > 1) intensity--;
		
		if (key.h.typed) player1.move.updateEvery++;
		if (key.k.typed) player2.move.updateEvery++;
		if (key.j.typed && player1.move.updateEvery > 1) player1.move.updateEvery--;
		if (key.l.typed && player2.move.updateEvery > 1) player2.move.updateEvery--;
		
		if (key.one.typed) playSound(song1);
		if (key.two.typed) playSound(song2);
		if (key.three.typed) playSound(song3);
		if (key.four.typed) playSound(flap);
		if (key.five.typed) playSound(slither);
		
		if (key.t.typed) circle = new Circle(circle.x, circle.y, circle.diameter - 1);
		if (key.y.typed) circle = new Circle(circle.x, circle.y, circle.diameter + 1);
		
		//if (mouse.left.clicked) screen.cam.panToMouse(this);
		screen.cam.dragWithMouse(this);
		
		if (key.i.typed) screen.cam.panTowards(0, -75);
		if (key.j.typed) screen.cam.panTowards(-75, 0);
		if (key.k.typed) screen.cam.panTowards(0, 75);
		if (key.l.typed) screen.cam.panTowards(75, 0);
		
		if (key.c.typed) screen.cam.centerPan(player1.x, player1.y);
		if (key.v.typed) screen.cam.centerPan(player2.x, player2.y);
		
		if (pan.able()) {
			
			//screen.cam.panTowards(1, 0);
			
			pan.gather();
			
		}
		
		player1.u(this);
		player2.u(this);
		
		screen.cam.update(this);
		
		Random rand = new Random();
		
		int vibrationRate = (rand.nextInt(intensity) * 2) + 1;
		
		line2 = new Line(player1.x + 8 + rand.nextInt(vibrationRate) - ((vibrationRate - 1) / 2), player1.y + 8 + rand.nextInt(vibrationRate) - ((vibrationRate - 1) / 2), player2.x + 8 + rand.nextInt(vibrationRate) - ((vibrationRate - 1) / 2), player2.y + 8 + rand.nextInt(vibrationRate) - ((vibrationRate - 1) / 2));
		
	}
	
	public void render() {
		
		//line0.draw(e);
		//line1.draw(e);
		//line3.draw(e);
		//line4.draw(e);
		//line5.draw(e);
		//line6.draw(e);
		
		Random rand = new Random();
		
		line2.color = rand.nextInt(-0xFF777777) + 1;
		
		screen.in.render(0, 0, sprite);
		
		screen.on.render(mouse.x / scale, mouse.y / scale, new Sprite(0xFFFF00FF, 3, 3));
		
		line2.draw(this);
		
		circle.render(this);
		
		//System.out.println(e.screen.pixels[((circle.y + 2) * e.screen.width) + (circle.x + 2)]);
		
		player1.render(this);
		player2.render(this);
		
		screen.in.render(screen.cam.dX, screen.cam.dY, new Sprite(0xFFFFFF00, 3, 3));
		screen.in.render(screen.cam.x, screen.cam.y, new Sprite(0xFF0000FF, 3, 3));
		
		//back.render(this);
		
		//System.out.println(screen.cam.x + ", " + screen.cam.y + "   " + screen.cam.dX + ", " + screen.cam.dY);
		
		frames++;
		
		if (second.able()) {
			
			System.out.println(frames);
			
			frames = 0;
			
			second.gather();
			
		}
		
	}
	
}
