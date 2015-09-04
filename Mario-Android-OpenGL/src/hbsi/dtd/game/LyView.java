package hbsi.dtd.game;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class LyView extends GLSurfaceView {

	public LyVec2F winSize = null;
	public GL10 _gl;
	public LyScene _scene = null;
	public long  _delta = 0;
	public static LyView view;
	
	Context _context = null;
	public LyView(Context context) {
		super(context);
		LyAssets.setContext(context);

		this.setEGLConfigChooser(8,8,8,8,16,0);
		LyRenderer renderer = new LyRenderer();
		int screenHeight = this.getMeasuredHeight();
		int screenWidth = this.getMeasuredWidth();
		winSize = new LyVec2F(screenWidth,screenHeight);
		this.setRenderer(renderer);
	}
	public static LyView getView(){
		return view;
	}
	public LyVec2F convertTouchToWorldSpace(LyVec2F vec2){
		return new LyVec2F(vec2.x,winSize.y-vec2.y);
	}
	/*
	 * 在创建窗口之后调用的函数init
	 * 
	 */
	public void init(){

	}
    public void update(){

    }
	class LyRenderer implements Renderer{
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			 // ������Ӱƽ��
	        gl.glShadeModel(GL10.GL_SMOOTH);
	        // ��ɫ����
	        // ������Ȼ���
	        gl.glClearDepthf(1.0f);                            
	        // ������Ȳ���
	        gl.glEnable(GL10.GL_DEPTH_TEST);                        
	        // ������Ȳ��Ե�����
	        gl.glDepthFunc(GL10.GL_LEQUAL);                            
	        
	        // ����ϵͳ��͸�ӽ�������
	        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		}
	
		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			view._gl = gl;
			winSize = new LyVec2F(width,height);
			LyLog.info(winSize.x+","+winSize.y);
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glOrthof(0, width, 0, height, -5, 5);
			openTexture(gl);
			LyView.getView().init();
		}
	
		@Override
		public void onDrawFrame(GL10 gl) {
			long startTime =	System.currentTimeMillis();
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			if(_scene!=null){
				_scene.paint(gl);
			}
//			long stopTime = System.currentTimeMillis();
//			long spaceTime= stopTime  - startTime;
			//LyLog.info(spaceTime+"渲染时间");

//			if(spaceTime > _delta){
//                //LyLog.info("渲染时间 should sleep"+( spaceTime -_delta ));
//
//				try {
//					Thread.sleep( spaceTime - _delta);
//
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}

//			}
            LyView.getView().update();
				
		}

		private void openTexture(GL10 gl) {
			gl.glEnable(GL10.GL_BLEND);//开启颜色混合功能
			
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, 
					GL10.GL_ONE_MINUS_SRC_ALPHA);//͸��	
			gl.glEnable(GL10.GL_TEXTURE_2D);//��������
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
					GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR_MIPMAP_NEAREST);//�Ŵ�
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
					GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);//��С	
		}
	
	}
	
	public void runWithScene(LyScene scene){
		LyView.this._scene = scene;
	}

	public void setAnimationInterval(float deltaTime){
		view._delta = (long) (deltaTime*1000);	
	}

    private LyVec2F touchPosition = new LyVec2F();
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        LyLog.info("touch");
        if(_scene!=null &&_scene.isTouchEnable){
            touchPosition.set(event.getX(),event.getY());
            _scene.LyTouchAny(touchPosition);
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    _scene.LyTouchBegan(touchPosition);
                    break;
                case MotionEvent.ACTION_MOVE:
                    _scene.LyTouchMove(touchPosition);
                    break;
                case MotionEvent.ACTION_UP:
                    _scene.LyTouchEnded(touchPosition);
                    break;
            }

        }
        return true;
    }

}
