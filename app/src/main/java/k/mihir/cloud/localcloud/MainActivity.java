package k.mihir.cloud.localcloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "LocalCloud";
    private String totalCount;
    List<String> itemString;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //   StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //  StrictMode.setThreadPolicy(policy);
        ListView listView = findViewById(R.id.list_item);
        itemString = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.listadapter, itemString);
        listView.setAdapter(arrayAdapter);
        CloudDirectory cloudDirectory = new CloudDirectory(itemString,arrayAdapter,null);
        cloudDirectory.execute();
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CloudDirectory cd = new CloudDirectory(itemString,arrayAdapter,itemString.get(i));
                cd.execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        CloudDirectory cd = new CloudDirectory(itemString,arrayAdapter,"..");
        cd.execute();
    }
}
