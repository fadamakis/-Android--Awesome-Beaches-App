package fadamakis.awesomebeach;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import model.Beach;

public class customList extends ArrayAdapter<Beach> {

    Context context;
    int layoutResourceId;
    ArrayList<Beach> data = null;

    public customList(Context context, int layoutResourceId, ArrayList<Beach> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CustomHolder holder = null;
        Beach beach = data.get(position);

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new CustomHolder();
            holder.txtTitle = (TextView)row.findViewById(R.id.title_c);
            row.setTag(holder);
        }
        else
        {
            holder = (CustomHolder)row.getTag();
        }

        InputStream ims = null;
        try {
            ims = context.getAssets().open("images/"+ beach.getSlug() +"/1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable d = Drawable.createFromStream(ims, null);
        row.setBackground(d);
        holder.txtTitle.setText(beach.getTitle());

        return row;
    }

    static class CustomHolder
    {
        TextView txtTitle;
    }
}