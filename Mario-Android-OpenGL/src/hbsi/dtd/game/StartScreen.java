package hbsi.dtd.game;


/**
 * Created by root on 14-9-15.
 */
public class StartScreen extends LyScene {
   LySprite startButton;
   public StartScreen(){
       this.setColor(255,0,255,255);
       this.setIsTouchEnable(true);
       startButton = new LySprite(LyAssets.getBitmap("startgame.png"));
       startButton.setScale(0.3f);
       startButton.setPosition(LyView.getView().winSize.x/2,LyView.getView().winSize.y/2);
       this.addChild(startButton);
   }
    LyVec2F worldPos;
    @Override
    public void LyTouchAny(LyVec2F touchPos) {
        LyLog.info("one touch"+touchPos.x+","+touchPos.y);
        worldPos = LyView.getView().convertTouchToWorldSpace(touchPos);
        LyLog.info("two touch"+worldPos.x+","+worldPos.y);
        if(startButton.boundingBox().containsPoint(worldPos)){
            LyLog.info("click the startButton!");
            startButton.setScale(0.4f);
        }
        else{
            startButton.setScale(0.3f);
        }

    }

    @Override
    public void LyTouchEnded(LyVec2F touchPos) {
        worldPos = LyView.getView().convertTouchToWorldSpace(touchPos);
        if(startButton.boundingBox().containsPoint(worldPos)){
            GameScreen gs = new GameScreen();
            LyView.getView().runWithScene(gs);
        }
    }
}
