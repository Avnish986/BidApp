package com.test2.event;

import java.util.*;

public class Event {

    int id;
    String name;
    String prize;
    Set<Integer> registeredMembers = new HashSet<>();
    List<Integer> bids = new ArrayList<>();
    Map<Integer, Integer> memberBids = new HashMap<>(); // memberId -> bid

    public Event(int id, String name, String prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public Set<Integer> getRegisteredMembers() {
        return registeredMembers;
    }

    public void setRegisteredMembers(Set<Integer> registeredMembers) {
        this.registeredMembers = registeredMembers;
    }

    public List<Integer> getBids() {
        return bids;
    }

    public void setBids(List<Integer> bids) {
        this.bids = bids;
    }

    public Map<Integer, Integer> getMemberBids() {
        return memberBids;
    }

    public void setMemberBids(Map<Integer, Integer> memberBids) {
        this.memberBids = memberBids;
    }
}
