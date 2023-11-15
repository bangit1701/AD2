package bangdnph29162.fpoly.ad2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    Button btnKieu1,btnKieu2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnKieu1=findViewById(R.id.btnKieu1);
        btnKieu2=findViewById(R.id.btnKieu2);
        btnKieu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnKieu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(StartActivity.this,ActivityGrid.class);
                startActivity(mintent);
            }
        });
    }
}