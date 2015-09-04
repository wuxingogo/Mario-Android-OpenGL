package hbsi.dtd.game;

import android.content.Context;
import android.media.SoundPool;

public class LySoundPool {

    private static SoundPool pool;
	private Context _context;

    public void setContext(Context context){
		_context = context;
	}

    public static SoundPool getPool(){
		if(pool == null)
			pool = new SoundPool(0, 0, 0);
		return pool;
	}

    public static void openBGM(){
		
	}
	
	

	
}
