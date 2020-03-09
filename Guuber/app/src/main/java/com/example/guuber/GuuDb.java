package com.example.guuber;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;


///TODO:
//    -creating a collection of request history and being adding to it
//    -other update methods most likely
//    -creating a collection of ratings and able to add to that (maybe)
//    -an average rating of the user (maybe)


public class GuuDb {
    private FirebaseFirestore db;

    //Root is the Users collection
    private CollectionReference root;
    // doc to be used to get user information
    private DocumentReference doc;

    public GuuDb(){
        db = FirebaseFirestore.getInstance();
        root = db.collection("Users");

    }

    /**
     * find the doc of user and uses it as a reference
     * @param username - the username to find in the database
     */

    public void findUser(final String username){
        doc = root.document(username);
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Log.d(username,"Document exist");
                }
                else{
                    Log.d(username, "Document does not exist");
                }
            }
        });
    }
    /**
     * gets user's information
    * --------NOTE: That the user/Rider/Driver class constructor must have no aruguments---------------------
    *-------- Nebye can you try and see if there is another way to do this?-------------------------------
     */
    public User getUserInfo(){
        final User[] user = new User[1];
        doc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    user[0] = documentSnapshot.toObject(User.class);
                }
                else{

                }
            }
        });
        return user[0];
    }
    /**
     * Creates and add the minimal info of the user into the database
     * @param username - the document id, as it has to be unique like a username
     * @param first - first name of the user
     * @param last - Last name of the user
     * @param email - the user's email
     * */
    public void setUpUser(String username, String first,String last,String email,String phone){
        Map<String,Object> user = new HashMap<>();
        user.put("first",first);
        user.put("last",last);
        user.put("email",email);
        user.put("phone",phone);

        root.document(username).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Document", "DocumentSnapshot successfully written");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Document","Error writing document");
            }
        });
    }
    /**
     * Adds the balance field if it doesn't exist or updates it in the database
     * @param balance - amount to put in Database
     * */
    public void addWallet(double balance){
        doc.update("balance", balance).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("Balance","Documentsnapshot updated");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Balance"," Error updating document",e);
            }
        });
    }


    /**
     * Adds the registered car into the database
     * @param make - the maker of the car
     * @param model - the car model
     * @param colour - the colour of the car
     * */
    public void regCar(String make,String model, String colour){
        Map<String,Object> car = new HashMap<>();
        car.put("make",make);
        car.put("model", model);
        car.put("colour", colour);
        doc.collection("car").document("userCar").set(car).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("carDoc","successfully created");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("carDoc","Error writing document");
            }
        });
    }
    /**
     * updates the registered car into the database
     * @param make - the maker of the car
     * @param model - the car model
     * @param colour - the colour of the car
     * */
    public void updateCar(String make,String model, String colour){
        Map<String,Object> car = new HashMap<>();
        car.put("make",make);
        car.put("model", model);
        car.put("colour", colour);
        doc.collection("car").document("userCar").update(car).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("updateCar","Car updated");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("updateCar","Update failed");
            }
        });
    }




}

