package com.example.guuber;

//import static org.junit.Assert.*;
import android.content.Context;

import com.google.firebase.FirebaseApp;

import org.junit.Test;

public class GuuDbTests {

    private GuuDb mockDb(){
        FirebaseApp.initializeApp(this);
        return new GuuDb();
    }

    @Test
    public void TestUserSetup() {
        GuuDb gdb = mockDb();
        gdb.setUpUser("123@123.ca","Luke","Atme","LukeAtme","780 780 7807","rider");
        gdb.findUser("123@123.ca");

    }

}
