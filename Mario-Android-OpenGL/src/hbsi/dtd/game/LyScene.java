package hbsi.dtd.game;

import javax.microedition.khronos.opengles.GL10;

public class LyScene extends LyNode {

    private int red;
    private int green;
    private int blue;
    private int alpha;
    //touch enable is false
    public boolean isTouchEnable = false;

	public LyScene(){
		this.tag = "Scene Tag";
		
	}
    public void setIsTouchEnable(boolean touchEnable){
        isTouchEnable = touchEnable;
    }
    public void setColor(int red,int green,int blue,int alpha){
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    public void update(){

    }
	@Override
	public void paint(GL10 gl){
		super.paint(gl);
        gl.glClearColor(red/255.0f,green/255.0f,blue/255.0f,alpha/255.0f);
//		Log.d("", "paint"+"Scene ");
	}
    /*touch start*/
    public void LyTouchBegan(LyVec2F touchPos){
    }
    /*touch move*/
    public void LyTouchMove(LyVec2F touchPos){
    }
    /*touch ended*/
    public void LyTouchEnded(LyVec2F touchPos){
    }
    public void LyTouchAny(LyVec2F touchPos){

    }
}
