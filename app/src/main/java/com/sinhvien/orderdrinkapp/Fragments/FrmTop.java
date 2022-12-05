package com.sinhvien.orderdrinkapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;


import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterTop;
import com.sinhvien.orderdrinkapp.DAO.DonDatDAO;
import com.sinhvien.orderdrinkapp.DTO.top;
import com.sinhvien.orderdrinkapp.R;

import java.util.ArrayList;


public class FrmTop extends Fragment {

    ListView listView;
    ArrayList<top> listTop;
    AdapterTop adapterTop;

    public FrmTop() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_frm_top, container, false);
        listView = view.findViewById(R.id.lvTop);
        DonDatDAO donDatDAO = new DonDatDAO(getActivity());
        listTop = (ArrayList<top>)donDatDAO.getTop();
        adapterTop = new AdapterTop(getActivity() , this , listTop);
        listView.setAdapter(adapterTop);
        return view;
    }
}