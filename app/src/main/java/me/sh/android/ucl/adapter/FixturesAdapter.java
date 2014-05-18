package me.sh.android.ucl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.sh.android.ucl.R;
import me.sh.android.ucl.model.GroupItem;

/**
 * Created by SH on 05/May/2014.
 */
public class FixturesAdapter extends ArrayAdapter<GroupItem> {

    private Context mContext;
    private ArrayList<GroupItem> mItems;

    public FixturesAdapter(Context context, ArrayList<GroupItem> items) {
        super(context, R.layout.match_item, items);

        mContext = context;
        mItems = items;
    }

    private ViewHolder getHolder(View vi) {
        ViewHolder holder = new ViewHolder(vi);

        return holder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View vi = convertView;

        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.match_item, null);
            holder = getHolder(vi);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.date.setText(mItems.get(position).getDate());

        holder.team1.setText(mItems.get(position).getMatch().getTeam1());
        holder.team2.setText(mItems.get(position).getMatch().getTeam2());
        holder.score.setText(mItems.get(position).getMatch().getScore());
        holder.time.setText(mItems.get(position).getTime());
        holder.stadium.setText(mItems.get(position).getStadium());

        return vi;
    }

    static class ViewHolder {
        @InjectView(R.id.date)
        TextView date;
        @InjectView(R.id.team1)
        TextView team1;
        @InjectView(R.id.team2)
        TextView team2;
        @InjectView(R.id.score)
        TextView score;
        @InjectView(R.id.time)
        TextView time;
        @InjectView(R.id.stadium)
        TextView stadium;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
