package fadamakis.awesomebeach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import model.Beach;


public class BeachList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beach_list);

        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void readFile() throws IOException,JSONException{

        InputStream is = this.getAssets().open("beaches.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String bufferString = new String(buffer);
        populateList(bufferString);
    }

    public void populateList(String bufferString) throws IOException,JSONException{

        JSONArray jsonArray = new JSONArray(bufferString);
        final ArrayList<Beach> beaches = new ArrayList<Beach>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            Beach beach = new Beach(jsonObj.getString("slug"), jsonObj.getString("title"), jsonObj.getString("description"));
            beaches.add(beach);
        }

        customList adapter = new customList(BeachList.this,R.layout.list_item,beaches);
        ListView list = (ListView) findViewById(R.id.listView);
//        list.setDivider(null);
        list.setDividerHeight(32);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(BeachList.this, BeachPage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Beach", (Serializable) beaches.get(position));

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }
}
