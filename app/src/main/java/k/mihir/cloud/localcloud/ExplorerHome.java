package k.mihir.cloud.localcloud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExplorerHome extends AppCompatActivity implements View.OnClickListener {
    private TextView internalSDTV,LocalCloudTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer_home);
        internalSDTV=findViewById(R.id.internalSDTV);
        LocalCloudTV=findViewById(R.id.LocalCloudTV);
        internalSDTV.setOnClickListener(this);
        LocalCloudTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ExplorerHome.this,MainActivity.class);
        switch (view.getId()){
            case R.id.internalSDTV:
                intent.putExtra("source","internal");
                startActivity(intent);
                break;
            case R.id.LocalCloudTV:
                intent.putExtra("source","cloud");
                startActivity(intent);
                break;
        }

    }
}
