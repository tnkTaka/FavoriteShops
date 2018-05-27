package local.hal.st21.android.favoriteshops60213;

import java.sql.Timestamp;

public class Shop {

    private int _id;
    private int _idNo;
    private String _name;
    private String _tell;
    private String _url;
    private String _note;
    private Timestamp _updateAt;

    //以下アクセサメソッド

    public int getId() {
        return _id;
    }
    public void setId(int id) {
        _id = id;
    }
    public String getName() {
        return _name;
    }
    public void setName(String name) {
        _name = name;
    }
    public String getTell() {
        return _tell;
    }
    public void setTell(String tell) {
        _tell = tell;
    }
    public String getUrl() {
        return _url;
    }
    public void setUrl(String url) {
        _url = url;
    }
    public String getNote() {
        return _note;
    }
    public void setNote(String note) {
        _note = note;
    }
}
