package com.ecode.ehome.model;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

/**
 * Created by matuszewski on 05/05/16.
 */
@Type("accomodation")
public class Accomodation {

    @Id
    private String id;
}
