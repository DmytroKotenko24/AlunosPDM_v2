package ipleiria.eec.pdm.alunospdm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.Menu;
import android.widget.ListView;

import modelo.GestaoPessoas;
import modelo.ListViewAdapter;
import modelo.Pessoa;

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

        if (savedInstanceState == null) {
            gestorContactos = new GestaoPessoas();
            gestorContactos.lerFicheiro(this);
        } else {
            this. gestorContactos = (GestaoPessoas)
                    savedInstanceState.getSerializable("estado");
        }
        if (gestorContactos.getListaPessoas().isEmpty()) {
            gestorContactos.initPessoas();
        }

         /* gestorContactos = new GestaoPessoas();
        gestorContactos.initPessoas();*/
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

    ActivityResultLauncher<Intent> activityResultPessoa = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Pessoa p = (Pessoa) data.getSerializableExtra("pessoa");
                            if (p != null) {
                                gestorContactos.adicionarPessoa(p);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            });
    public void onClickAdd(MenuItem item) {
        Intent i = new Intent(this, PessoaActivity.class);
        activityResultPessoa.launch(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gestorContactos.gravarFicheiro(this);
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("estado", gestorContactos);
    }
}