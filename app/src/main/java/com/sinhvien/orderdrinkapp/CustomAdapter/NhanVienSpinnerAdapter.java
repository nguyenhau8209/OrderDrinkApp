package com.sinhvien.orderdrinkapp.CustomAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sinhvien.orderdrinkapp.DAO.BanAnDAO;
import com.sinhvien.orderdrinkapp.DAO.NhanVienDAO;
import com.sinhvien.orderdrinkapp.DTO.DonDatDTO;
import com.sinhvien.orderdrinkapp.DTO.NhanVienDTO;
import com.sinhvien.orderdrinkapp.R;

import java.util.List;

public class NhanVienSpinnerAdapter extends BaseAdapter {

    Context context;
    int layout;
    List<NhanVienDTO> nhanVienDTOS;
    ViewHolder viewHolder;
    NhanVienDAO nhanVienDAO;
    BanAnDAO banAnDAO;

    public NhanVienSpinnerAdapter(Context context, int layout, List<NhanVienDTO> nhanVienDTOS){
        this.context = context;
        this.layout = layout;
        this.nhanVienDTOS = nhanVienDTOS;
//        nhanVienDAO = new NhanVienDAO(context);
//        banAnDAO = new BanAnDAO(context);
    }

    @Override
    public int getCount() {
        return nhanVienDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return nhanVienDTOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return nhanVienDTOS.get(position).getMANV();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,parent,false);

            viewHolder.txt_customstatistic_MaNhanVien = (TextView)view.findViewById(R.id.manv_sp);

            viewHolder.txt_customstatistic_TenNV = (TextView)view.findViewById(R.id.tennv_sp);

            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        NhanVienDTO nhanVienDTO = nhanVienDTOS.get(position);

        viewHolder.txt_customstatistic_MaNhanVien.setText("Mã nhân viên: "+nhanVienDTO.getMANV());

        viewHolder.txt_customstatistic_TenNV.setText(nhanVienDTO.getHOTENNV());

        return view;
    }
    public class ViewHolder{
        TextView txt_customstatistic_MaNhanVien, txt_customstatistic_TenNV
                ;

    }
}
