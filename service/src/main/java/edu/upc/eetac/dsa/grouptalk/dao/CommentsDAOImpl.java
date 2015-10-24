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
        PreparedStatement stmt = null;
        String id = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(UserDAOQuery.UUID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
                id = rs.getString(1);
            else
                throw new SQLException();

            stmt = connection.prepareStatement(CommentsDAOQuery.CREATE_COMMENT);
            stmt.setString(1, id);
            stmt.setString(2, groupid);
            stmt.setString(3, creator);
            stmt.setString(4, title);
            stmt.setString(5, comment);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) {
                connection.setAutoCommit(true);
                connection.close();
            }
        }
        return getCommentById(id);
    }

    @Override
    public Comments getCommentById(String id) throws SQLException {
        Comments comments = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CommentsDAOQuery.GET_COMMENT_BY_ID);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                comments = new Comments();
                comments.setId(rs.getString("id"));
                comments.setGroup(rs.getString("group"));
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
    public CommentsCollection getComment() throws SQLException {
        CommentsCollection stingCollection = new CommentsCollection();

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
                comments.setGroup(rs.getString("group"));
                comments.setCreator(rs.getString("creator"));
                comments.setTitle(rs.getString("title"));
                comments.setComment(rs.getString("comment"));
                comments.setCreationTimestamp(rs.getTimestamp("creation_timestamp").getTime());
                comments.setLastModified(rs.getTimestamp("last_modified").getTime());
                if (first) {
                    CommentsCollection.setNewestTimestamp(comments.getLastModified());
                    first = false;
                }
                stingCollection.setOldestTimestamp(comments.getLastModified());
                stingCollection.getComments().add(comments);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }
        return stingCollection;
    }

    @Override
    public Comments updateComment(String id, String comment) throws SQLException {
        Comments comments = null;

        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = Database.getConnection();

            stmt = connection.prepareStatement(CommentsDAOQuery.UPDATE_COMMENT);
            stmt.setString(1, comment);
            stmt.setString(2, id);

            int rows = stmt.executeUpdate();
            if (rows == 1)
                comments = getCommentById(id);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (connection != null) connection.close();
        }

        return comments;
    }

    @Override
    public boolean deleteComment(String id) throws SQLException {
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

