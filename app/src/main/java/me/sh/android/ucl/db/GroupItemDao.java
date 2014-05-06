/*
 * Copyright 2014 Ye Lin Aung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.sh.android.ucl.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import me.sh.android.ucl.model.GroupItem;

/**
 * Created by Ye Lin Aung on 14/05/07.
 */
public class GroupItemDao {
    private Dao<GroupItem, Integer> mGroupItemDao;
    private ConnectionSource source;

    public GroupItemDao(Context ctx) {
        try {
            DbMgr dbManager = new DbMgr();
            DbHelper dbHelper = dbManager.getHelper(ctx);
            mGroupItemDao = dbHelper.getGroupItemDao();
            source = dbHelper.getConnectionSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(GroupItem groupItem) {
        try {
            return mGroupItemDao.create(groupItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<GroupItem> getGroupByGroupNum(int groupNum) {
        try {
            QueryBuilder<GroupItem, Integer> qb = mGroupItemDao.queryBuilder();
            return qb.where().eq("groupNum", groupNum).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllGroupItem() throws SQLException {
        TableUtils.clearTable(source, GroupItem.class);
    }
}
