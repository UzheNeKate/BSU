package by.pmvs.lab6task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;

    int guess;
    boolean gameFinished;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvInfo = (TextView)findViewById(R.id.tvGuess);
        etInput = (EditText)findViewById(R.id.editTextNumber);
        bControl = (Button)findViewById(R.id.btnInput);

        guess = (int)(Math.random() * 100);
        gameFinished = false;
    }

    public void onClick(View v){
        int msgId = 0;
        int input = -1;
        if (!gameFinished){
            try{
                input = Integer.parseInt(etInput.getText().toString());
            }
            catch (Exception e){
                Snackbar.make(bControl, getResources().getText(R.string.empty), Snackbar.LENGTH_SHORT).show();
            }
            if(input < 1 || input > 100)
                msgId = R.string.not_valid;
            else {
                if (input > guess)
                    msgId = R.string.ahead;
                else if (input < guess)
                    msgId = R.string.behind;
                else  {
                    msgId = R.string.hit;
                    bControl.setText(getResources().getString(R.string.play_more));
                    gameFinished = true;
                }
            }
            Snackbar.make(bControl, getResources().getText(msgId), Snackbar.LENGTH_SHORT).show();
        }
        else
        {
            guess = (int)(Math.random() * 100);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameFinished = false;
        }
        etInput.setText("");
    }

    public void onExit(View view) {
        finish();
    }
}