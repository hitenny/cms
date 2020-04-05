package org.cms.rest;

import org.cms.dao.MemberDao;
import org.cms.models.Member;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class MemberService
{
    @POST
    @Path("/members")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMember(Member member) {
        System.out.println("Created member");

        MemberDao memberDao = new MemberDao();
        memberDao.save(member);

        return Response.status(200).build();
    }

    @GET
    @Path("/members")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMembers() {
        System.out.println("Retrieved members");
        MemberDao memberDao = new MemberDao();
        GenericEntity<List<Member>> entity = new GenericEntity<List<Member>>(memberDao.getAll()){};


        return Response.status(200).entity(entity).build();
    }

}
