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
    private int port = 8080,count;
    private String host = "192.168.31.246";
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
        dirList.clear();
        dirAdapter.notifyDataSetChanged();
            for(int i = 0 ;i < getCount();i++ ){
                DirClass dirClass = new DirClass(socket,dirList,dirAdapter);
                dirClass.execute();
            }

        super.onPostExecute(strings);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    protected String doInBackground(Void... voids) {
        DataInputStream in ;
        try {
                ad = InetAddress.getByName(host);
                socket = new Socket(ad, port);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                if(child==null){ dataOutputStream.writeUTF("Client Is Requesting");}
                else{ dataOutputStream.writeUTF(child);}
                in = new DataInputStream(socket.getInputStream());
                inToString = in.readUTF();
                count = Integer.parseInt(inToString);
                setCount(count);
                return inToString;

        }
        catch(Exception e)
        {
            Log.e(TAG,Log.getStackTraceString(e));
        }
        return null;
    }
}
