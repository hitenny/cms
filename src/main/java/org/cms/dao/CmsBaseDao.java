package org.cms.dao;

import org.cms.Database;

import java.sql.ResultSet;

public class CmsBaseDao {
    protected boolean execute(String query) {
        return Database.execute(query);
    }

    protected ResultSet executeQuery(String query) {
        return Database.executeQuery(query);
    }
}
