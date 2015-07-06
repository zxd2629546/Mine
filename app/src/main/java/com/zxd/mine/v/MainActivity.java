package com.zxd.mine.v;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.zxd.mine.R;
import com.zxd.mine.c.MineField;
import com.zxd.mine.c.MineFieldAdapter;
import com.zxd.mine.c.ScoreContorl;
import com.zxd.mine.m.MineMap;


public class MainActivity extends Activity implements BackToMenuDialog.BackToMenuListener{
    private GridView mineField;
    private Button restart;
    private Button exit;
    private MineFieldAdapter adapter;
    private MineField field;
    private boolean isFinish;
    private ScoreContorl scoreContorl;
    private int difficulity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficulity = (Integer)getIntent().getExtras().get("mode");
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
        field = new MineField(new MineMap(MineMap.MAPSCALE[difficulity]));
        field.init();
        adapter = new MineFieldAdapter(field, mineField, MainActivity.this);
        mineField.setNumColumns(MineMap.MAPSCALE[difficulity][1]);
        mineField.setAdapter(adapter);
    }

    private void setListener(){
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRestartDialog();
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
                        showRestartDialog();
                    }
                }
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBackToMenuDialog();
            }
        });
    }

    private void showRestartDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Restart")
                .setMessage("your score is: " + scoreContorl.getScore())
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.reset(MineMap.MAPSCALE[difficulity]);
                        scoreContorl.reset();
                        isFinish = false;
                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();

    }

    private void showBackToMenuDialog(){
        BackToMenuDialog dialog = new BackToMenuDialog();
        dialog.show(getFragmentManager(), "personal_back");
    }

    @Override
    public void onBackToMenu() {
        MainActivity.this.finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            showBackToMenuDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
