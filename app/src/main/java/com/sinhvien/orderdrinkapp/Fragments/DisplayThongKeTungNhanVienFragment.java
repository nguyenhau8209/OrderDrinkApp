package com.sinhvien.orderdrinkapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinhvien.orderdrinkapp.R;


public class DisplayThongKeTungNhanVienFragment extends Fragment {


    public DisplayThongKeTungNhanVienFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_thong_ke_tung_nhan_vien, container, false);
        Intent intent = getActivity().getIntent();
        String manvString = intent.getStringExtra("manv");
        Log.d("zzzz", "onCreateView: manv: "+manvString);
        return view;
    }
}