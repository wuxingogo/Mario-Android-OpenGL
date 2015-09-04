package hbsi.dtd.game;

/**
 * Created by root on 14-9-15.
 */
public class LyRect {
    public LyVec2F _position;
    public LyVec2F _size;
    public LyVec2F scale;
    public LyRect(){
        _size = new LyVec2F();
        scale = new LyVec2F(1,1);
    }
    public void setScale(float scaleX,float scaleY){
        scale.set(scaleX,scaleY);
    }
    public LyRect setSize(LyVec2F size){
        this._size = size;
        return this;
    }
    public LyRect setPosition(LyVec2F position){
        this._position = position;
        return this;
    }
    public boolean containsPoint(LyVec2F position){
       if(position.x>this._position.x-this._size.x*this.scale.x/2 &&//left
          position.x<this._position.x+this._size.x*this.scale.x/2 &&//right
          position.y>this._position.y-this._size.y*this.scale.x/2 &&//top
          position.y<this._position.y+this._size.y*this.scale.y/2   //bottom
              )
           return true;
        return false;
    }

}
