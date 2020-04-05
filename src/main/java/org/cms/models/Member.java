package org.cms.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Member {
    private long id;
    private String name;
    private Sex sex;
    private String phone;
    private String email;
    private String address;
    private MembershipType memberType;
    private long relatedToId;
    private RelationType relationType;
    private String motherParish;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
    private Date joinedDate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
    private Date birthDay;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
    private Date weddingDay;
    private Boolean isHeadOfFamily;

    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy");
    
    public void merge(Member updatedMember) throws ParseException {
        this.setName(updatedMember.getName() != null ? updatedMember.getName() : this.getName() );
        this.setAddress(updatedMember.getAddress() != null ? updatedMember.getAddress() : this.getAddress());
        this.setBirthDay(updatedMember.getBirthDay() != null ? format.parse(updatedMember.getBirthDay()) : format.parse(this.getBirthDay()));
        this.setEmail(updatedMember.getEmail() != null ? updatedMember.getEmail() : this.getEmail());
        this.setHeadOfFamily(updatedMember.isHeadOfFamily() != null ? updatedMember.isHeadOfFamily() : this.isHeadOfFamily());
        this.setJoinedDate(updatedMember.getJoinedDate() != null ? format.parse(updatedMember.getJoinedDate()) : format.parse(this.getJoinedDate()));
        this.setMemberType(updatedMember.getMemberType() != null ? updatedMember.getMemberType() : this.getMemberType());
        this.setMotherParish(updatedMember.getMotherParish() != null ? updatedMember.getMotherParish() : this.getMotherParish());
        this.setPhone(updatedMember.getPhone() != null ? updatedMember.getPhone() : this.getPhone());
        this.setRelatedToId(updatedMember.getRelatedToId() > 0 ? updatedMember.getRelatedToId() : this.getRelatedToId());
        this.setRelationType(updatedMember.getRelationType() != null ? updatedMember.getRelationType() : this.getRelationType());
        this.setSex(updatedMember.getSex() != null ? updatedMember.getSex() : this.getSex());
        this.setWeddingDay(updatedMember.getWeddingDay() != null ? format.parse(updatedMember.getWeddingDay()) : format.parse(this.getWeddingDay()));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MembershipType getMemberType() {
        return memberType;
    }

    public void setMemberType(MembershipType memberType) {
        this.memberType = memberType;
    }

    public long getRelatedToId() {
        return relatedToId;
    }

    public void setRelatedToId(long relatedToId) {
        this.relatedToId = relatedToId;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public String getMotherParish() {
        return motherParish;
    }

    public void setMotherParish(String motherParish) {
        this.motherParish = motherParish;
    }

    public String getJoinedDate() {
        return joinedDate != null ? format.format(joinedDate) : null;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getBirthDay() {
        return birthDay != null ? format.format(birthDay) : null;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getWeddingDay() {
        return weddingDay != null ? format.format(weddingDay) : null;
    }

    public void setWeddingDay(Date weddingDay) {
        this.weddingDay = weddingDay;
    }

    public Boolean isHeadOfFamily() {
        return isHeadOfFamily;
    }

    public void setHeadOfFamily(Boolean headOfFamily) {
        isHeadOfFamily = headOfFamily;
    }
}
