package com.zxd.mine.c;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.zxd.mine.R;
import com.zxd.mine.m.MineMap;


public class MainActivity extends Activity {
    private GridView mineField;
    private Button restart;
    private Button exit;
    private MineFiledAdapter adapter;
    private MineField field;
    private boolean isFinish;
    private ScoreContorl scoreContorl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isFinish = false;
        initViews();
        initField();
        setListener();
    }

    private void initViews(){
        mineField = (GridView)findViewById(R.id.mine_field);
        restart = (Button)findViewById(R.id.restart);
        scoreContorl = new ScoreContorl(this);
        exit = (Button)findViewById(R.id.exit);
    }

    private void initField(){
        field = new MineField(new MineMap(MineMap.MAPSCALE[2]));
        field.init();
        adapter = new MineFiledAdapter(field, mineField, MainActivity.this);
        mineField.setNumColumns(MineMap.MAPSCALE[2][1]);
        mineField.setAdapter(adapter);
    }

    private void setListener(){
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.reset(MineMap.MAPSCALE[2]);
                scoreContorl.reset();
                isFinish = false;
            }
        });
        mineField.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isFinish) {
                    return;
                }
                boolean shown = field.getShown(position);
                if (!shown) {
                    int ret = adapter.update(position);
                    scoreContorl.updateScore(scoreContorl.val2score(ret));
                    if (field.getNumOfLeftMine() <= 0) {
                        isFinish = true;
                    }
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }
}
