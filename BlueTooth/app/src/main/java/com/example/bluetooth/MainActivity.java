package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private Button open, visible, discover, off;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> SBD;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        open = (Button) findViewById(R.id.open);
        visible = (Button) findViewById(R.id.visible);
        discover = (Button) findViewById(R.id.discover);
        off = (Button) findViewById(R.id.off);
        listView = (ListView) findViewById(R.id.lv);

        BA = BluetoothAdapter.getDefaultAdapter();
        Log.v("AddddddddddD", String.valueOf(BA));

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!BA.isEnabled()) {
                    Intent open = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(open, 0);
                }
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BA.disable();
            }
        });

        visible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(visible, 0);
            }
        });

        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SBD = BA.getBondedDevices();
                ArrayList list = new ArrayList();
                for (BluetoothDevice bt : SBD) list.add(bt.getName());
                final ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, list);
                listView.setAdapter(adapter);

            }
        });
    }
}
