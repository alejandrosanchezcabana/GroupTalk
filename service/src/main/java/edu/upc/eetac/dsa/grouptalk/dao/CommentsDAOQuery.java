package edu.upc.eetac.dsa.grouptalk.dao;

/**
 * Created by Alex on 24/10/15.
 */
public class CommentsDAOQuery {
    public final static String UUID = "select REPLACE(UUID(),'-','')";
    public final static String CREATE_COMMENT = "insert into comments (id, groupid, creator, title, comment) values (UNHEX(?), unhex(?), ?, ?)";
    public final static String GET_COMMENT_BY_ID = "select hex(s.id) as id, hex(s.creator) as creator, s.title, s.comment, s.creation_timestamp, s.last_modified, u.fullname from comments s, users u where s.id=unhex(?) and u.id=s.creator";
    public final static String GET_COMMENTS = "select hex(id) as id, hex(creator) as creator, title, creation_timestamp, last_modified from comments";
    public final static String UPDATE_COMMENT = "update comments set comment=? where id=unhex(?) ";
    public final static String DELETE_COMMENT = "delete from comments where id=unhex(?)";
}