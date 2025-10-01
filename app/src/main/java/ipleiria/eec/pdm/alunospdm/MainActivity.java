package ipleiria.eec.pdm.alunospdm;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.Menu;
import android.widget.ListView;

import modelo.GestaoPessoas;
import modelo.ListViewAdapter;

public class MainActivity extends AppCompatActivity {

    private GestaoPessoas gestorContactos;
    private ListView lv;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gestorContactos = new GestaoPessoas();
        gestorContactos.initPessoas();
        lv = findViewById(R.id.listview);
        adapter = new ListViewAdapter(gestorContactos.getListaPessoas(), this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    public void onClickAdd(MenuItem item) {

    }
}