package me.sh.android.ucl.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.sh.android.ucl.MainActivity;
import me.sh.android.ucl.R;
import me.sh.android.ucl.adapter.FixturesAdapter;
import me.sh.android.ucl.db.GroupItemDao;
import me.sh.android.ucl.model.GroupItem;

/**
 * Created by SH on 05/May/2014.
 */
public class MainFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private ArrayList<GroupItem> mItems = new ArrayList<GroupItem>();
    private FixturesAdapter mAdapter;
    private int groupNum;
    private ProgressDialog dialog;
    private GroupItemDao mGroupItemDao;
    @InjectView(R.id.fixture_list_view)
    ListView listView;

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_match, container, false);
        ButterKnife.inject(this, rootView);
        dialog = new ProgressDialog(getActivity());

        mGroupItemDao = new GroupItemDao(getActivity());

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mItems = (ArrayList<GroupItem>) mGroupItemDao.getGroupByGroupNum(groupNum);

        if (!mItems.isEmpty()) {
            mAdapter = new FixturesAdapter(getActivity(), mItems);
            listView.setAdapter(mAdapter);
        } else {
            getAllFixtures(groupNum);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        groupNum = getArguments().getInt(ARG_SECTION_NUMBER);
        ((MainActivity) activity).onSectionAttached(groupNum);
    }

    void getAllFixtures(final int group) {

        // clear all the items first
        mItems.clear();

        Connection connection = new Connection(getActivity());

        if (connection.isConnected()) {
            dialog.setMessage("Loading Match Data");
            dialog.show();

            String fileName = "group_a.json";
            switch (group) {
                case 1:
                    fileName = "group_a.json";
                    break;
                case 2:
                    fileName = "group_b.json";
                    break;
                case 3:
                    fileName = "group_c.json";
                    break;
                case 4:
                    fileName = "group_d.json";
                    break;
                case 5:
                    fileName = "group_e.json";
                    break;
                case 6:
                    fileName = "group_f.json";
                    break;
                case 7:
                    fileName = "group_g.json";
                    break;
                case 8:
                    fileName = "group_h.json";
                    break;
            }

            Ion.with(getActivity(), "http://uclmm.herokuapp.com/" + fileName)
                    .setLogging("iON", Log.DEBUG)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            dialog.dismiss();
                            JsonParser parser = new JsonParser();
                            JsonObject jObj = (JsonObject) parser.parse(result);
                            JsonArray jArray = jObj.getAsJsonArray("fixture");
                            Gson gson = new GsonBuilder().create();

                            for (int i = 0; i < jArray.size(); i++) {
                                JsonObject obj = jArray.get(i).getAsJsonObject();
                                GroupItem item = gson.fromJson(obj, GroupItem.class);

                                if (!item.getMatch().getGoal1().equalsIgnoreCase("")) {
                                    item.getMatch().setScore();
                                }

                                item.setGroupId(group);

                                mGroupItemDao.create(item);
                                mItems.add(item);
                                mAdapter = new FixturesAdapter(getActivity(), mItems);
                                listView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "Please check your Internet Connection.", Toast.LENGTH_LONG).show();
        }
    }

    public class Connection {
        private Context mContext;

        public Connection(Context context) {
            this.mContext = context;
        }

        public boolean isConnected() {
            ConnectivityManager connectivity =
                    (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
