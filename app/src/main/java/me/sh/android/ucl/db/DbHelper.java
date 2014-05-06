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
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import me.sh.android.ucl.model.GroupItem;
import me.sh.android.ucl.model.MatchItem;

/**
 * Created by Ye Lin Aung on 14/05/07.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "ucl.db";
    /**
     * UPDATE THE SCHEMA CHANGES WITH VERSION NUMBER ACCORDINGLY
     */
    private static final int DATABASE_VERSION = 1;

    private Dao<GroupItem, Integer> mGroupItemDao = null;
    private Dao<MatchItem, Integer> mMatchItemDao = null;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, GroupItem.class);
            TableUtils.createTable(connectionSource, MatchItem.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i,
                          int i2) {
        try {
            TableUtils.dropTable(connectionSource, GroupItem.class, true);
            TableUtils.dropTable(connectionSource, MatchItem.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<GroupItem, Integer> getGroupItemDao() throws SQLException {
        if (mGroupItemDao == null) {
            mGroupItemDao = getDao(GroupItem.class);
        }
        return mGroupItemDao;
    }

    public Dao<MatchItem, Integer> getMatchItemDao() throws SQLException {
        if (mMatchItemDao == null) {
            mMatchItemDao = getDao(MatchItem.class);
        }
        return mMatchItemDao;
    }


    @Override
    public void close() {
        super.close();
        mGroupItemDao = null;
        mMatchItemDao = null;
    }
}
