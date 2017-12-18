package k.mihir.cloud.localcloud;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class DirClass extends AsyncTask<Void,Void,String> {
    private String name;
    private Socket socket;
    private List<String> drList;
    private ArrayAdapter<String> dirAdapter;
    public DirClass(Socket socket, List<String> dirList, ArrayAdapter<String> dirAdapter) {
        this.dirAdapter=dirAdapter;
        this.drList= dirList;
        this.socket = socket;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        drList.add(s);
        dirAdapter.notifyDataSetChanged();
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            name=dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
