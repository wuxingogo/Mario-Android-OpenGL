package hbsi.dtd.game;

import android.graphics.Bitmap;

/**
 * Created by root on 14-9-15.
 */
public class GameScreen extends LyScene{

    LyLayerManager manager;
    LyFrameSprite sprite1;
    public GameScreen(){

        this.setColor(8, 96, 132,255);
        this.setIsTouchEnable(true);

        Bitmap bg = LyAssets.getBitmap("bg.png");
        Bitmap sp = LyAssets.getBitmap("aa.png");
        sprite1 = new LyFrameSprite(sp,new LyVec2F(0,0),new LyVec2F(18,18));
        sprite1.setSequence(new int[]{0,1,2});
//        sprite1.setSize(40,40);
        LyVec2X vec1 = new LyVec2X(GameData1.MAP0_0[0].length,GameData1.MAP0_0.length);
        LyTiledLayer tiledlayer0 = new LyTiledLayer(vec1,bg,new LyVec2F(16,16));
        LyTiledLayer tiledlayer1 = new LyTiledLayer(vec1,bg,new LyVec2F(16,16));
        LyTiledLayer tiledlayer2 = new LyTiledLayer(vec1,bg,new LyVec2F(16,16));
        LyTiledLayer tiledlayer3 = new LyTiledLayer(vec1,bg,new LyVec2F(16,16));
        LyTiledLayer tiledlayer4 = new LyTiledLayer(vec1,bg,new LyVec2F(16,16));

        float size  = LyView.getView().winSize.y/20;
//
        tiledlayer0.setSize(size,size);
        tiledlayer1.setSize(size,size);
        tiledlayer2.setSize(size,size);
        tiledlayer3.setSize(size,size);
        tiledlayer4.setSize(size,size);

        for(int i = 0; i <GameData1.MAP0_0.length; i ++){
            for(int j = 0; j < GameData1.MAP0_0[i].length; j++){
                //if(GameData.MAP0_0[i][j]!=0)
                tiledlayer0.setTile(j, i, GameData1.MAP0_0[i][j]);
                tiledlayer1.setTile(j, i, GameData1.MAP0_1[i][j]);
                tiledlayer2.setTile(j, i, GameData2.MAP0_2[i][j]);
                tiledlayer3.setTile(j, i, GameData.MAP0_3[i][j]);
                tiledlayer4.setTile(j, i, GameData.MAP0_4[i][j]);
            }
        }

        /*
        addChild(tiledlayer0);
        addChild(tiledlayer1);
        addChild(tiledlayer2);
        addChild(tiledlayer3);
        addChild(tiledlayer4);
        */
        manager = new LyLayerManager();
        manager.addElements(tiledlayer0);
        manager.addElements(tiledlayer1);
        manager.addElements(tiledlayer2);
        manager.addElements(tiledlayer3);
        manager.addElements(tiledlayer4);
        this.addChild(manager);
        sprite1.setPosition(150.0f,150.0f);
        addChild(sprite1);
    }
    @Override
    public void update(){
        LyLog.info("update");
    }
    @Override
    public void LyTouchAny(LyVec2F touchPos) {
        manager.setPosition(_position.x-=1,0);
    }

}
