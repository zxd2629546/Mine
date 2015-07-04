package com.zxd.mine.c;

import com.zxd.mine.m.MineCorrdinate;
import com.zxd.mine.m.MineMap;

import java.util.Arrays;

/**
 * Created by zxd on 2015/7/3.
 */
public class MineField {
    private MineMap map;
    private boolean[][] shown;
    private MineMapGenerator generator;
    private int numOfLeftMine;

    public MineField(MineMap map){
        this.map = map;
        generator = new MineMapGenerator(map);
        numOfLeftMine = map.getNumOfMines();
    }

    public void init(){
        shown = new boolean[map.getMapHeight()][map.getMapWidth()];
        for(int i = 0;i < map.getMapHeight();i ++){
            shown[i] = new boolean[map.getMapWidth()];
            Arrays.fill(shown[i], false);
        }
        generator.createRandomMap();
    }

    public MineMap getMap() {
        return map;
    }

    public boolean[][] getShown() {
        return shown;
    }
    public boolean getShown(int x, int y){
        return shown[x][y];
    }
    public boolean getShown(int position){
        return getShown(position / map.getMapWidth(), position % map.getMapWidth());
    }
    public int getCount() {
        return map.getMapHeight() * map.getMapWidth();
    }
    public int getNumOfLeftMine() {
        return numOfLeftMine;
    }

    public void reset(int[] scale){
        map = new MineMap(scale);
        generator.setMap(map);
        init();
    }
    public int update(MineCorrdinate corrdinate){
        int ret = generator.update(corrdinate, shown);
        if (ret == -1){
            numOfLeftMine -= 1;
        }
        return ret;
    }
    public int update(int position){
        return update(Pos2Corrinate(position));
    }

    public MineCorrdinate Pos2Corrinate(int position){
        System.out.println(position + "%" + map.getMapWidth());
        return new MineCorrdinate(position / map.getMapWidth(), position % map.getMapWidth());
    }
}
