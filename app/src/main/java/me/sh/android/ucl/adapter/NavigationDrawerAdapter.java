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
import me.sh.android.ucl.model.NavigationItem;

/**
 * Created by SH on 05/May/2014.
 */
public class NavigationDrawerAdapter extends ArrayAdapter<NavigationItem> {

    private Context mContext;
    private ArrayList<NavigationItem> mItems;

    public NavigationDrawerAdapter(Context context, ArrayList<NavigationItem> navItems) {
        super(context, R.layout.navigation_item, navItems);

        this.mContext = context;
        this.mItems = navItems;
    }

    private ViewHolder getHolder(View v) {
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View vi = convertView;

        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.navigation_item, null);
            holder = getHolder(vi);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        holder.groupBadge.setText(mItems.get(position).getGroupBadge());
        holder.groupName.setText(mItems.get(position).getGroupName());
        holder.groupBadge.setBackgroundColor(mItems.get(position).getGroupBadgeColor());

        return vi;
    }

    static class ViewHolder {
        @InjectView(R.id.txt_group_badge)
        TextView groupBadge;
        @InjectView(R.id.txt_group_name)
        TextView groupName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
