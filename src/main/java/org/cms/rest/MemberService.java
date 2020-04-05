package org.cms.rest;

import org.cms.dao.MemberDao;
import org.cms.models.Member;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/members")
public class MemberService
{
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMember(Member member) {
        MemberDao memberDao = new MemberDao();
        memberDao.save(member);
        System.out.println("Created member");
        return Response.status(200).build();
    }
    
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMember(Member member) {
        MemberDao memberDao = new MemberDao();
        memberDao.update(member);
        System.out.println("Updated member");
        return Response.status(200).build();
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMembers() {
        MemberDao memberDao = new MemberDao();
        GenericEntity<List<Member>> entity = new GenericEntity<List<Member>>(memberDao.getAll()){};

        System.out.println("Retrieved members");
        return Response.status(200).entity(entity).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMember(@PathParam("id") long id) {
        MemberDao memberDao = new MemberDao();
        memberDao.delete(id);
        System.out.println("Deleted member");
        return Response.status(200).build();
    }

}
