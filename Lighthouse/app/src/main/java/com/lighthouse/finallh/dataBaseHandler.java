package com.lighthouse.finallh;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class dataBaseHandler {
    Firebase root = new Firebase("https://appjamlh.firebaseio.com/Events");
    long idCounts;
    List<Long> events = new ArrayList<Long>()  ;
    public List<Long> IDs = new ArrayList<Long>();

    public dataBaseHandler(){}


public void addEvent(String eventName , String startDate , String endDate
                    ,String startTime , String endTime ,  String location ,
                     String[] tags, String comment) {

    final long newid ;
    root.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            // do some stuff once
            idCounts = snapshot.getChildrenCount();

            System.out.println("single value count datachange" + idCounts);
            System.out.println("get value " + snapshot.child("id1").child("name").getValue());
        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
        }
    });

    root.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            //     while (snapshot.hasChild("id" + Long.toString(idCounts))) {
            //       idCounts++;
            // }
            idCounts = snapshot.getChildrenCount();

            System.out.println(snapshot.getChildrenCount() + " Value Event datachange !!!!!!!!!!!!!!!!!!");

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
            System.out.println("The read failed: " + firebaseError.getMessage());
        }
    });
    root.addChildEventListener(new ChildEventListener() {
        // Retrieve new posts as they are added to the database
        @Override
        public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {

            System.out.println(snapshot.getChildrenCount() + " child Event aded !!!!!!!!!!!!!!!!!!");
            //System.out.println(snapshot.getValue() + " child even");
            idCounts = snapshot.getChildrenCount();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            idCounts = dataSnapshot.getChildrenCount();
            System.out.println("on child removed " + idCounts);
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }

    });



//TODO user randomly selected
    Random rand = new Random();
    long id = rand.nextLong();
    String eventID=  Long.toString( id );
    root.child(eventID).child("name").setValue(eventName);
    root.child(eventID).child("date").child("startDate").setValue(startDate);
    root.child(eventID).child("date").child("endDate").setValue(endDate);
    root.child(eventID).child("time").child("startTime").setValue(startTime);
    root.child(eventID).child("time").child("endTime").setValue(endTime);

    root.child(eventID).child("location").setValue(location);
    root.child(eventID).child("Comment").setValue(comment);
for(int i = 0 ; i< tags.length ; i++) root.child(eventID).child("tags").child(tags[i]).
        setValue(eventID);



}// end of add Event method




} // end of DB handler class
