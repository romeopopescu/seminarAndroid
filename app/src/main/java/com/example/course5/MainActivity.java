package com.example.course5;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void switchFragment(View view) {
        if (view.getId() == R.id.button) {
            replaceFragment(new FirstFragment());
        }
        else if (view.getId() == R.id.button2){
            replaceFragment(new SecondFragment());
        }
    }


    private void replaceFragment(FirstFragment firstFragment) {
        Bundle bundle = new Bundle();
        bundle.putString("key", "Hello from MainActivity!");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = null;
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment, bundle);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commit();
    }
}