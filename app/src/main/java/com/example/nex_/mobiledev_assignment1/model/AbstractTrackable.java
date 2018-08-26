package com.example.nex_.mobiledev_assignment1.model;

import java.net.URL;

public abstract class AbstractTrackable implements InterfaceTrackable {
    int ID;
    String name;
    String TackableDes;
    URL URL;
    CategoryList category;

    public void parseData(){};
    public void getData(){};


}
