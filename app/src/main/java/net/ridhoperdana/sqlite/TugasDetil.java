package net.ridhoperdana.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by RIDHO on 12/5/2015.
 */
public class TugasDetil extends ActionBarActivity implements android.view.View.OnClickListener{

    Button btnSave;
    Button btnClose;
    EditText editTextNama;
    EditText editTextDiberikan;
    EditText editTextDikumpulkan;
    EditText editTextKompleksitas;
    private int _Tugas_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);

        btnSave = (Button) findViewById(R.id.tambah_tugas);
//        btnDelete = (Button) findViewById(R.id.btnDelete);
//        btnClose = (Button) findViewById(R.id.btnClose);

        editTextNama = (EditText) findViewById(R.id.judul_tugas);
        editTextDiberikan = (EditText) findViewById(R.id.tanggal_diberikan);
        editTextDikumpulkan = (EditText) findViewById(R.id.tanggal_kumpul);
//        editTextKompleksitas = (EditText) findViewById(R.id.)
//        editTextKompleksitas = (EditText) findViewById(R.id.pilihan_mudah)
//        editTextKompleksitas = (EditText) findViewById(R.id.pilihan_mudah)
//        editTextKompleksitas = (EditText) findViewById(R.id.pilihan_mudah)

        btnSave.setOnClickListener(this);
//        btnDelete.setOnClickListener(this);
//        btnClose.setOnClickListener(this);


        _Tugas_id =0;
        Intent intent = getIntent();
        _Tugas_id =intent.getIntExtra("tugas_id", 0);
        TugasRepo repo = new TugasRepo(this);
        Tugas tugas = new Tugas();
        tugas = repo.getTugasById(_Tugas_id);

//        editTextKompleksitas.setText(String.valueOf(tugas.kompleksitas));
        editTextNama.setText(tugas.nama);
        editTextDiberikan.setText(tugas.tanggalDikasih);
        editTextDikumpulkan.setText(tugas.tanggalDikumpul);
    }



    public void onClick(View view) {
        if (view == findViewById(R.id.tambah_tugas)) {
            TugasRepo repo = new TugasRepo(this);
            Tugas tugas = new Tugas();
            tugas.nama = editTextNama.getText().toString();
            tugas.tanggalDikasih = editTextDiberikan.getText().toString();
            tugas.tanggalDikumpul = editTextDikumpulkan.getText().toString();
            tugas.id_tugas = _Tugas_id;

            if (_Tugas_id == 0) {
                _Tugas_id = repo.insert(tugas);

                Toast.makeText(this, "Tugas sudah diperbaharui " + _Tugas_id, Toast.LENGTH_SHORT).show();
            } else {

                repo.update(tugas);
                Toast.makeText(this, "Deskripsi tugas telah diperbaharui", Toast.LENGTH_SHORT).show();
            }
        }

//        }else if (view== findViewById(R.id.btnDelete)){
//            StudentRepo repo = new StudentRepo(this);
//            repo.delete(_Student_Id);
//            Toast.makeText(this, "Student Record Deleted", Toast.LENGTH_SHORT);
//            finish();
//        }else if (view== findViewById(R.id.btnClose)){
//            finish();
//        }


    }

}