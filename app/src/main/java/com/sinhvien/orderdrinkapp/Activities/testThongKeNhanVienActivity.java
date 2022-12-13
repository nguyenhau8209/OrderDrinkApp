package com.sinhvien.orderdrinkapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.GregorianCalendar;
import java.util.List;

public class testThongKeNhanVienActivity extends AppCompatActivity {
    ListView lvThongKe;
    TextView tuNgay, denNgay;
    TextView btnSearch, tvTongTien;
    List<DonDatDTO> donDatDTOList;
    DonDatDAO donDatDAO;
    AdapterDisplayStatistic adapterThongKe;
    FragmentManager fragmentManager;
    NhanVienDAO nhanVienDAO;
    List<NhanVienDTO> listNhanVien;
    NhanVienSpinnerAdapter nvspAdapter;
    Context context;
    NhanVienDTO nhanVienDTO;
    int madon,manv, maban;
    int tongtien;
    String ngaydat, thang;
    int manv1=1;
    SimpleDateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
    int mYear , mMonth , mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thong_ke_nhan_vien);
        Intent intent = getIntent();
        String tendn = intent.getStringExtra("tendn1");
        Log.d("zzzz", "testnv: tendn: "+tendn);
        nhanVienDAO = new NhanVienDAO(testThongKeNhanVienActivity.this);
        manv = nhanVienDAO.Laymadangnhap(intent.getStringExtra("tendn1"));
        Log.d("zzzz", "onCreate: manv: "+ manv);
        Log.d("zzzz", "manv: "+nhanVienDTO);
//        getSupportActionBar().setTitle("Quản lý thống kê!");
        lvThongKe = findViewById(R.id.lvThongKe1);
        tuNgay =findViewById(R.id.edDate_ThongKe2);
        denNgay = findViewById(R.id.edDate_ThongKe3);
        btnSearch = findViewById(R.id.btnSreach2);
        tvTongTien = findViewById(R.id.tvTongTien);
//        tongtien =0;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        tuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(testThongKeNhanVienActivity.this , 0 , mDateTuNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        denNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth= calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(testThongKeNhanVienActivity.this , 0 , mDateDenNgay  , mYear , mMonth , mDay);
                dialog.show();
            }
        });
        nhanVienDTO = new NhanVienDTO();
        nhanVienDTO.getMANV();
        Log.d("zzzz", "Get manv: "+ nhanVienDTO);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tongtien = 0;
                donDatDTOList = new ArrayList<>();
                if (denNgay.getText().equals("")||tuNgay.getText().equals("")|| manv <=0){
                    Toast.makeText(testThongKeNhanVienActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    donDatDAO = new DonDatDAO(testThongKeNhanVienActivity.this);
                    donDatDTOList = donDatDAO.LayDSDonDatTenDN(manv, tuNgay.getText().toString(), denNgay.getText().toString());
                    adapterThongKe = new AdapterDisplayStatistic(testThongKeNhanVienActivity.this, R.layout.custom_layout_displaystatistic, donDatDTOList);
                    lvThongKe.setAdapter(adapterThongKe);
//

                    //tổng doanh thu
                    for (int i=0; i<donDatDTOList.size();i++){
                        tongtien = tongtien+ Integer.parseInt(donDatDTOList.get(i).getTongTien());
                        Log.d("zzzz", "onClick: Tổng tiền+ "+ tongtien);
                    }
                    tvTongTien.setText("Tổng tiền: "+ tongtien);
                    Log.d("zzzz", "onClick: Tổng tiền 2"+tvTongTien);

                }
            }

        });

    }
    DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            tuNgay.setText(date1.format(calendar.getTime()));

        }
    };
    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            mYear = i;
            mMonth = i1;
            mDay = i2;
            GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth , mDay);
            denNgay.setText(date1.format(calendar.getTime()));

        }
    };
}