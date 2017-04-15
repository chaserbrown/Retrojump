package net.jumboturtle.game.level.tiles;

import net.jumboturtle.game.gfx.Colours;
import net.jumboturtle.game.gfx.Screen;
import net.jumboturtle.game.level.Level;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicTile(0, 0, 0, Colours.get(000, -1, -1, -1));
	public static final Tile GRASS = new BasicTile(1, 1, 0, Colours.get(-1, 3,  141,  -1));
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	
	public Tile(int id, boolean isSolid, boolean isEmitter)
	{
		this.id = (byte) id;
		if(tiles[id] != null) throw new RuntimeException("Duplicate Tile ID on " + id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		tiles[id] = this;
	}
	
	public byte getId()
	{
		return id;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public boolean isEmitter(){
		return emitter;
	}
	
	public abstract void render(Screen screen, Level level, int x, int y);
	
}
