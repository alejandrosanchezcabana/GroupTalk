package edu.upc.eetac.dsa.grouptalk.dao;

import edu.upc.eetac.dsa.grouptalk.entity.Comments;
import edu.upc.eetac.dsa.grouptalk.entity.CommentsCollection;

import java.sql.SQLException;

/**
 * Created by Alex on 24/10/15.
 */
public interface CommentsDAO {
    public Comments createComment(String creator, String groupid, String title, String comment) throws SQLException;
    public Comments getCommentById(String id) throws SQLException;
    public CommentsCollection getComment() throws SQLException;
    public Comments updateComment(String id, String comment) throws SQLException;
    public boolean deleteComment(String id) throws SQLException;
}