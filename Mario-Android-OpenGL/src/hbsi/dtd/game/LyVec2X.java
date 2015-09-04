package hbsi.dtd.game;

public class LyVec2X {
	public int x;
	public int y;
	public LyVec2X(){
		x = 0;
		y = 0;
	}
	public LyVec2X set(int _x,int _y){
		x = _x;
		y = _y;
		return this;
	}
	public LyVec2X(int _x,int _y){
		x = _x;
		y = _y;
	}
    public LyVec2X(float _x,float _y){
        x = (int)_x;
        y = (int)_y;

    }
	public LyVec2X add(int _x,int _y){
		x += _x;
		y += _y;
		return this;
	}
	public LyVec2X add(LyVec2X node){
		x += node.x;
		y += node.y;
		return this;
	}
	public LyVec2X sub(int _x,int _y){
		x -= _x;
		y -= _y;
		return this;
	}
	public LyVec2X sub(LyVec2X node){
		x -= node.x;
		y -= node.y;
		return this;
	}
	public LyVec2X copy(){
		return this;
	}
	
	public static LyVec2X ZERO = new LyVec2F(0,0);
}
