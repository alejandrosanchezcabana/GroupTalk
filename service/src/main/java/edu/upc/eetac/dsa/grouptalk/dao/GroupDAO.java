package edu.upc.eetac.dsa.grouptalk.dao;

import edu.upc.eetac.dsa.grouptalk.entity.Groups;
import edu.upc.eetac.dsa.grouptalk.entity.GroupsCollection;

import java.sql.SQLException;

/**
 * Created by Alex on 24/10/15.
 */
public interface GroupDAO {
    public Groups createGroup(String theme, String description) throws SQLException;
    public Groups getGroupById(String id) throws SQLException;
    public GroupsCollection getGroups() throws SQLException;
    public Groups updateGroup(String id, String theme, String description) throws SQLException;
    public boolean deleteGroup(String id) throws SQLException;
}