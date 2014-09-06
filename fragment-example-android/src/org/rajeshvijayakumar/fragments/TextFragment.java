package org.rajeshvijayakumar.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class TextFragment extends Fragment {
    TextView text,vers;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment, container, false);
        text= (TextView) view.findViewById(R.id.name_text_view);
        Bundle bundle = getArguments();
        String name = bundle.getString("names");
        text.setText(name);
        		
        return view;
    }
}