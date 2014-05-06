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
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import me.sh.android.ucl.model.MatchItem;

/**
 * Created by Ye Lin Aung on 14/05/07.
 */
public class MatchItemDao {
    private Dao<MatchItem, Integer> mMatchItemDao;
    private ConnectionSource source;

    public MatchItemDao(Context ctx) {
        try {
            DbMgr dbManager = new DbMgr();
            DbHelper dbHelper = dbManager.getHelper(ctx);
            mMatchItemDao = dbHelper.getMatchItemDao();
            source = dbHelper.getConnectionSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int create(MatchItem matchItem) {
        try {
            return mMatchItemDao.create(matchItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteAllMatchItem() throws SQLException {
        TableUtils.clearTable(source, MatchItem.class);
    }
}
