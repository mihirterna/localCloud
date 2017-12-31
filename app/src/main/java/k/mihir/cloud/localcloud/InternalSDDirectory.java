package k.mihir.cloud.localcloud;


import android.os.Environment;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.File;
import java.util.List;

public class InternalSDDirectory {
    List<String> dirList;
    ArrayAdapter<String> dirAdapter;
    String child,current,root,back="..",TAG="LocalCloud";
    MainActivity m = new MainActivity();

    public InternalSDDirectory( List<String> dirList, ArrayAdapter<String> dirAdapter, String child) {

        this.dirList = dirList;
        this.dirAdapter = dirAdapter;
        this.child=child;
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

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
                m.setCurrentDir(file.getAbsolutePath());
            } else {
                Log.e(TAG,"Internal SD "+m.getCurrentDir());
                File file=new File(m.getCurrentDir());
                if(child.equalsIgnoreCase(back)){
                    String parentFolder=file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf("/"));
                    file = new File(parentFolder);
                    Log.e(TAG,"Internal SD new ->"+file.getAbsolutePath());
                }else{
                  file = new File(file,child);
                    Log.e(TAG,"Internal SD new ->"+file.getAbsolutePath());
                }

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
                m.setCurrentDir(file.getAbsolutePath());
            }
        }
    };

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getCurrent() {
        return m.currentDir;
    }
}
