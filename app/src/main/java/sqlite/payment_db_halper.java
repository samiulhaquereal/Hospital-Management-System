package sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class payment_db_halper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "payment.db";
    private static final String TABLE_NAME = "payment_details";
    private static final String ID = "Id";
    private static final String PATIENT_ID = "patientid";
    private static final String TRANSACTION_ID = "transactionid";
    private static final String PAYMENT_TYPE = "paymenttype";
    private static final int VERSION_NUMBER = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PATIENT_ID + " VARCHAR(255) NOT NULL," + TRANSACTION_ID + " VARCHAR(255) NOT NULL," + PAYMENT_TYPE + " VARCHAR(255) NOT NULL);";

    private static final String Drop_table = "DROP TABLE if exists " + TABLE_NAME;

    private Context context;







    public payment_db_halper(@Nullable Context context) {
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

    public long insertData(String patientid, String transactionid, String label) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PATIENT_ID, patientid);
        contentValues.put(TRANSACTION_ID, transactionid);
        contentValues.put(PAYMENT_TYPE, label);


        long rowid = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowid;


    }
}
