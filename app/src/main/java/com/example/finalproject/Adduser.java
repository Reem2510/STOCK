package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Adduser#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Adduser extends Fragment {
    private EditText Address,Phone,editUsername,Fullname;
    private Button Addbus ;
    private FirebaseFirestore db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Adduser() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Adduser.
     */
    // TODO: Rename and change types and number of parameters
    public static Adduser newInstance(String param1, String param2) {
        Adduser fragment = new Adduser();
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
    private void attachComponents(){
        Fullname=getActivity().findViewById(R.id.Fullname);
        editUsername=getActivity().findViewById(R.id.editUsername);
        Phone=getActivity().findViewById(R.id.Phone);
        Address=getActivity().findViewById(R.id.Address);
        Addbus=getActivity().findViewById(R.id.Addbus);
        db = FirebaseFirestore.getInstance();
        Addbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddtoFireBseStore();
            }
        });
    }
    private void AddtoFireBseStore() {
        String fullname,Username,userPhone,userAddress;
        fullname=Fullname.getText().toString();
        Username=editUsername.getText().toString();
        userPhone=Phone.getText().toString();
        userAddress=Address.getText().toString();
        if (fullname.trim().isEmpty()||Username.trim().isEmpty()||userAddress.trim().isEmpty()||userPhone.trim().isEmpty())
        {
            Toast.makeText(getActivity(),"some data are missing",Toast.LENGTH_SHORT).show();
            return;
        }
       UserClass u=new UserClass(fullname,userAddress,userPhone,Username);
        Map<String, Object> docData = new HashMap<>();
        docData.put("Fullname", fullname);
        docData.put("Username", Username);
        docData.put("Address", userAddress);
        docData.put("Phone", userPhone);
        DocumentReference future = db.collection("users").document(Username);
        future.set(docData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        attachComponents();
        return inflater.inflate(R.layout.fragment_adduser, container, false);
    }
}