package net.ridhoperdana.sqlite;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RIDHO on 12/5/2015.
 */

public class TugasRepo {
    private DBHelper dbHelper;

    public TugasRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public int insert(Tugas tugas)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Tugas.KEY_nama, tugas.nama);
        values.put(Tugas.KEY_tanggalDikasih, tugas.tanggalDikasih);
        values.put(Tugas.KEY_tanggalDikumpul, tugas.tanggalDikumpul);
        values.put(Tugas.KEY_waktuDikasih, tugas.waktuDikasih);
        values.put(Tugas.KEY_tanggalDikumpul, tugas.waktuDikumpul);
        values.put(Tugas.KEY_kompleksitas, tugas.kompleksitas);

        long id_tugas = db.insert(Tugas.TABLE, null, values);
        db.close();
        return (int) id_tugas;
    }

    public void delete(int id_tugas)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Tugas.TABLE, Tugas.KEY_ID + "= ?", new String[] {String.valueOf(id_tugas)});
        db.close();
    }

    public void update(Tugas tugas)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Tugas.KEY_nama, tugas.nama);
        values.put(Tugas.KEY_tanggalDikasih, tugas.tanggalDikasih);
        values.put(Tugas.KEY_tanggalDikumpul, tugas.tanggalDikumpul);
        values.put(Tugas.KEY_kompleksitas, tugas.kompleksitas);

        db.update(Tugas.TABLE, values, Tugas.KEY_ID + "= ?", new String[]{String.valueOf(tugas.id_tugas)});

        db.close();
    }

    public ArrayList<HashMap<String, String>> getTugasList()
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Tugas.KEY_ID + "," +
                Tugas.KEY_nama + "," + Tugas.KEY_tanggalDikasih +
                "," + Tugas.KEY_tanggalDikumpul + "," + Tugas.KEY_waktuDikasih + "," + Tugas.KEY_waktuDikumpul + "," + Tugas.KEY_kompleksitas + " FROM " + Tugas.TABLE;

        ArrayList<HashMap<String, String>> tugaslist = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do {
                HashMap<String, String> tugas = new HashMap<String, String>();

                tugas.put("id", cursor.getString(cursor.getColumnIndex(Tugas.KEY_ID)));
                tugas.put("nama", cursor.getString(cursor.getColumnIndex(Tugas.KEY_nama)));
                Log.d("log id", cursor.getString(cursor.getColumnIndex(Tugas.KEY_ID)));
                Log.d("log nama", cursor.getString(cursor.getColumnIndex(Tugas.KEY_nama)));
                tugaslist.add(tugas);
            }
            while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tugaslist;
    }

    public Tugas getTugasById(int id)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT " + Tugas.KEY_ID + "," +
                Tugas.KEY_nama + "," + Tugas.KEY_tanggalDikasih + "," +
                Tugas.KEY_tanggalDikumpul + "," + Tugas.KEY_waktuDikasih + "," + Tugas.KEY_waktuDikumpul + "," + Tugas.KEY_kompleksitas + " FROM " + Tugas.TABLE + " WHERE " + Tugas.KEY_ID + "=?";

        int iCount = 0;
        Tugas tugas = new Tugas();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(id)});

        if(cursor.moveToFirst())
        {
            do {
                tugas.id_tugas = cursor.getInt(cursor.getColumnIndex(Tugas.KEY_ID));
                tugas.nama = cursor.getString(cursor.getColumnIndex(Tugas.KEY_nama));
                tugas.tanggalDikasih = cursor.getString(cursor.getColumnIndex(Tugas.KEY_tanggalDikasih));
                tugas.tanggalDikumpul = cursor.getString(cursor.getColumnIndex(Tugas.KEY_tanggalDikumpul));
                tugas.waktuDikasih = cursor.getString(cursor.getColumnIndex(Tugas.KEY_waktuDikasih));
                tugas.waktuDikumpul = cursor.getString(cursor.getColumnIndex(Tugas.KEY_waktuDikumpul));
                tugas.kompleksitas = cursor.getInt(cursor.getColumnIndex(Tugas.KEY_kompleksitas));

            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return tugas;
    }
}
