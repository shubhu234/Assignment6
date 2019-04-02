//UserDetails class to get the name and id of the user
package com.example.studentmanagement.model;

import java.io.Serializable;

public class UserDetails implements Serializable {


    private  String id;
    private   String  name;

    /**
    Constructor of student class
    @param name-name of student
    @param id- id of the student
     */
    public UserDetails(String name, String id)
    {
        this.name=name;
        this.id=id;
    }
    //method getmName-get the name of student
    //@return name of student
    public   String getmName()
    {
        return  name;
    }

    public   String getId()
    {
        return  id;
    }


}
