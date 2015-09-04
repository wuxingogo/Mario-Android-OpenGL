package hbsi.dtd.game;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class LyTiledLayer extends LyNode {
	
	private int[][] map;
	private Buffer[] _textureBuffer;
	private Buffer _vertexBuffer;
	private Bitmap _bitmap;
	private LyVec2F _cutBitmapSize;
	private LyVec2X _cutBitmapNum;
	private LyVec2F _tileSize;
	private int tiledWidthNum;
	private int tiledHeightNum;
	//初始化TiledLayer
	/*
	 * 地图初始化应是mapSize x是竖列个数,y是横列个数
	 * 
	 */
	public LyTiledLayer(LyVec2X mapSize,Bitmap bitmap,LyVec2F cutBitmapSize){
		_bitmap = bitmap;
		
		map = new int[mapSize.y][mapSize.x];
		_cutBitmapSize = cutBitmapSize;
		_cutBitmapNum = new LyVec2X((int)(bitmap.getWidth()/cutBitmapSize.x),(int)(bitmap.getHeight()/cutBitmapSize.y));
		_textureBuffer = new Buffer[_cutBitmapNum.x*_cutBitmapNum.y];
	
		//LyLog.info("横列"+mapSize.y+",竖着"+mapSize.x);
	}

    public void setSize(float wid,float hei){
		float[] vertex = new float[]{
				-wid/2,-hei/2,			//LB
				wid/2,-hei/2,			//RB	
				wid/2,hei/2,			//RT
				-wid/2,hei/2,			//LT	
			};
		_vertexBuffer = ByteBuffer.allocateDirect(vertex.length * 4).
					order(ByteOrder.nativeOrder()).
					asFloatBuffer().put(vertex).
					flip();
		_tileSize = new LyVec2F(wid, hei);
		 tiledWidthNum =  (int) (LyView.getView().winSize.x/wid);
		 tiledHeightNum = (int) (LyView.getView().winSize.y/hei);
		 //LyLog.info("tiledWidthNum is " + tiledWidthNum);
		 //LyLog.info("tiledHeightNum is " + tiledHeightNum);
		 
	}
    public int getTile(int row ,int col){
        return map[col][row];
    }
    public void setTile(int row ,int col,int index){
		if(index != 0 ){
		/*
		 * 如果index下标的内存为空
		 * SetTile 把下标为index的图片内存开辟出来
		 * 图块下标的内存指向textureBuffer数组的下标的内存
		 * 因为在图层使用的时候会把地图每个图块都set一下
		 * 所以避免了内存浪费的开辟，只开辟有用的内存
		 * row是列坐标，col是行坐标
		 * 地图数组里应该是map[col][row]
		 */
		
//		LyLog.info("index"+index);
		if(_textureBuffer[index]==null){
//			LyLog.info("index "+index);
			float[] temp = new float[]{
					(index-1)%_cutBitmapNum.x*_cutBitmapSize.x/_bitmap.getWidth(),((index-1)/_cutBitmapNum.x+1)*_cutBitmapSize.y/_bitmap.getHeight(),
					((index-1)%_cutBitmapNum.x+1)*_cutBitmapSize.x/_bitmap.getWidth(),((index-1)/_cutBitmapNum.x+1)*_cutBitmapSize.y/_bitmap.getHeight(),	
					((index-1)%_cutBitmapNum.x+1)*_cutBitmapSize.x/_bitmap.getWidth(),(index-1)/_cutBitmapNum.x*_cutBitmapSize.y/_bitmap.getHeight(),
					(index-1)%_cutBitmapNum.x*_cutBitmapSize.x/_bitmap.getWidth(),(index-1)/_cutBitmapNum.x*_cutBitmapSize.y/_bitmap.getHeight(),	
			};
//			LyLog.info("LB:"+(index-1)%_cutBitmapNum.x*_cutBitmapSize.x+","+((index-1)/_cutBitmapNum.x+1)*_cutBitmapSize.y);
//			LyLog.info("RB:"+((index-1)%_cutBitmapNum.x+1)*_cutBitmapSize.x+","+((index-1)/_cutBitmapNum.x+1)*_cutBitmapSize.y);
//			LyLog.info("RT:"+((index-1)%_cutBitmapNum.x+1)*_cutBitmapSize.x+","+(index-1)/_cutBitmapNum.x*_cutBitmapSize.y);
//			LyLog.info("LT:"+(index-1)%_cutBitmapNum.x*_cutBitmapSize.x+","+(index-1)/_cutBitmapNum.x*_cutBitmapSize.y);
			/*
			 * 图块顺序与vertex坐标一致 LB,RB,RT,LT
			 */
			_textureBuffer[index] = 	ByteBuffer.allocateDirect(temp.length * 4).
					order(ByteOrder.nativeOrder()).
					asFloatBuffer().put(temp).
					flip();	
		}
		map[col][row] = index;
		}
		
	}

    @Override
    public void paint(GL10 gl){
		super.paint(gl);
		
//		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity(); 
		gl.glTranslatef(_position.x, _position.y, 0);
		
		
//		_position.x-=1;
		gl.glEnable(GL10.GL_TEXTURE_2D);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, _bitmap, 0);//3--�ƶ�����
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		_vertexBuffer.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT,0, _vertexBuffer);
		
		int startX = (int) (this._position.x/_tileSize.x);
		int startY = (int) (this._position.y/_tileSize.y);	
		startX *= -1;
		startY *= -1;
		
        //LyLog.info(""+tiledHeightNum);
		for(int i = startY; i <startY+tiledHeightNum&&i<map.length ; i++){
			for(int j = startX; j < startX + tiledWidthNum +2 &&j<map[i].length; j++){
				if( i >= 0 && j >= 0 && map[i][j] != 0){
					gl.glPushMatrix();
					gl.glTranslatef(j*_tileSize.x,  LyView.getView().winSize.y+(i+1) *-_tileSize.y, 0);
					
					gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
					_textureBuffer[map[i][j]].position(0);
					gl.glTexCoordPointer(2, GL10.GL_FLOAT, 2*4, 	_textureBuffer[map[i][j]]);
					gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
					gl.glLoadIdentity();
					gl.glPopMatrix();
				}	
			}
		}

	}


}
