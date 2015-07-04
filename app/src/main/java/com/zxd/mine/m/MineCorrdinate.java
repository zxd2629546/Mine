package com.zxd.mine.m;

/**
 * Created by zxd on 2015/7/2.
 */
public class MineCorrdinate {
    public int x;
    public int y;

    public MineCorrdinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double calculateDistance(MineCorrdinate mine){
        return Math.sqrt((x - mine.x) * (x - mine.x) + (y - mine.y) * (y - mine.y));
    }
}
