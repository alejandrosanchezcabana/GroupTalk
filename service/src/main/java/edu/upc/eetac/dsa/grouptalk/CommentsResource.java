package edu.upc.eetac.dsa.grouptalk;

import edu.upc.eetac.dsa.grouptalk.dao.CommentsDAO;
import edu.upc.eetac.dsa.grouptalk.dao.CommentsDAOImpl;
import edu.upc.eetac.dsa.grouptalk.entity.AuthToken;
import edu.upc.eetac.dsa.grouptalk.entity.Comments;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Created by Alex on 26/10/15.
 */
@Path("groups/{groupid}/comments")
public class CommentsResource {
    @Context
    private SecurityContext securityContext;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(GroupTalkMediaType.GROUPTALK_COMMENT)
    public Response createSting(@PathParam("groupid") String groupid, @FormParam("title") String title, @FormParam("comment") String comment, @Context UriInfo uriInfo) throws URISyntaxException, SQLException{
        if(title==null || comment == null)
            throw new BadRequestException("all parameters are mandatory");
        CommentsDAO commentsDAO = new CommentsDAOImpl();
        Comments comments = null;
        AuthToken authenticationToken = null;
        try {
            comments=commentsDAO.createComment(securityContext.getUserPrincipal().getName(), groupid, title, comment);


        } catch (SQLException e) {
            throw new InternalServerErrorException();
        }
        URI uri = new URI(uriInfo.getAbsolutePath().toString() + "/" + comments.getId());
        return Response.created(uri).type(GroupTalkMediaType.GROUPTALK_COMMENT).entity(comments).build();
    }



}