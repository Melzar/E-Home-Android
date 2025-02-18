package com.ecode.ehome.model;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

/**
 * Created by matuszewski on 05/05/16.
 */
@Type("accomodation")
public class Accomodation {

    @Id
    private String id;
    private String name;

//    @Relationship("accomodation-type")
//    private AccomodationType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public AccomodationType getType() {
//        return type;
//    }
//
//    public void setType(AccomodationType type) {
//        this.type = type;
//    }
}
