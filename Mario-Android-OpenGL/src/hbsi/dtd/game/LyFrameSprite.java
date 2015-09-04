package hbsi.dtd.game;

import java.nio.Buffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.widget.Toast;

public class LyFrameSprite extends LySprite {
	//帧序列
	private int[] frame = null;
	//帧脚标
	private int index;

	private LyVec2F cutSize;

    //texture buffer array
    private Buffer[] bufferTextureArray;

    //cut tiled's num
    private LyVec2X cutNum;
	
	public LyFrameSprite(Bitmap bitmap,LyVec2F left_top, LyVec2F right_bottom){
        _bitmap = bitmap;
		//super(bitmap,left_top, right_bottom );
		cutSize = new LyVec2F(right_bottom.x-left_top.x,right_bottom.y-left_top.y);

        cutNum = new LyVec2X(bitmap.getWidth()/cutSize.x,bitmap.getHeight()/cutSize.y);
		//LyLog.info("cutNum is"+cutNum.x+","+cutNum.y);
        bufferTextureArray = new Buffer[cutNum.x*cutNum.y];

        bufferTextureArray[0] = setBitmap(left_top,right_bottom);

        setSize(cutSize.x,cutSize.y);
        
//        setSequence(new int[]{0, 1, 2});
	}
	
	public void setSequence(int []sequence){
		this.frame = sequence;
        for(int i = 0; i < sequence.length; i++){
            if(bufferTextureArray[frame[i]] ==null){
                bufferTextureArray[frame[i]] = setBitmap(
                    new LyVec2F(frame[i]%cutNum.x*cutSize.x,(int)(frame[i]/cutNum.x)*cutSize.y),
                    new LyVec2F((frame[i]%cutNum.x+1)*cutSize.x,((int)(frame[i]/cutNum.x)+1)*cutSize.y));
            }
        }

	}
	public void nextFrame(){
		if(frame == null) return;
//		Toast.makeText(LyAssets._context, "frame is null", Toast.LENGTH_SHORT);
		//frame 序列index有没有超出size,如果超出index from 0 start
		index =(index >= frame.length - 1?0:++index);
        //LyLog.info("index is "+index);
		this.setFrame(frame[index]);

	}
	public void setFrame(int index){
        if(bufferTextureArray[index]==null){
            bufferTextureArray[index] = setBitmap(
                    new LyVec2F(index%cutNum.x*cutSize.x,(int)(index/cutNum.x)*cutSize.y),
                    new LyVec2F((index%cutNum.x+1)*cutSize.x,((int)(index/cutNum.x)+1)*cutSize.y));
        }
        _bufferTexture = bufferTextureArray[index];
	}
    public void paint(GL10 gl){
        super.paint(gl);
        nextFrame();
    }


}
