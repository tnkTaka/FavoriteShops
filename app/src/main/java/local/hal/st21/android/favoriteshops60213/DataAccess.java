package local.hal.st21.android.favoriteshops60213;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DataAccess {

    public static Cursor findAll(SQLiteDatabase db) {
        String sql = "SELECT _id, name, tell, url, note FROM shops ";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public static Shop findByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT  _id, name, tell, url, note FROM shops WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        Shop result = null;
        if(cursor.moveToFirst()) {
            int idxName = cursor.getColumnIndex("name");
            int idxTell = cursor.getColumnIndex("tell");
            int idxUrl = cursor.getColumnIndex("url");
            int idxNote = cursor.getColumnIndex("note");
            String name = cursor.getString(idxName);
            String tell = cursor.getString(idxTell);
            String url = cursor.getString(idxUrl);
            String note = cursor.getString(idxNote);

            result = new Shop();
            result.setId(id);
            result.setName(name);
            result.setTell(tell);
            result.setUrl(url);
            result.setNote(note);
        }
        return result;
    }

    public static int update(SQLiteDatabase db, int id, String name, String tell, String url, String note) {
        String sql = "UPDATE shops SET name = ?, tell = ?, url = ?, note = ? WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, name);
        stmt.bindString(2, tell);
        stmt.bindString(3, url);
        stmt.bindString(4, note);
        stmt.bindLong(5, id);
        int result = stmt.executeUpdateDelete();
        return result;
    }

    public static long insert(SQLiteDatabase db, String name, String tell, String url, String note) {
        String sql = "INSERT INTO shops (name, tell, url, note) VALUES (?, ?, ?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, name);
        stmt.bindString(2, tell);
        stmt.bindString(3, url);
        stmt.bindString(4, note);
        long id = stmt.executeInsert();
        return id;
    }

    public static int delete(SQLiteDatabase db, int id) {
        String sql = "DELETE FROM shops WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, id);
        int result = stmt.executeUpdateDelete();
        return result;
    }
}