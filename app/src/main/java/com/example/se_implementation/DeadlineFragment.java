package com.example.se_implementation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeadlineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeadlineFragment extends Fragment {

    public interface FragmentDeadlineListener {
        void onInputDeadlineSent(CharSequence input);

    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeadlineFragment() {
        // Required empty public constructor
    }

    public static DeadlineFragment newInstance(String param1, String param2) {
        DeadlineFragment fragment = new DeadlineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deadline, container, false);


        return v;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_deadline, container, false);
    }

}