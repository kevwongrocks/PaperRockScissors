package com.example.paperrockscissors;

import android.os.Bundle;
import android.app.Activity;
//import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
//import android.annotation.TargetApi;
import android.content.Intent;
//import android.os.Build;

public class DisplayMessageActivity extends Activity {

    //@SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent bobIntent = getIntent();
        String message = bobIntent.getStringExtra(MainActivity.statusMessage);
        
        TextView kevText = new TextView(DisplayMessageActivity.this);
        kevText.setText(message);
        LinearLayout kevLay = (LinearLayout) findViewById(R.id.LinearDisplay);
        kevLay.addView(kevText);
        
        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(v.getContext(), MainActivity.class);
				v.getContext().startActivity(intent);
			}
			
		});
        
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
