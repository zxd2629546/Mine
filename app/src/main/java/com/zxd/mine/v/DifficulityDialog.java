package com.zxd.mine.v;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.zxd.mine.R;

/**
 * Created by zxd on 2015/7/5.
 */
public class DifficulityDialog extends DialogFragment implements View.OnClickListener{
    private Button easy, mid, difficult;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.difficulity_select_menu, null);

        easy = (Button)view.findViewById(R.id.easy);
        mid = (Button)view.findViewById(R.id.mid);
        difficult = (Button)view.findViewById(R.id.difficult);

        easy.setOnClickListener(this);
        mid.setOnClickListener(this);
        difficult.setOnClickListener(this);

        builder.setView(view);

        return builder.create();
    }

    @Override
    public void onClick(View v) {
        int result = 0;
        if (v.equals(mid)){
            result = 1;
        } else if(v.equals(difficult)){
            result = 2;
        }
        ((DifficultChangeListener)getActivity()).onDifficultChanged(result, getTag());
        dismiss();
    }

    public interface DifficultChangeListener{
        void onDifficultChanged(int diff, String mode);
    }
}
