package hbsi.dtd.game;

/**
 * Created by root on 14-9-19.
 */
public class LyLayerManager extends LyNode{
    private LyNode[] array;
    private int index = 0;
    public LyLayerManager(){
        array = new LyNode[5];
    }
    public void addElements(LyNode node){
        array[index++] = node;
        this.addChild(node);
    }
    public void setPosition(float x,float y){
        for(int i = 0; i < index ; i++)
        array[i].setPosition(x,y);
    }



}
