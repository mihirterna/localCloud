package k.mihir.cloud.localcloud;


import android.os.AsyncTask;

import java.io.DataOutputStream;

public class NavigDir extends AsyncTask<Void,Void,Void> {
    private DataOutputStream dataOutputStream;
    private String itemName;

    public NavigDir(String itemName) {
        this.itemName = itemName;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }
}
