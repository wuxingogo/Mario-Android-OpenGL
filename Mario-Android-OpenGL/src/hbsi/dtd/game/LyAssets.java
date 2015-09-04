package hbsi.dtd.game;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by root on 14-9-15.
 */
public class LyAssets {
	public static Context _context;
    public static void setContext(Context context){
        _context = context;
    }
    public static Bitmap getBitmap(String fileName){



        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(_context.getAssets().open(fileName));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }


}
