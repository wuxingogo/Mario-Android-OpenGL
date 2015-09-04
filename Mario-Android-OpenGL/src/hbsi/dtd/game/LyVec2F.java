package hbsi.dtd.game;

public class LyVec2F extends LyVec2X{
	public float x;
	public float y;
	public LyVec2F(){
        zero();
	}
    public LyVec2F zero() {
        this.x = 0;
        this.y = 0;
        return this;
    }
	public LyVec2F(float _x,float _y){
		x = _x;
		y = _y;
	}
	public LyVec2F set(float _x,float _y){
		x = _x;
		y = _y;
		return this;
	}
	public LyVec2F add(float _x,float _y){
		x += _x;
		y += _y;
		return this;
	}
	public LyVec2F add(LyVec2F node){
		x += node.x;
		y += node.y;
		return this;
	}
	public LyVec2F sub(float _x,float _y){
		x -= _x;
		y -= _y;
		return this;
	}
	public LyVec2F sub(LyVec2F node){
		x -= node.x;
		y -= node.y;
		return this;
	}
	public LyVec2F copy(){
		return this;
	}
	
	
	public static LyVec2F ZERO = new LyVec2F(0.0f,0.0f);
}
