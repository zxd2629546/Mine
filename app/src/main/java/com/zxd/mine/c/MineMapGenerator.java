package com.zxd.mine.c;

import com.zxd.mine.m.MineBlock;
import com.zxd.mine.m.MineCorrdinate;
import com.zxd.mine.m.MineMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * Created by zxd on 2015/7/2.
 */
public class MineMapGenerator {
    private MineMap map;

    public MineMapGenerator(MineMap map){
        this.map = map;
    }

    public void setMap(MineMap map) {
        this.map = map;
    }

    public void createRandomMap(){
        int width = map.getMapWidth();
        int height = map.getMapHeight();
        map.initMap(height, width);
        int numOfMines = map.getNumOfMines();
        Set<Integer> positionSet = new HashSet<Integer>(numOfMines);
        Random seed = new Random();
        while(positionSet.size() < numOfMines){
            Random generator = new Random(seed.nextInt());
            int val = generator.nextInt(width * height);
            positionSet.add(val);
        }
        Iterator<Integer> it = positionSet.iterator();
        ArrayList<MineCorrdinate> mineCorrdinates = map.getMineCorrdinates();
        while(it.hasNext()){
            int temp = it.next();
            MineCorrdinate corrdinate = new MineCorrdinate(temp / width, temp % width);
            mineCorrdinates.add(corrdinate);
            map.setValueMapValue(corrdinate, -1);
        }
        initValueMap(width, height, MineMap.DIRECT8);
    }

    private void initValueMap(int width, int height, int[][] direct){
        MineBlock[][] valueMap = map.getValueMap();
        for(int row = 0; row < height;row ++){
            for (int col = 0; col < width;col ++){
                int value = valueMap[row][col].getValue();
                if (value < 0) continue;
                for (int i = 0;i < direct.length;i ++){
                    int x = row + direct[i][0], y = col + direct[i][1];
                    if (map.hasBlock(x, y)) {
                        value += (valueMap[x][y].getValue() == -1 ? 1 : 0);
                    }
                }
                valueMap[row][col].setValue(value);
            }
        }
    }

    public int update(MineCorrdinate corrdinate, boolean[][] field){
        int value = map.getValue(corrdinate), sum = 0;
        field[corrdinate.x][corrdinate.y] = true;
        if(value == 0){
            for (int i = 0;i < MineMap.DIRECT8.length;i ++){
                int x = MineMap.DIRECT8[i][0] + corrdinate.x;
                int y = MineMap.DIRECT8[i][1] + corrdinate.y;
                if (map.hasBlock(x, y) && !field[x][y] && map.getValue(x, y) >= 0) {
                    sum += update(new MineCorrdinate(x, y), field);
                }
            }
        }
        sum += value;
        return sum;
    }
}
