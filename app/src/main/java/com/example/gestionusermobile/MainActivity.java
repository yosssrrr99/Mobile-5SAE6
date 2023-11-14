package com.example.gestionusermobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionusermobile.Entity.User;
import com.example.gestionusermobile.Entity.UserDao;
import com.example.gestionusermobile.Entity.UserDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword,adress;
    Button signup, signin;
    RecyclerView recycler;
    DBHelper DB;
    private UserDao userDao;
    private UserDatabase userDatabase;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDatabase=UserDatabase.getInstance(this);
        userDao=UserDatabase.INSTANCE.getDao();

        username = (EditText) findViewById(R.id.username);
        adress=(EditText) findViewById(R.id.adress) ;
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        recycler=findViewById(R.id.userRecycler);
        DB = new DBHelper(this);


        userAdapter = new UserAdapter(this);
        recycler.setAdapter(userAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String adresse = adress.getText().toString();

                if(username.equals("")||pass.equals("")|| repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(userName);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(userName, pass);
                            if(insert==true){
                                User user=new User(0,userName,adresse,pass) ;
                                userAdapter.addUser(user);
                                userDao.insert(user);

                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                //startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void fetchData(){
        List<User> userList=userDao.getAllUsers();
        for (int i=0;i<userList.size();i++){
            User user=userList.get(i);
            userAdapter.addUser(user);
        }
    }
}