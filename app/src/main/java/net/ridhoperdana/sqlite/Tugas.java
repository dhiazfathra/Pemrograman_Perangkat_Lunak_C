package net.ridhoperdana.sqlite;
//package net.ridhoperdana.sqlitedb;
/**
 * Created by RIDHO on 12/5/2015.
 */
public class Tugas {
    // Labels table name
    public static final String TABLE = "Tugas";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_nama = "nama";
    public static final String KEY_tanggalDikasih = "tanggalDikasih";
    public static final String KEY_tanggalDikumpul = "tanggalDikumpul";
    public static final String KEY_kompleksitas = "kompleksitas";

    // property help us to keep data
    public int id_tugas;
    public String nama;
    public String tanggalDikasih;
    public String tanggalDikumpul;
    public int kompleksitas;
}
