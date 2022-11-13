package ro.ase.pdm.app1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvMasini;
    List<Masina> listMasini = new ArrayList<>();
    ArrayAdapter<Masina> adapter;

    final int CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(view ->{
            Intent intent = new Intent(this, AdaugaMasina.class);
            startActivityForResult(intent,CODE);
        });

        listMasini.add(new Masina("Marca",2000,new Date(),TipMasina.DIESEL));

        lvMasini = findViewById(R.id.listViewMain);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listMasini);
        lvMasini.setAdapter(adapter);

        if(savedInstanceState !=null)
        {
            listMasini = (List<Masina>) savedInstanceState.get("listkey");
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listMasini);
            lvMasini.setAdapter(adapter);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == CODE)
        {
            if(data !=null){
                Masina masina = (Masina) data.getSerializableExtra("MASINA");
                listMasini.add(masina);
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("listkey",(Serializable) listMasini);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @NonNull
    @Override
    public MenuInflater getMenuInflater() {
        return super.getMenuInflater();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.adaugaMasina)
        {
            Intent intent = new Intent(this,AdaugaMasina.class);
            startActivityForResult(intent,CODE);
            return true;
        }
        if(item.getItemId() == R.id.statistici)
        {
            Intent intent = new Intent(this,Statistici.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}