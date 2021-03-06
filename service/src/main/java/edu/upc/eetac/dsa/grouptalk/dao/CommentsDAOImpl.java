package edu.upc.eetac.dsa.grouptalk.dao;

import edu.upc.eetac.dsa.grouptalk.entity.Comments;
import edu.upc.eetac.dsa.grouptalk.entity.CommentsCollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Alex on 24/10/15.
 */
public class CommentsDAOImpl implements CommentsDAO {

    @Override
    public Comments createComment(String creator, String groupid, String title, String comment) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt=null, stmt2 = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOQuery.UUID);
            stmt2 =connection.prepareStatement(CommentsDAOQuery.ASSIGN_ADDED_GROUPS);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(CommentsDAOQuery.CREATE_COMMENT);
            stmt.setString(1, id);
            stmt.setString(2, creator);
            stmt.setString(3, groupid);
            stmt.setString(4, title);
            stmt.setString(5, comment);
            stmt.executeUpdate();


            stmt2 = connection.prepareStatement(CommentsDAOQuery.ASSIGN_ADDED_GROUPS);
            stmt2.setString(1, creator);
            stmt2.setString(2, groupid);
            stmt2.executeUpdate();
            stmt2.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null || stmt2!= null) {
                stmt.close();
                stmt2.close();
            }
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return getCommentById(id, groupid);
    }

    @Override
    public Comments getCommentById(String id, String groupid) throws SQLException {
        Comments comments = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CommentsDAOQuery.GET_COMMENT_BY_ID);
            stmt.setString(1, id);
            stmt.setString(2, groupid);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comments = new Comments();
                comments.setId(rs.getString("id"));
                comments.setGroupid(rs.getString("groupid"));
                comments.setCreator(rs.getString("creator"));
                comments.setTitle(rs.getString("title"));
                comments.setComment(rs.getString("comment"));
                comments.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                comments.setLastModified(rs.getTimestamp("last_modified").getTime());
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return comments;
    }

    @Override
    public CommentsCollection getComments() throws SQLException {
        CommentsCollection commentsCollection = new CommentsCollection();

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();
            stmt = connection.prepareStatement(CommentsDAOQuery.GET_COMMENTS);

            ResultSet rs = stmt.executeQuery();
            boolean first = true;
            while (rs.next()) {
                Comments comments = new Comments();
                comments = new Comments();
                comments.setId(rs.getString("id"));
                comments.setGroupid(rs.getString("groupid"));
                comments.setCreator(rs.getString("creator"));
                comments.setTitle(rs.getString("title"));
                comments.setComment(rs.getString("comment"));
                comments.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                comments.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    commentsCollection.setNewestTimestamp(comments.getLastModified());
                    first = false;
                }
                commentsCollection.setOldestTimestamp(comments.getLastModified());
                commentsCollection.getComments().add(comments);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return commentsCollection;
    }

    @Override
    public Comments updateComment(String id, String groupid, String comment) throws SQLException {
        Comments comments = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CommentsDAOQuery.UPDATE_COMMENT);
            stmt.setString(1, id);
            stmt.setString(3, comment);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                comments = getCommentById(id, groupid);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return comments;
    }

    @Override
    public boolean deleteComment(String id, String groupid) throws SQLException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CommentsDAOQuery.DELETE_COMMENT);
            stmt.setString(1, id);

            int rows = stmt.executeUpdate();
            return (rows == 1);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
    }
}

