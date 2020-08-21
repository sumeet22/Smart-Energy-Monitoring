package com.example.smartenergymonitoring.models;

public class SubjectData {
    public String SubjectName;
    public String Link;
    public int Image;
    public String consumtion;


    public SubjectData(String subjectName, String link, int image, String consumtion) {
        SubjectName = subjectName;
        Link = link;
        Image = image;
        this.consumtion = consumtion;
    }

}
