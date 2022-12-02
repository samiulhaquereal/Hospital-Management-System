package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class doctor_appointment_db_halper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "appointment.db";
    private static final String TABLE_NAME = "appointment_details";
    private static final String ID = "Id";
    private static final String NAME = "Name";
    private static final String PHONE_NUMBER = "Phone";
    private static final String GENDER = "Gender";
    private static final String DATE = "Date";
    private static final String DOCTOR_NAM = "doctorname";
    private static final int VERSION_NUMBER = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) NOT NULL,"+ PHONE_NUMBER + " VARCHAR(255) NOT NULL,"+ GENDER +" VARCHAR(255) NOT NULL,"+ DATE +" VARCHAR(255) NOT NULL,"+ DOCTOR_NAM +" VARCHAR(255) NOT NULL);";

    private static final String Drop_table = "DROP TABLE if exists " + TABLE_NAME;
    private static final String displaydoc = "SELECT COUNT (DISTINCT(doctorname)) FROM " + TABLE_NAME;
    private static final String display = "SELECT * FROM " + TABLE_NAME;


    private Context context;

    public doctor_appointment_db_halper(@Nullable Context context) {
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
    public Cursor displayalldata() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(display, null);

        return cursor;


    }


    public long insertData(String namet, String phonet, String userGender,String datet,String docnam) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, namet);
        contentValues.put(PHONE_NUMBER, phonet);
        contentValues.put(GENDER, userGender);
        contentValues.put(DATE, datet);
        contentValues.put(DOCTOR_NAM, docnam);


        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowid;


    }
    public Cursor displaydoc() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(displaydoc, null);

        return cursor;


    }
}
