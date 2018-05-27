package local.hal.st21.android.favoriteshops60213;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append("CREATE TABLE shops (");
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append("name TEXT NOT NULL,");
        sb.append("tell TEXT,");
        sb.append("url TEXT,");
        sb.append("note TEXT");
        sb.append(");");
        String sql = sb.toString();

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}