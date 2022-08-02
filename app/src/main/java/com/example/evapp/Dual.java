package com.example.evapp;

public class Dual {
    private String Candidate_ID;
    private String Constituency;
    private String Name;


    public Dual() {
    }

    public Dual(String candidate_ID, String constituency, String name) {
        Candidate_ID = candidate_ID;
        Constituency = constituency;
        this.Name = name;
    }

    public String getCandidate_ID() {
        return Candidate_ID;
    }

    public void setCandidate_ID(String candidate_ID) {
        Candidate_ID = candidate_ID;
    }

    public String getConstituency() {
        return Constituency;
    }

    public void setConstituency(String constituency) {
        Constituency = constituency;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
