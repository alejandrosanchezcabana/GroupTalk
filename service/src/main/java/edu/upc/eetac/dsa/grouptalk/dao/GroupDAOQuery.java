package edu.upc.eetac.dsa.grouptalk.dao;

/**
 * Created by Alex on 24/10/15.
 */
public interface GroupDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_GROUP = "insert into groups (id, theme, description) values (UNHEX(?), ?, ?)";
    public final static String GET_GROUP_BY_ID = "select hex(g.id) as id, g.theme, g.description from groups g where g.id=UNHEX(?)";
    public final static String GET_GROUPS = "select theme, description from groups";
    public final static String GET_ADMIN = "select hex(userid) from user_roles where role=admin";
    public final static String UPDATE_GROUP = "update groups set description=? where id=unhex(?) ";
    public final static String DELETE_GROUP = "delete from groups where id=unhex(?)";
}