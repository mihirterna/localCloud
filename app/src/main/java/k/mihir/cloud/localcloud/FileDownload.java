package k.mihir.cloud.localcloud;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class FileDownload extends AsyncTask<Void,Void,byte[]> {

    private Socket  socket;
    private String extension;
    public FileDownload(Socket socket) {
        this.socket=socket;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
        super.onPostExecute(bytes);
    }

    @Override
    protected byte[] doInBackground(Void... voids) {
        int BUF_SIZE = 64000,count;

        byte[] message = new byte[BUF_SIZE];
        try {
            socket.setReceiveBufferSize(BUF_SIZE);
            socket.setSendBufferSize(BUF_SIZE);
            File abc = new File(Environment.getExternalStorageDirectory(),"LocalCloud");
            if (!abc.exists()) abc.mkdir();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String ext = dataInputStream.readUTF();
            File today = new File(abc,ext);
            Log.e("TimeStamp"," Downloading Started - >"+ext);
            Log.v("TimeStamp"," Downloading Started - >"+ext);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(today));

            while ((count = bufferedInputStream.read(message))>0){
                bos.write(message,0,count);
                bos.flush();
            }
            bos.close();
            bufferedInputStream.close();
               Log.e("TimeStamp"," Downloading Finished");
            Log.v("TimeStamp"," Downloading Finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    return message;
    }
}
