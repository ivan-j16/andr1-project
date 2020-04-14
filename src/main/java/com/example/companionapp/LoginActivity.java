package com.example.companionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.companionapp.Fragments.TokenFragment;

public class LoginActivity extends AppCompatActivity implements TokenFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!android.webkit.CookieManager.getInstance().hasCookies()) {
            FragmentManager fragMgr = getSupportFragmentManager();
            FragmentTransaction fragTrans = fragMgr.beginTransaction();
            TokenFragment tokenFragment = new TokenFragment();
            fragTrans.add(R.id.token_fragment, tokenFragment, "Token fragment");
            fragTrans.commit();
        }
    }

    @Override
    public void onFragmentInteraction(String token) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
    }
}
