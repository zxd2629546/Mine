package com.zxd.mine.m;

/**
 * Created by zxd on 2015/7/2.
 */
public class MineBlock {
    private MineCorrdinate corrdinate;
    private int value;

    public MineBlock(MineCorrdinate corrdinate, int value){
        this.corrdinate = corrdinate;
        this.value = value;
    }
    public MineBlock(int x, int y, int value){
        this.corrdinate = new MineCorrdinate(x, y);
        this.value = value;
    }
    public MineCorrdinate getCorrdinate() {
        return corrdinate;
    }
    public int getValue() {
        return value;
    }
    public void setCorrdinate(MineCorrdinate corrdinate) {
        this.corrdinate = corrdinate;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
