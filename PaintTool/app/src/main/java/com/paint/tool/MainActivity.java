package com.paint.tool;

import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.paint.tool.view.ImageTool;


public class MainActivity extends ActionBarActivity {

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        container=(FrameLayout)findViewById(R.id.pbcontainer);
        ToolController.getController().init(this,container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Line");
        menu.add("Rect");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ToolController.getController().selectRotateTool();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onTouchEvent(MotionEvent event) {
        ToolController.getController().touchEvent(event);
        return true;
    }
}
