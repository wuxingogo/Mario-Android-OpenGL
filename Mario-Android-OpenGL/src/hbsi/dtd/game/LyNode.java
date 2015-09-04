package hbsi.dtd.game;

import javax.microedition.khronos.opengles.GL10;

public class LyNode {
	LyNode _child										= null;
	public String tag									= "";
	public LyVec2F _position                =   new LyVec2F(0,0);
    public LyVec2F _contentSize             =   new LyVec2F(0,0);
    public LyRect _boundingBox              =   new LyRect() ;
	public void setPosition(LyVec2F Pos){
		this._position = Pos;
	}
    public void setContentSize(LyVec2F size){
        _contentSize.x = size.x;
        _contentSize.y = size.y;
        _boundingBox.setSize(size);
    }
    public LyRect boundingBox(){
        return _boundingBox.setPosition(_position);
    }
	public void addChild(LyNode child){
		if(_child == null)
			_child = child;
		else
			_child.addChild(child);
	}
	public void removeChild(LyNode child){
		if(seach(child)){
			
		}
	}
	public void setPosition(float _x,float _y){
		this._position.set(_x,_y);
	}
	public boolean seach(LyNode child){
		if(this.equals(child))
			return true;
		if(this._child !=null )	
			return _child.equals(child);
		return false;
	}
	
	public void paint(GL10 gl){

	
//		Log.d("", "paint:"+tag);
		if(_child != null){
			_child.paint(gl);
		}
	}
}
