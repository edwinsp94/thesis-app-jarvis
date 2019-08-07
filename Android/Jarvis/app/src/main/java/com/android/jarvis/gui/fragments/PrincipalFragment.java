package com.android.jarvis.gui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.jarvis.R;
import com.squareup.picasso.Picasso;

public class PrincipalFragment extends Fragment {

    public PrincipalFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_principal, container, false);
        ImageView imageView = view.findViewById(R.id.FragmentPrincipal_imageView_fondo_pantalla);
        Picasso.get().load(R.drawable.fondo_pantalla).fit().into(imageView);

        return view;
    }

}
