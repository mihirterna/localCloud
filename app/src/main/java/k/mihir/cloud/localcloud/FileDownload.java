package k.mihir.cloud.localcloud;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.Buffer;

public class FileDownload extends AsyncTask<Void,Void,byte[]> {

    private Socket  socket;
    private String extension;
    public FileDownload(Socket socket) {
        this.socket=socket;
    }

    @Override
    protected void onPostExecute(byte[] bytes) {
     /*   try (FileOutputStream fos = new FileOutputStream(today)) {
            fos.write(bytes);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

        super.onPostExecute(bytes);
    }

    @Override
    protected byte[] doInBackground(Void... voids) {
        byte[] message = new byte[32 * 1024];
        try {
            File abc = new File(Environment.getExternalStorageDirectory(),"LocalCloud");
            if (!abc.exists()) abc.mkdir();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            //int size = dataInputStream.readInt();
            String ext = dataInputStream.readUTF();
            File today = new File(abc,ext);
            Log.e("TimeStamp"," Downloading Started - >"+ext);
            Log.v("TimeStamp"," Downloading Started - >"+ext);
             //dataInputStream.readFully(message, 0, message.length);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(today));
            int count;
            while ((count = bufferedInputStream.read(message))>0){
                bos.write(message,0,count);
            }
            bos.close();
            bufferedInputStream.close();
            //   dataInputStream.readFully(message);
               Log.e("TimeStamp"," Downloading Finished");
            Log.v("TimeStamp"," Downloading Finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    return message;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
