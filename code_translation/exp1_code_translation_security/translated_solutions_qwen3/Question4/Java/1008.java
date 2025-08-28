import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class StudentDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydb.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    // SQL statement to create the table
    private static final String CREATE_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            " (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";

    public StudentDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This is called when the database is first created
        db.execSQL(CREATE_TABLE_SQL);
        Log.d("StudentDatabaseHelper", "Table created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table and recreate
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Creates the students table explicitly.
     * This mirrors the JavaScript `createTable()` function.
     */
    public void createTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL(CREATE_TABLE_SQL);
            db.setTransactionSuccessful();
            Log.d("StudentDatabaseHelper", "Table created successfully");
        } catch (Exception e) {
            Log.e("StudentDatabaseHelper", "Error occurred while creating the table", e);
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Inserts a new student into the students table.
     * This mirrors the JavaScript `insertStudent(name, age)` function.
     */
    public void insertStudent(String name, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            String insertSQL = "INSERT INTO " + TABLE_NAME + " (name, age) VALUES (?, ?)";
            SQLiteStatement statement = db.compileStatement(insertSQL);
            statement.bindString(1, name);
            statement.bindLong(2, age);
            statement.executeInsert();
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("StudentDatabaseHelper", "Error occurred while inserting student", e);
        } finally {
            db.endTransaction();
        }
    }
}