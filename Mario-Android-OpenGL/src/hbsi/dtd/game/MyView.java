package hbsi.dtd.game;

import android.content.Context;


public class MyView extends LyView {


	public MyView(Context context) {
		super(context);
	}
	

    @Override
	public void init(){
        StartScreen gs = new StartScreen();
        this.runWithScene(gs);
	}
    @Override
    public void update(){
        if(this._scene!=null)
        _scene.update();
    }
}
