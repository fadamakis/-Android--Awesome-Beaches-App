package fadamakis.awesomebeach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import model.Beach;

public class BeachPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beach_page);

        Beach beach = (Beach) getIntent().getExtras().getSerializable("Beach");

        TextView t = (TextView) findViewById(R.id.textView);
        t.setText(beach.getTitle());

    }
}