package com.github.juniorpires.validate;

import android.content.Context;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.QuickRule;

public class MaiorIdadeRule extends QuickRule<EditText> {

    // Override this constructor ONLY if you want sequencing.
    public MaiorIdadeRule() {
        super();
    }

    @Override
    public boolean isValid(EditText editText) {
        String id = editText.getText().toString();

        int num = id.equals("")?0:Integer.valueOf(id);
        if (num >18) return true;


        return false;


    }

    @Override
    public String getMessage(Context context) {
        return "Deve ser maior de idade";
    }



}