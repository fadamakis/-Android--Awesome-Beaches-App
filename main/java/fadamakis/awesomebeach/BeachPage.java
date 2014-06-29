package fadamakis.awesomebeach;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import model.Beach;

public class BeachPage extends Activity {

    LinearLayout myGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beach_page);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        Beach beach = (Beach) getIntent().getExtras().getSerializable("Beach");

        TextView t = (TextView) findViewById(R.id.title);
        t.setText(beach.getTitle());

        TextView d = (TextView) findViewById(R.id.description);
        d.setText(beach.getDescription());


        myGallery = (LinearLayout)findViewById(R.id.mygallery);

        AssetManager assetManager = getAssets();
        String[] files = null;
        try {
            files = assetManager.list("images/" + beach.getSlug());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String file : files){
            myGallery.addView(insertPhoto("images/" + beach.getSlug() + "/" + file));
        }

        bindMapButton(beach);

    }

    private void bindMapButton(final Beach beach) {
        Button mapBtn = (Button) findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Build the intent
                Uri location = Uri.parse("geo:0,0?q="+ beach.getTitle());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

                // Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent);
                }
            }
        });
    }

    View insertPhoto(String path){

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(640, 480));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        InputStream ims = null;
        try {
            ims = getAssets().open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(ims, null);
        imageView.setImageDrawable(d);

        return imageView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}