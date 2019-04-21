package sn.zhang.exam_reporter_v2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import flex.messaging.io.amf.ASObject;
import flex.messaging.io.amf.client.AMFConnection;
import sn.zhang.amfparser.Config;
import sn.zhang.amfparser.utils.Tools;
import sn.zhang.amfparser.utils.values;
public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        try {
            new Config().main(null);
        } catch (Exception e) {

        }
        setSupportActionBar((Toolbar) (findViewById(R.id.toolbar)));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });
    }

    public void query() {
        values values = new values();
        Tools tools = new Tools();

        try {

            AMFConnection amfConnection = new AMFConnection();
            amfConnection.connect(values.url);
            Object result1 = amfConnection.call(values.command, new Object[]{19868, values.studentID, "Why not check my token???"});
            ASObject asObject = (ASObject) result1;
            ArrayList rootMap = (ArrayList) asObject.get("source");
            tools.query(rootMap, asObject, values);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

