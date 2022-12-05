package com.sinhvien.orderdrinkapp.Fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sinhvien.orderdrinkapp.DAO.DonDatDAO;
import com.sinhvien.orderdrinkapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DisplayTurnoverFragment extends Fragment {
// doanh thu
Button btnTuNgay , btnDenNgay , btnDoanhThu;
    EditText edTuNgay , edDenNgay;
    TextView tvDoanhThu;
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
    int mYear , mMonth , mDay;
    public DisplayTurnoverFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_display_turnover, container, false);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity() , 0 , mDateTuNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getActivity() , 0 , mDateDenNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                DonDatDAO phieuMuonDAO = new DonDatDAO(getContext());
                tvDoanhThu.setText("Doanh Thu: "+phieuMuonDAO.getDoanhThu(tuNgay , denNgay));
            }
        });
        return view;
    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            edTuNgay.setText(date1.format(calendar.getTime()));

        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            edDenNgay.setText(date1.format(calendar.getTime()));

        }
    };
}