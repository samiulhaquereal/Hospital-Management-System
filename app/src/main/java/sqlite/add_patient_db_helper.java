package sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class add_patient_db_helper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "patients.db";
    private static final String TABLE_NAME = "patients_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String Address = "Address";
    private static final String PHONE_NUMBER = "Phone";
    private static final String GENDER = "Gender";
    private static final String DATE = "Date";
    private static final String BED_NO = "Bedno";
    private static final int VERSION_NUMBER = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) NOT NULL," + Address + " VARCHAR(255) NOT NULL,"+ PHONE_NUMBER + " VARCHAR(255) NOT NULL,"+ GENDER +" VARCHAR(255) NOT NULL,"+ DATE +" VARCHAR(255) NOT NULL,"+ BED_NO +" INTEGER NOT NULL);";

    private static final String display = "SELECT * FROM " + TABLE_NAME;
    private static final String displaybed = "SELECT SUM(Bedno) FROM " + TABLE_NAME;
    private static final String displayp = "SELECT COUNT(Name) FROM " + TABLE_NAME;
    private static final String Drop_table = "DROP TABLE if exists " + TABLE_NAME;

    private Context context;








    public add_patient_db_helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

            sqLiteDatabase.execSQL(CREATE_TABLE);
            //Toast.makeText(context, "Table Created", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {

            sqLiteDatabase.execSQL(Drop_table);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "Table Upgrade", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {

            Toast.makeText(context, "Exception : " + e, Toast.LENGTH_SHORT).show();

        }

    }

    public long insertData(String namet, String addresst, String phonet, String gendert,String datet,String bedt) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, namet);
        contentValues.put(Address, addresst);
        contentValues.put(PHONE_NUMBER, phonet);
        contentValues.put(GENDER, gendert);
        contentValues.put(DATE, datet);
        contentValues.put(BED_NO, bedt);


        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowid;


    }

    public Cursor displayalldata() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(display, null);

        return cursor;


    }
    public Cursor displaypa() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(displayp, null);

        return cursor;


    }
    public Cursor displaybed() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(displaybed, null);

        return cursor;


    }


    public int delete_data(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});


    }


}
