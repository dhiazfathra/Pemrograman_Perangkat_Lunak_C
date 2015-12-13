package net.ridhoperdana.sqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener{

    Button tombol_tambah,btnGetAll;
    TextView tugas_id;

    @Override
    public void onClick(View view) {
        if (view== findViewById(R.id.tombol_tambah_tugas)){

            Intent intent = new Intent(this,TugasDetil.class);
            intent.putExtra("student_Id",0);
            startActivity(intent);

        }else {
            refreshList();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        tombol_tambah = (Button) findViewById(R.id.tombol_tambah_tugas);
        tombol_tambah.setOnClickListener(this);

        refreshList();

//        btnGetAll = (Button) findViewById(R.id.btnGetAll);
//        btnGetAll.setOnClickListener(this);

    }

    public void refreshList()
    {
        TugasRepo repo = new TugasRepo(this);
        ArrayList<HashMap<String, String>> studentList =  repo.getTugasList();
        if(studentList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    tugas_id = (TextView) view.findViewById(R.id.tugas_id);
                    String tugasid = tugas_id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(),TugasDetil.class);
                    objIndent.putExtra("tugas_id", Integer.parseInt( tugasid));
                    startActivity(objIndent);
                }
            });
            //sortinh
            //....
            //
            ListAdapter adapter = new SimpleAdapter( MainActivity.this,studentList,
                    R.layout.view_masuk_tugas, new String[] { "id","nama"}, new int[] {R.id.tugas_id, R.id.tugas_judul});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this, "Tidak ada tugas", Toast.LENGTH_SHORT).show();
        }
    }
}