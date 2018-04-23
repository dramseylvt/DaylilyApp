package com.example.drams.daylilyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewItem = (ListView) findViewById(R.id.main_listview_item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_main_new_item:
                //start ItemActivity in newItemMode
                startActivity(new Intent(this, ItemActivity.class));
                break;
        }

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mListViewItem.setAdapter(null);

        ArrayList<Item> items = Utilities.getAllSavedItems(this);

        if(items ==null || items.size()==0){
            Toast.makeText(this, "You have no saved notes", Toast.LENGTH_LONG).show();
            return;
        }else{
            ItemAdapter ia = new ItemAdapter(this, R.layout.item_note, items);
            mListViewItem.setAdapter(ia);

            mListViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    String fileName = ((Item)mListViewItem.getItemAtPosition(position)).getDateTime() + Utilities.FILE_EXTENSION;

                    Intent viewItemIntent = new Intent(getApplicationContext(), ItemActivity.class);
                    viewItemIntent.putExtra("ITEM_FILE", fileName);
                    startActivity(viewItemIntent);
                }
            });
        }
    }
}
