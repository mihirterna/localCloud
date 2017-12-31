package k.mihir.cloud.localcloud;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class CloudDirectory extends AsyncTask<Void,Void,String> {

    private String TAG = "LocalCloud";
    private List<String> dirList;
    private ArrayAdapter<String> dirAdapter;
    private int port = 8080;
    private String count;
    private String host = "192.168.31.246",word="file";
    private String inToString = null;
    private Socket socket;
    private InetAddress ad;
    private String child;

    CloudDirectory(List<String> dirList, ArrayAdapter<String> dirAdapter, String child) {
        this.dirList = dirList;
        this.dirAdapter = dirAdapter;
        this.child= child;
    }

    @Override
    protected void onPostExecute(String strings) {
        try {
        if (getCount().equalsIgnoreCase(word)) {
            FileDownload fileDownload = new FileDownload(socket);
            fileDownload.execute();
        } else {
            dirList.clear();
            dirAdapter.notifyDataSetChanged();
            for (int i = 1; i <= Integer.parseInt(getCount()); i++) {
                DirClass dirClass = new DirClass(socket, dirList, dirAdapter);
                dirClass.execute();
            }
        }
    }
    catch (NullPointerException ne ){
            Log.e(TAG,Log.getStackTraceString(ne));
        }
        super.onPostExecute(strings);
    }

    private String getCount() {
        return count;
    }

    private void setCount(String count) {
        this.count = count;
    }

    @Override
    protected String doInBackground(Void... voids) {
        DataInputStream in ;
        DataOutputStream dataOutputStream ;
        try {
                ad = InetAddress.getByName(host);
                socket = new Socket(ad, port);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                if(child==null){ dataOutputStream.writeUTF("init");}
                else{ dataOutputStream.writeUTF(child);}
                in = new DataInputStream(socket.getInputStream());
                inToString = in.readUTF();
                setCount(inToString);
                return inToString;

        }
        catch(Exception e)
        {
            Log.e(TAG,Log.getStackTraceString(e));
        }
        return null;
    }
}
