package com.zxd.mine.v;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zxd.mine.R;

/**
 * Created by zxd on 2015/7/5.
 */
public class MenuActivity extends Activity implements DifficulityDialog.DifficultChangeListener{
    private Button single, mulServer, mulClient;
    public final static String SINGLE = "single";
    public final static String MULITY = "mulity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_layout);

        initView();
        setListener();
    }

    private void initView(){
        single = (Button)findViewById(R.id.personal_game);
        mulServer = (Button)findViewById(R.id.mulity_game_server);
        mulClient = (Button)findViewById(R.id.mulity_game_client);
    }

    private void setListener(){
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DifficulityDialog dialog = new DifficulityDialog();
                dialog.show(getFragmentManager(), SINGLE);
            }
        });
        mulServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DifficulityDialog dialog = new DifficulityDialog();
                dialog.show(getFragmentManager(), MULITY);
            }
        });
    }

    @Override
    public void onDifficultChanged(int diff, String mode) {
        if (mode.equals(SINGLE)){
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            intent.putExtra("mode", diff);
            startActivity(intent);
        }
    }
}
