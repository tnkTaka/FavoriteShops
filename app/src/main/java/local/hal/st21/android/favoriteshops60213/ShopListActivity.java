package local.hal.st21.android.favoriteshops60213;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ShopListActivity extends AppCompatActivity {

    static final int MODE_INSERT = 1;
    static final int MODE_EDIT = 2;
    private ListView _lvShopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);

        _lvShopList = findViewById(R.id.lvShopList);
        _lvShopList.setOnItemClickListener(new ListItemClickListener());
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper helper = new DatabaseHelper(ShopListActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = DataAccess.findAll(db);
        String[] from = {"name"};
        int[] to = {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(ShopListActivity.this, android.R.layout.simple_list_item_1, cursor, from, to, 0);
        _lvShopList.setAdapter(adapter);
    }

    public void onNewButtonClick(View view) {
        Intent intent = new Intent(ShopListActivity.this, ShopEditActivity.class);
        intent.putExtra("mode", MODE_INSERT);
        startActivity(intent);
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Cursor item = (Cursor) parent.getItemAtPosition(position);
            int idxId = item.getColumnIndex("_id");
            int idNo =  item.getInt(idxId);

            Intent intent = new Intent(ShopListActivity.this, ShopEditActivity.class);
            intent.putExtra("mode", MODE_EDIT);
            intent.putExtra("idNo", idNo);
            startActivity(intent);
        }
    }
}