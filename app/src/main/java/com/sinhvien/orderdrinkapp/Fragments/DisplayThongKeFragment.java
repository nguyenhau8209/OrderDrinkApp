package com.sinhvien.orderdrinkapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sinhvien.orderdrinkapp.Activities.HomeActivity;
import com.sinhvien.orderdrinkapp.CustomAdapter.AdapterDisplayStatistic;
import com.sinhvien.orderdrinkapp.CustomAdapter.NhanVienSpinnerAdapter;
import com.sinhvien.orderdrinkapp.DAO.DonDatDAO;
import com.sinhvien.orderdrinkapp.DAO.NhanVienDAO;
import com.sinhvien.orderdrinkapp.DTO.DonDatDTO;
import com.sinhvien.orderdrinkapp.DTO.NhanVienDTO;
import com.sinhvien.orderdrinkapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DisplayThongKeFragment extends Fragment {
Spinner thongkespinner, monthspinner;
ListView lvThongKe;
EditText edDate_ThongKe;
TextView btnSearch, tvTongTien;
List<DonDatDTO> donDatDTOList;
DonDatDAO donDatDAO;
AdapterDisplayStatistic adapterThongKe;
FragmentManager fragmentManager;
NhanVienDAO nhanVienDAO;
List<NhanVienDTO> listNhanVien;
NhanVienSpinnerAdapter nvspAdapter;
Context context;
    int madon,manv, maban;
    long tongtien;
    String ngaydat, thang;
    int manv1=1;

    public DisplayThongKeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display_thong_ke, container, false);
        ((HomeActivity)getActivity()).getSupportActionBar().setTitle("Quản lý thống kê");
        setHasOptionsMenu(true);
        lvThongKe = view.findViewById(R.id.lvThongKe);
        edDate_ThongKe = view.findViewById(R.id.edDate_ThongKe);
        btnSearch = view.findViewById(R.id.btnSreach);
        tvTongTien = view.findViewById(R.id.tvTongTien);
        //sự kiện spinner
        nhanVienDAO = new NhanVienDAO(getActivity());
        thongkespinner = view.findViewById(R.id.thongke_spinner);
        listNhanVien = nhanVienDAO.LayDSNV();
        nvspAdapter = new NhanVienSpinnerAdapter(getActivity(),R.layout.nhanvien_spinner_item, listNhanVien);
        thongkespinner.setAdapter(nvspAdapter);
        thongkespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                manv = listNhanVien.get(position).getMANV();
                Log.d("zzzz", "onItemSelected: manv"+ manv);
                Toast.makeText(getContext(), "Chọn: "+ listNhanVien.get(position).getHOTENNV(), Toast.LENGTH_SHORT).show();
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                thang = dateFormat.format(calendar.getTime());
                edDate_ThongKe.setEnabled(false);
                edDate_ThongKe.setTextColor(Color.BLACK);
                edDate_ThongKe.setText(thang);

                btnSearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edDate_ThongKe.getText().equals("")|| manv <=0){
                            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            donDatDAO = new DonDatDAO(getActivity());
                            donDatDTOList = donDatDAO.LayDSDonDatMaNV(manv, thang);
                            adapterThongKe = new AdapterDisplayStatistic(getActivity(), R.layout.custom_layout_displaystatistic, donDatDTOList);
                            lvThongKe.setAdapter(adapterThongKe);
//                            tongtien = Long.parseLong(String.valueOf(donDatDAO.getDoanhThu(manv, thang)));
//                            tvTongTien.setText("Tổng tiền: "+ tongtien);
                            adapterThongKe.notifyDataSetChanged();

                        }
                    }
                });



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return view;
    }
}