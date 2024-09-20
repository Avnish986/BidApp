package com.test2.app;

import com.test2.event.Event;
import com.test2.user.User;

import java.util.*;

public class BidBlitz {

    private Map<Integer, User> members = new HashMap<>();
    private Map<Integer, Event> events = new HashMap<>();
    private List<String> winnersLog = new ArrayList<>();
    private int eventCounter = 1;

    public String addMember(int id, String name, int superCoins) {
        if (superCoins <= 0) {
            return "Super coins should be greater than zero.";
        }
        members.put(id, new User(id, name, superCoins));
        return name + " added successfully";
    }

    public String addEvent(String eventName, String prizeName) {
        if (events.values().stream().anyMatch(event -> event.getName().equals(eventName))) {
            return "Event name must be unique.";
        }
        if (events.size() >= 1) {
            return "Only one event can be added in a single day.";
        }
        Event newEvent = new Event(eventCounter++, eventName, prizeName);
        events.put(newEvent.getId(), newEvent);
        return eventName + " with prize " + prizeName + " added successfully";
    }

    public String registerMember(int memberId, int eventId) {
        Event event = events.get(eventId);
        User member = members.get(memberId);

        if (event == null) {
            return "Event does not exist.";
        }
        if (member == null) {
            return "Member does not exist.";
        }
        if (event.getRegisteredMembers().contains(memberId)) {
            return member.name + " is already registered for this event.";
        }

        event.getRegisteredMembers().add(memberId);
        return member.name + " registered to the " + event.getName() + " event successfully";
    }

    public String submitBid(int memberId, int eventId, int[] bids) {
        Event event = events.get(eventId);
        User member = members.get(memberId);

        if (event == null) {
            return "Event does not exist.";
        }
        if (member == null) {
            return "Member does not exist.";
        }
        if (!event.getRegisteredMembers().contains(memberId)) {
            return "Member did not register for this event.";
        }
        if (bids.length > 5) {
            return "Maximum 5 bids can be submitted.";
        }

        Set<Integer> uniqueBids = new HashSet<>();
        int maxBid = 0;

        for (int bid : bids) {
            if (bid <= 0) {
                return "Each bid must be greater than zero.";
            }
            if (!uniqueBids.add(bid)) {
                return "Bids must be unique.";
            }
            maxBid = Math.max(maxBid, bid);
        }

        if (member.getCoins() < maxBid) {
            return "Not enough super coins to submit the highest bid.";
        }

        member.coins -= maxBid;
        Integer[] bidObjects = Arrays.stream(bids).boxed().toArray(Integer[]::new);
        event.getBids().addAll(List.of(bidObjects));
        event.getMemberBids().put(memberId, Arrays.stream(bids).min().getAsInt());
        return "BIDS submitted successfully";
    }

    public String declareWinner(int eventId) {
        Event event = events.get(eventId);
        if (event == null) {
            return "Event does not exist.";
        }
        if (event.getMemberBids().isEmpty()) {
            return "No bids submitted for this event.";
        }

        // Find lowest bid
        int lowestBid = Integer.MAX_VALUE;
        int winnerId = -1;
        boolean isTie = false;

        for (Map.Entry<Integer, Integer> entry : event.getMemberBids().entrySet()) {
            int memberId = entry.getKey();
            int bid = entry.getValue();

            if (bid < lowestBid) {
                lowestBid = bid;
                winnerId = memberId;
                isTie = false;
            } else if (bid == lowestBid) {
                isTie = true; // tie detected
            }
        }

        String winnerName = members.get(winnerId).name;
        String result = winnerName + " wins the " + event.getPrize() + " with lowest bid " + lowestBid;

        // Log winner
        winnersLog.add("Event ID: " + eventId + ", Winner: " + winnerName + ", Bid: " + lowestBid + ", Date: " + new Date());
        return result;
    }

    public List<String> listWinners(String orderBy) {
        int limit = Math.min(winnersLog.size(), 5);
        List<String> sortedWinners = new ArrayList<>(winnersLog);

        if ("asc".equalsIgnoreCase(orderBy)) {
            Collections.sort(sortedWinners);
        } else {
            Collections.sort(sortedWinners, Collections.reverseOrder());
        }

        return sortedWinners.subList(0, limit);
    }

}
