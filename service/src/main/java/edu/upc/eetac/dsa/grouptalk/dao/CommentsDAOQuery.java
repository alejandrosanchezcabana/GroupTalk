package edu.upc.eetac.dsa.grouptalk.dao;

/**
 * Created by Alex on 24/10/15.
 */
public class CommentsDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_COMMENT = "insert into comments (id, creator, groupid, title, comment) values (UNHEX(?), UNHEX(?), UNHEX(?), ?, ?)";
    public final static String GET_COMMENT_BY_ID = "select hex(id) as id, hex(groupid) as groupid, hex(creator) as creator, title, comment, last_modified, creation_timestamp from comments where id=UNHEX(?) and groupid=UNHEX(?)";
    public final static String GET_COMMENTS = "select hex(c.id) as id, hex(c.groupid) as groupid, hex(c.creator) as creator, title, comment, creation_timestamp, last_modified from comments c order by creation_timestamp desc limit 5";
    public final static String UPDATE_COMMENT = "update comments set comment=? where id=unhex(?) ";
    public final static String DELETE_COMMENT = "delete from comments where id=unhex(?)";
    public final static String ASSIGN_ADDED_GROUPS = "insert into added_groups (userid, groupid) values (UNHEX(?), UNHEX(?))";
}