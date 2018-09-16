package com.example.vjfernandez.memento.helpers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class InputValidation {
    private Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    public boolean isInputEditTextFilled(EditText textInputEditText) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            hideKeyboardFrom(textInputEditText);
            Toast.makeText(InputValidation.this.context,"Fill the Information",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean isInputEditTextMatches(EditText textInputEditText1,EditText textInputEditText2) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            hideKeyboardFrom(textInputEditText2);
            Toast.makeText(InputValidation.this.context,"Password is not match",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
