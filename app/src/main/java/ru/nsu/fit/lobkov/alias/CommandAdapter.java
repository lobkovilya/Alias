package ru.nsu.fit.lobkov.alias;

import android.content.Context;
import android.media.Image;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lobkov on 14.11.2016.
 */

public class CommandAdapter extends BaseAdapter {
    private final String LOG_TAG = CommandAdapter.class.getSimpleName();

    private Context mCtx;
    private LayoutInflater mInflater;
    private ArrayList<String> commands = new ArrayList<>();
    private Map<Integer, ViewHolder> holderMap = new HashMap<>();
    private int count = 0;

    public CommandAdapter(Context ctx, ArrayList<String> mCommandNames) {
        this.mCtx = ctx;
        mInflater = (LayoutInflater)mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        return commands.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v(LOG_TAG, "Asked view by " + position + " pos");
        View view = convertView;
        final ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.commands_listview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.index = position;
            viewHolder.editText = (EditText) view.findViewById(R.id.command_name_edittext);
            viewHolder.btn = (ImageButton) view.findViewById(R.id.delete_command_btn);
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            viewHolder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    commands.add(viewHolder.index, s.toString());
                }
            });
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        return view;
    }

    public void addItem() {
        ++count;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        EditText editText;
        ImageButton btn;
        int index;
    }
}
