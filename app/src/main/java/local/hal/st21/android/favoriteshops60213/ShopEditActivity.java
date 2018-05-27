package local.hal.st21.android.favoriteshops60213;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShopEditActivity extends AppCompatActivity {

    private int _mode = ShopListActivity.MODE_INSERT;

    private int _idNo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_edit);

        Intent intent = getIntent();
        _mode = intent.getIntExtra("mode", ShopListActivity.MODE_INSERT);

        if(_mode == ShopListActivity.MODE_INSERT) {
            TextView tvTitleEdit = findViewById(R.id.tvTitleEdit);
            tvTitleEdit.setText(R.string.tv_title_insert);

            Button btnSave = findViewById(R.id.btnSave);
            btnSave.setText(R.string.btn_insert);

            Button btnDelete = findViewById(R.id.btnDelete);
        }
        else {
            _idNo = intent.getIntExtra("idNo", 0);
            DatabaseHelper helper = new DatabaseHelper(ShopEditActivity.this);
            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                Shop shopData = DataAccess.findByPK(db, _idNo);

                EditText etInputTitle = findViewById(R.id.etInputTitle);
                etInputTitle.setText(shopData.getName());

                EditText etInputTell = findViewById(R.id.etInputTell);
                etInputTell.setText(shopData.getTell());

                EditText etInputUrl = findViewById(R.id.etInputUrl);
                etInputUrl.setText(shopData.getUrl());

                EditText etInputContent = findViewById(R.id.etInputContent);
                etInputContent.setText(shopData.getNote());
            }
            catch(Exception ex) {
                Log.e("ERROR", ex.toString());
            }
            finally {
                db.close();
            }
        }
    }

    public void onSaveButtonClick(View view) {
        EditText etInputTitle = findViewById(R.id.etInputTitle);
        String inputTitle = etInputTitle.getText().toString();
        if(inputTitle.equals("")) {
            Toast.makeText(ShopEditActivity.this, R.string.msg_input_title, Toast.LENGTH_SHORT).show();
        } else {
            EditText etInputTell = findViewById(R.id.etInputTell);
            String inputTell = etInputTell.getText().toString();

            EditText etInputUrl = findViewById(R.id.etInputUrl);
            String inputUrl = etInputUrl.getText().toString();

            EditText etInputContent = findViewById(R.id.etInputContent);
            String inputContent = etInputContent.getText().toString();

            DatabaseHelper helper = new DatabaseHelper(ShopEditActivity.this);
            SQLiteDatabase db = helper.getWritableDatabase();
            try {
                if(_mode == ShopListActivity.MODE_INSERT ) {
                    DataAccess.insert(db, inputTitle, inputTell, inputUrl, inputContent);
                } else {
                    DataAccess.update(db, _idNo, inputTitle, inputTell, inputUrl, inputContent);
                }
            } catch(Exception ex) {
                Log.e("ERROR", ex.toString());
            } finally {
                db.close();
            }
            finish();
        }
    }

    public void onBackButtonClick(View view) {
        finish();
    }

    public void onDeleteButtonClick(View view) {
        int idNo = _idNo;
        Bundle extras = new Bundle();
        extras.putInt("idNo",idNo);
        DeleteDialogFragment dialog = new DeleteDialogFragment();
        dialog.setArguments(extras);
        FragmentManager manager = getSupportFragmentManager();
        dialog.show(manager,"DeleteDialogFragment");
    }

}