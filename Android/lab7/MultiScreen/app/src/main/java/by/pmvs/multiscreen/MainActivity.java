package by.pmvs.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

    String[] islands = { "Канары", "Курилы", "Мальдивы", "Филиппины"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, islands);
        setListAdapter(adapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent1 = new Intent(MainActivity.this, Canari.class);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2 = new Intent(MainActivity.this, Curili.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(MainActivity.this, Maldivi.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        Intent intent4 = new Intent(MainActivity.this, Philippini.class);
                        startActivity(intent4);
                        break;
                }
            }
        };
        getListView().setOnItemClickListener(itemListener);
    }
}