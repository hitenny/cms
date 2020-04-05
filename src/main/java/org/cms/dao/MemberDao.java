package org.cms.dao;

import org.cms.models.Member;
import org.cms.models.MembershipType;
import org.cms.models.RelationType;
import org.cms.models.Sex;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDao extends CmsBaseDao implements ICmsDao<Member>  {
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
    
    @Override
    public Optional<Member> get(long id) {
        return Optional.ofNullable(getMemberFromDb(id));
    }

    @Override
    public List<Member> getAll() {
        return getMembersFromDb();
    }

    @Override
    public void save(Member member) {
        String query = "INSERT INTO MEMBERS VALUES(NULL,'" + member.getName() + "', '" + member.getSex() + "', " + member.getPhone() + ", '" + member.getEmail() + "', '" + member.getAddress() + "', '" + member.getMemberType() + "', " + member.getRelatedToId() + ", '" + member.getRelationType() + "', '" + member.getMotherParish() + "', '" + member.getJoinedDate() + "', '" + member.getBirthDay() + "', '" + member.getWeddingDay() + "', '" + member.isHeadOfFamily() + "')";
        executeQuery(query);
    }

    @Override
    public void update(Member member) {
        Optional<Member> memberFromDb = get(member.getId());
        if(memberFromDb.isPresent()) {
            try {
                memberFromDb.get().merge(member);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Member member) {
        execute("DELETE FROM MEMBERS WHERE ID = " + member.getId());
    }

    private List<Member> getMembersFromDb() {
        List<Member> members = new ArrayList<>();
        try {
            ResultSet results = executeQuery("SELECT * FROM MEMBERS");
            while (results.next()) {
                members.add(getMember(results));
            }
        }
        catch(Exception e) {
            System.out.println("Exception while getting members from DB");
        }

        return members;
    }
    
    private Member getMemberFromDb(long id) {
        Member member = null;
        try {
            ResultSet results = executeQuery("SELECT * FROM MEMBERS WHERE ID = " + id);
            while (results.next()) {
                member = getMember(results);
            }
        }
        catch(Exception e) {
            System.out.println("Exception while getting members from DB");
        }

        return member;
    }

    private Member getMember(ResultSet result) {
        try {
            Member member = new Member();
            member.setId(result.getLong(1));
            member.setName(result.getString(2));
            member.setSex(Enum.valueOf(Sex.class, result.getString(3)));
            member.setPhone(result.getString(4));
            member.setEmail(result.getString(5));
            member.setAddress(result.getString(6));
            member.setMemberType(Enum.valueOf(MembershipType.class, result.getString(7)));
            member.setRelatedToId(result.getLong(8));
            member.setRelationType(Enum.valueOf(RelationType.class, result.getString(9)));
            member.setMotherParish(result.getString(10));
            member.setJoinedDate(result.getDate(11));
            member.setBirthDay(result.getDate(12));
            member.setWeddingDay(result.getDate(13));
            member.setHeadOfFamily(result.getBoolean(14));

            return member;
        }
        catch (Exception e) {
            System.out.println("Exception while getting members from DB");
        }

        return null;
    }
}
