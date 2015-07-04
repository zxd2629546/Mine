package com.zxd.mine.m;

import java.util.ArrayList;

/**
 * Created by zxd on 2015/7/1.
 */
public class MineMap {
    public final static int[][] MAPSCALE = {{9, 9, 10}, {16, 16, 40}, {30, 16, 99}};
    public final static int[][] DIRECT4 = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public final static int[][] DIRECT8 = {{0, 1}, {1, 0}, {0, -1}, {-1, 0},
                                    {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};

    /**
     * <pre>for each position:
     * -1 means mine, 0 means nothing
     * and others are stand for how many mine around</pre>
     */
    private MineBlock[][] valueMap;
    private int numOfMines;
    private ArrayList<MineCorrdinate> mineCorrdinates;

    public MineMap(int[] scale){
        initMap(scale[0], scale[1]);
        numOfMines = scale[2];
        mineCorrdinates = new ArrayList<MineCorrdinate>(numOfMines);
    }

    public void initMap(int height, int width){
        valueMap = new MineBlock[height][width];
        for (int i = 0;i < height;i ++){
            valueMap[i] = new MineBlock[width];
            for (int j = 0;j < width;j ++){
                valueMap[i][j] = new MineBlock(i, j, 0);
            }
        }
    }

    public int getMapWidth(){
        return valueMap[0].length;
    }
    public int getMapHeight(){
        return valueMap.length;
    }
    public int getNumOfMines() {
        return numOfMines;
    }
    public ArrayList<MineCorrdinate> getMineCorrdinates() {
        return mineCorrdinates;
    }
    public MineBlock[][] getValueMap() {
        return valueMap;
    }
    public int getValue(int x, int y){
        return valueMap[x][y].getValue();
    }
    public int getValue(int position){
        return getValue(position / getMapWidth(), position % getMapWidth());
    }
    public int getValue(MineCorrdinate corrdinate){
        return getValue(corrdinate.x, corrdinate.y);
    }
    public void setValueMapValue(MineCorrdinate corrdinate, int value) {
        valueMap[corrdinate.x][corrdinate.y].setValue(value);
    }

    public float calculateDistanceOfMines(){
        float distance = 0.0f;
        for(int i = 0;i < mineCorrdinates.size();i ++){
            for(int j = i + 1;j < mineCorrdinates.size();j ++){
                distance += mineCorrdinates.get(i).calculateDistance(mineCorrdinates.get(j));
            }
        }
        return distance;
    }

    public boolean hasBlock(int x, int y){
        return x >= 0 && x < getMapHeight() && y >= 0 && y < getMapWidth();
    }
}
