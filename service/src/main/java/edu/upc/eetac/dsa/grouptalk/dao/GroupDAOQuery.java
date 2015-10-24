package edu.upc.eetac.dsa.grouptalk.dao;

/**
 * Created by Alex on 24/10/15.
 */
public interface GroupDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_GROUP = "insert into groups (id, theme, description) values (UNHEX(?), unhex(?), ?, ?)";
    public final static String GET_GROUP_BY_ID = "select hex(s.id) as id, s.theme, s.description, from stings s where s.id=unhex(?)";
    public final static String GET_GROUPS = "select hex(id) as id, theme, description, creation_timestamp, last_modified from groups";
    public final static String UPDATE_GROUP = "update groups set description=? where id=unhex(?) ";
    public final static String DELETE_GROUP = "delete from groups where id=unhex(?)";
}