
package net.ridhoperdana.sqlite;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.text.format.DateFormat;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.Fragment;


import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;

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
    EditText waktuDiberikan;
    EditText waktuDikumpulkan;

    private int _Tugas_id=0;
    private DatePickerDialog TextDiberikan;
    private DatePickerDialog TextDikumpulkan;
    private SimpleDateFormat dateFormatter;

    private TimePickerDialog waktuDiberikanDialog;
    private TimePickerDialog waktuDikumpulkanDialog;

    private RadioGroup radioGroup;

    private int kesulitan_int;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnSave = (Button) findViewById(R.id.tambah_tugas);
//        btnDelete = (Button) findViewById(R.id.btnDelete);
//        btnClose = (Button) findViewById(R.id.btnClose);

        editTextNama = (EditText) findViewById(R.id.judul_tugas);
        editTextDiberikan = (EditText) findViewById(R.id.tanggal_diberikan);
        editTextDiberikan.setInputType(InputType.TYPE_NULL);
        editTextDikumpulkan = (EditText) findViewById(R.id.tanggal_kumpul);
        editTextDikumpulkan.setInputType(InputType.TYPE_NULL);
        waktuDiberikan = (EditText) findViewById(R.id.waktu_diberikan);
        waktuDiberikan.setInputType(InputType.TYPE_NULL);
        waktuDikumpulkan = (EditText) findViewById(R.id.waktu_dikumpulkan);
        waktuDikumpulkan.setInputType(InputType.TYPE_NULL);
//        editTextKompleksitas = (EditText) findViewById(R.id.)
//        editTextKompleksitas = (EditText) findViewById(R.id.pilihan_mudah)
//        editTextKompleksitas = (EditText) findViewById(R.id.pilihan_mudah)
//        editTextKompleksitas = (EditText) findViewById(R.id.pilihan_mudah)

        btnSave.setOnClickListener(this);

        _Tugas_id =0;
        Intent intent = getIntent();
        _Tugas_id =intent.getIntExtra("tugas_id", 0);
        TugasRepo repo = new TugasRepo(this);
        Tugas tugas = new Tugas();
        tugas = repo.getTugasById(_Tugas_id);

        editTextNama.setText(tugas.nama);


        setDateTimeField();
        setTime();
        radioButton();
    }

    private int radioButton()
    {
        radioGroup = (RadioGroup) findViewById(R.id.grupKompleksitas);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                String mudah = "Mudah";
                String biasa = "Biasa";
                String sulit = "Sulit";
                if(checkedId > -1)
                {
                    String input  = rb.getText().toString();
                    if(input.equals(mudah))
                    {
                        kesulitan_int = 1;
                    }
                    else if(input.equals(biasa))
                    {
                        kesulitan_int = 2;
                    }
                    else
                    {
                        kesulitan_int = 3;
                    }
                }
            }
        });

        return kesulitan_int;
    }

    private void setDateTimeField()
    {
        editTextDiberikan.setOnClickListener(this);
        editTextDikumpulkan.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        int hari = newCalendar.get(Calendar.DAY_OF_MONTH);
        int bulan = newCalendar.get(Calendar.MONTH);
        int tahun = newCalendar.get(Calendar.YEAR);

        TextDiberikan = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDiberikan.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        TextDikumpulkan = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDikumpulkan.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setTime()
    {
        waktuDiberikan.setOnClickListener(this);
        waktuDikumpulkan.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        int hour = newCalendar.get(Calendar.HOUR_OF_DAY);
        int minutes = newCalendar.get(Calendar.MINUTE);

        waktuDiberikanDialog = new TimePickerDialog(this, new OnTimeSetListener() {
//            @Override

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //Calendar newTime = Calendar.getInstance();
                //newTime.set(hourOfDay, minute);
                waktuDiberikan.setText(hourOfDay + ":" + minute);
            }
        }, hour, minutes, true);

        waktuDikumpulkanDialog = new TimePickerDialog(this, new OnTimeSetListener() {
//            @Override

            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //Calendar newTime = Calendar.getInstance();
                //newTime.set(hourOfDay, minute);
                waktuDikumpulkan.setText(hourOfDay + ":" + minute);
            }
        }, hour, minutes, true);
    }


    public void onClick(View view) {
        if(view == editTextDiberikan) {
            TextDiberikan.show();
        } else if(view == editTextDikumpulkan) {
            TextDikumpulkan.show();
        }
        if(view == waktuDiberikan) {
            waktuDiberikanDialog.show();
        } else if(view == waktuDikumpulkan) {
            waktuDikumpulkanDialog.show();
        }
        if (view == findViewById(R.id.tambah_tugas)) {
            TugasRepo repo = new TugasRepo(this);
            Tugas tugas = new Tugas();
            tugas.nama = editTextNama.getText().toString();
            tugas.kompleksitas = radioButton();
            tugas.tanggalDikasih = editTextDiberikan.getText().toString();
            tugas.tanggalDikumpul = editTextDikumpulkan.getText().toString();
            //tugas.tanggalDikasih = editTextDiberikan.getText().toString();
            tugas.waktuDikasih = waktuDiberikan.getText().toString();
            tugas.waktuDikumpul = waktuDikumpulkan.getText().toString();
            tugas.id_tugas = _Tugas_id;

            if (_Tugas_id == 0) {
                _Tugas_id = repo.insert(tugas);

                Toast.makeText(this, "Tugas sudah diperbaharui " + tugas.kompleksitas, Toast.LENGTH_SHORT).show();
            } else {

                repo.update(tugas);
                Toast.makeText(this, "Deskripsi tugas telah diperbaharui", Toast.LENGTH_SHORT).show();
            }
            System.out.println("-----------------------------------masuk listener");
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