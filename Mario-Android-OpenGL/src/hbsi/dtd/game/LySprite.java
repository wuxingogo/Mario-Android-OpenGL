package hbsi.dtd.game;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

public class LySprite extends LyNode {
	
	public Buffer	_bufferVertex		=	null;
	public Bitmap	_bitmap				=	null;
	public Buffer	_bufferTexture	=	null;
    private float scaleX =   1;
    private float scaleY =   1;
	/*
	 *	顶点缓存
	 *	纹理
	 *	贴图缓存
	 */
	public LySprite(){
		
	}
    public void setScale(float scale){
        this.scaleX = this.scaleY = scale;
        this.boundingBox().setScale(scaleX,scaleY);
    }
    public void setScaleX(float scaleX){
        this.scaleX = scaleX;
    }
    public void setScaleY(float scaleY){
        this.scaleY = scaleY;
    }

	public LySprite(Bitmap bitmap){
		this.tag = "Sprite Tag";
		_bitmap					=	bitmap;

        _bufferTexture = setBitmap(new LyVec2F(0,0),new LyVec2F(bitmap.getWidth(),bitmap.getHeight()));
		
		setSize(bitmap.getWidth(),bitmap.getHeight());
	}
	public LySprite(Bitmap bitmap,LyVec2F left_top, LyVec2F right_bottom ){
		_bitmap					=	bitmap;

        _bufferTexture = setBitmap(left_top,right_bottom);

		setSize(right_bottom.x-left_top.x,right_bottom.y-left_top.y);
		
	}
	public LySprite(LyVec2F size){
		setSize(size.x,size.y);
	}
	public LySprite(LySprite copy){
		this._bufferVertex	=	copy._bufferVertex;
		this._bitmap		=	copy._bitmap;
		this._bufferTexture	=	copy._bufferTexture;
	}

	public void setSize(float wid,float hei){
        this.setContentSize(new LyVec2F(wid,hei));

		float[] vertex = new float[]{
			-wid/2,-hei/2,			//LB
			wid/2,-hei/2,				//RB	
			wid/2,hei/2,				//RT
			-wid/2,hei/2,				//LT	
		};
		_bufferVertex = ByteBuffer.allocateDirect(vertex.length * 4).
				order(ByteOrder.nativeOrder()).
				asFloatBuffer().put(vertex).
				flip();
		
	}
	public Buffer setBitmap(LyVec2F left_top, LyVec2F right_bottom){
		float[] texture = new float[]{
				left_top.x				/_bitmap.getWidth(),	right_bottom.y	/_bitmap.getHeight(),		//LB
				right_bottom.x	/_bitmap.getWidth(),	right_bottom.y	/_bitmap.getHeight(),		//RB
				right_bottom.x	/_bitmap.getWidth(),	left_top.y				/_bitmap.getHeight()	,		//RT
				left_top.x				/_bitmap.getWidth(),	left_top.y				/_bitmap.getHeight()	,		//LT
		};
		Buffer bufferTexture = ByteBuffer.allocateDirect(texture.length * 4).
				order(ByteOrder.nativeOrder()).
				asFloatBuffer().put(texture).
				flip();
        return bufferTexture;
	}

	public void paint(GL10 gl){
		super.paint(gl);
		
//		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
        gl.glTranslatef(_position.x, _position.y, 0);
        gl.glScalef(scaleX,scaleY,1);


		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		_bufferVertex.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT,0, _bufferVertex);
		
		if(_bufferTexture!=null){
			
//			gl.glBlendFunc(GL10.GL_SRC_ALPHA, 
//					GL10.GL_ONE_MINUS_SRC_ALPHA);//͸��
//			
//			gl.glEnable(GL10.GL_TEXTURE_2D);//��������
			
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, _bitmap, 0);//3--�ƶ�����
			
//			gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
//					GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR_MIPMAP_NEAREST);//�Ŵ�
//			gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
//					GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);//��С
			
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			_bufferTexture.position(0);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, _bufferTexture);
			
		}
		gl.glPushMatrix();
		gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
		gl.glLoadIdentity();
		gl.glPopMatrix();
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		if(_bufferTexture!=null)
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	
	
	
}
