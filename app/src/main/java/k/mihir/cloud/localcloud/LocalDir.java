package k.mihir.cloud.localcloud;


import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.List;

public class LocalDir extends AsyncTask<Void,Void,Void> {
    List<String> dirList;
    ArrayAdapter<String> dirAdapter;
    String child,root,back="..",TAG="LocalCloud";


    public LocalDir( List<String> dirList, ArrayAdapter<String> dirAdapter, String child) {

        this.dirList = dirList;
        this.dirAdapter = dirAdapter;
        this.child=child;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        if (child == null) {
            root = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(root);
            if (file.isDirectory()) {
                dirList.clear();
                dirAdapter.notifyDataSetChanged();
                Log.e(TAG,"Internal SD. child null "+file.getAbsolutePath()+" is a directory.");
                File[] list = file.listFiles();
                for (File aList : list) {
                    dirList.add(aList.getName());
                    dirAdapter.notifyDataSetChanged();
                }
            }
            if (file.isFile()) {
                Log.e(TAG,"Internal SD.child null"+file.getName()+" is a file.");
            }
        } else {
            File file = new File("/storage/emulated/0"+System.getProperty("user.dir"), child);
            Log.e(TAG,"Internal SD "+file.getAbsolutePath());
            if (file.isDirectory()) {
                dirList.clear();
                dirAdapter.notifyDataSetChanged();
                Log.e(TAG,"Internal SD"+file.getName()+" is a directory.");
                File[] list = file.listFiles();
                for (File aList : list) {
                    dirList.add(aList.getName());
                    dirAdapter.notifyDataSetChanged();
                }
            }
            else if (file.isFile()) {
                Log.e(TAG,"Internal SD"+file.getName()+" is a file.");
            }
            else Log.e(TAG,"Internal SD"+file.getName()+"  is nothinh");

        }
        return null;
    }

}
