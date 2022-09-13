package by.pmvs.example3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements GestureOverlayView.OnGesturePerformedListener {

    GestureLibrary gLib;
    GestureOverlayView gestures;
    TextView tvOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvOut=(TextView)findViewById(R.id.tv1);
        //Загрузка жестов (gestures) из res/raw/gestures
        gLib = GestureLibraries.fromRawResource(this, R.raw.gesture);
        if (!gLib.load()) {
            //Если жесты не загружены, то выход из приложения
            finish();
        }
        gestures = (GestureOverlayView) findViewById(R.id.gov1);
        gestures.addOnGesturePerformedListener(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.gestures, menu);
//        return true;
//    }

    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        //Создаёт ArrayList c загруженными из gestures жестами
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {
            //если загружен хотя бы один жест из gestures
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0) {
                if (prediction.name.equals("one"))
                    tvOut.setText("1");
                else if (prediction.name.equals("stop"))
                    tvOut.setText("stop");
                else if (prediction.name.equals("two"))
                    tvOut.setText("2");
            }else{
                tvOut.setText("Жест неизвестен");
            }
        }
    }
}