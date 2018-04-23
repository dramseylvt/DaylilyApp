package com.example.drams.daylilyapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class ItemActivity extends AppCompatActivity {

    private EditText mItemTitle;
    private EditText mItemName;
    private EditText mItemDesc;

    private String mItemFileName;
    private Item mLoadedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mItemTitle = (EditText) findViewById(R.id.item_title);
        mItemName = (EditText) findViewById(R.id.item_name);
        mItemDesc = (EditText) findViewById(R.id.item_desc);

        mItemFileName = getIntent().getStringExtra("ITEM_FILE");
        if (mItemFileName != null && !mItemFileName.isEmpty()) {
            mLoadedItem = Utilities.getItemByName(getApplicationContext(), mItemFileName);

            if (mLoadedItem != null) {
                mItemTitle.setText(mLoadedItem.getTitle());
                mItemName.setText(mLoadedItem.getName());
                mItemDesc.setText(mLoadedItem.getDescription());

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_new, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_item_save:
                saveNote();

            case R.id.action_item_delete:
                deleteNote();
                break;

        }

        return true;
    }

    private void deleteNote() {
        if (mLoadedItem == null) {
            finish();
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("You are about to delete " + mItemTitle.getText().toString())
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Utilities.deleteNote(getApplicationContext(), mLoadedItem.getDateTime() + Utilities.FILE_EXTENSION);
                            Toast.makeText(getApplicationContext(), mItemTitle.getText().toString() + " was Deleted", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    })
                    .setNegativeButton("no", null)
                    .setCancelable(false);
            dialog.show();
        }
    }




    private void saveNote(){
        Item item;

        if(mItemTitle.getText().toString().trim().isEmpty() || mItemDesc.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please enter a title and description", Toast.LENGTH_LONG).show();
            return;
        }
        if(mLoadedItem == null) {
            item = new Item(System.currentTimeMillis(), mItemTitle.getText().toString(), mItemName.getText().toString(), mItemDesc.getText().toString());
        }else{
            item = new Item(mLoadedItem.getDateTime(), mItemTitle.getText().toString(), mItemName.getText().toString(), mItemDesc.getText().toString());
        }
        if(Utilities.saveItem(this, item)){
            Toast.makeText(this, "Item is saved", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Can not save the item", Toast.LENGTH_LONG).show();
        }
        finish();
    }
}
