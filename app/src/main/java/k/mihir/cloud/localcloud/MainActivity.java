package k.mihir.cloud.localcloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "LocalCloud";
    private String totalCount,source;
    String internal="internal",cloud="cloud",currentDir,root="/storage/emulated/0";
    List<String> itemString;
    ArrayAdapter<String> arrayAdapter;
    InternalSDDirectory internalSDDirectory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        source=intent.getStringExtra("source");
        ListView listView = findViewById(R.id.list_item);
        itemString = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, R.layout.listadapter, itemString);
        listView.setAdapter(arrayAdapter);
        if(source.equalsIgnoreCase(cloud)){
            CloudDirectory cloudDirectory = new CloudDirectory(itemString, arrayAdapter,null);
            cloudDirectory.execute();
        }
        else if (source.equalsIgnoreCase(internal)){
            internalSDDirectory = new InternalSDDirectory(itemString, arrayAdapter, null);
            internalSDDirectory.runnable.run();

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (source.equalsIgnoreCase(cloud)) {
                    CloudDirectory cloudDirectory = new CloudDirectory(itemString, arrayAdapter, itemString.get(i));
                    cloudDirectory.execute();
                } else if (source.equalsIgnoreCase(internal)) {
                    //InternalSDDirectory internalSDDirectory = new InternalSDDirectory(itemString, arrayAdapter, itemString.get(i));
                    internalSDDirectory.setChild(itemString.get(i));
                    internalSDDirectory.runnable.run();

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(internalSDDirectory.getCurrent().equalsIgnoreCase(root)){
            super.onBackPressed();
        }else {
            if (source.equalsIgnoreCase(cloud)) {
                CloudDirectory cloudDirectory = new CloudDirectory(itemString, arrayAdapter, "..");
                cloudDirectory.execute();
            } else if (source.equalsIgnoreCase(internal)) {
                internalSDDirectory.setChild("..");
                internalSDDirectory.runnable.run();
            }
        }}


    public String getCurrentDir() {
        return currentDir;
    }

    public void setCurrentDir(String currentDir) {
        this.currentDir = currentDir;
    }
}

