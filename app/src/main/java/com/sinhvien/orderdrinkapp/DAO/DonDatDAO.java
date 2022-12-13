package com.sinhvien.orderdrinkapp.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import com.sinhvien.orderdrinkapp.DTO.ChiTietDonDatDTO;
import com.sinhvien.orderdrinkapp.DTO.DonDatDTO;
import com.sinhvien.orderdrinkapp.DTO.LoaiMonDTO;
import com.sinhvien.orderdrinkapp.DTO.MonDTO;
import com.sinhvien.orderdrinkapp.DTO.top;
import com.sinhvien.orderdrinkapp.Database.CreateDatabase;

import java.util.ArrayList;
import java.util.List;

public class DonDatDAO {
    Context context;
    SQLiteDatabase database;
    public DonDatDAO(Context context){
        this.context = context;
        CreateDatabase createDatabase = new CreateDatabase(context);
        database = createDatabase.open();
    }

    public long ThemDonDat(DonDatDTO donDatDTO){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_DONDAT_MABAN,donDatDTO.getMaBan());
        contentValues.put(CreateDatabase.TBL_DONDAT_MANV,donDatDTO.getMaNV());
        contentValues.put(CreateDatabase.TBL_DONDAT_NGAYDAT,donDatDTO.getNgayDat());
        contentValues.put(CreateDatabase.TBL_DONDAT_TINHTRANG,donDatDTO.getTinhTrang());
        contentValues.put(CreateDatabase.TBL_DONDAT_TONGTIEN,donDatDTO.getTongTien());

        long madondat = database.insert(CreateDatabase.TBL_DONDAT,null,contentValues);

        return madondat;
    }

    public List<DonDatDTO> LayDSDonDat(){
        List<DonDatDTO> donDatDTOS = new ArrayList<DonDatDTO>();
        String query = "SELECT * FROM "+CreateDatabase.TBL_DONDAT;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMaDonDat(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MADONDAT)));
            donDatDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MABAN)));
            donDatDTO.setTongTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TONGTIEN)));
            donDatDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TINHTRANG)));
            donDatDTO.setNgayDat(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_NGAYDAT)));
            donDatDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MANV)));
            donDatDTOS.add(donDatDTO);

            cursor.moveToNext();
        }
        return donDatDTOS;
    }

    public List<DonDatDTO> LayDSDonDatNgay(String ngaythang){
        List<DonDatDTO> donDatDTOS = new ArrayList<DonDatDTO>();
        String query = "SELECT * FROM "+CreateDatabase.TBL_DONDAT+" WHERE "+CreateDatabase.TBL_DONDAT_NGAYDAT+" like '"+ngaythang+"'";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMaDonDat(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MADONDAT)));
            donDatDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MABAN)));
            donDatDTO.setTongTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TONGTIEN)));
            donDatDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TINHTRANG)));
            donDatDTO.setNgayDat(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_NGAYDAT)));
            donDatDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MANV)));
            donDatDTOS.add(donDatDTO);

            cursor.moveToNext();
        }
        return donDatDTOS;
    }

    public List<DonDatDTO> LayDSDonDatMaNV(int manv, String thang){
        List<DonDatDTO> donDatDTOS = new ArrayList<DonDatDTO>();
        String query = "SELECT * FROM DONDAT WHERE MANV ="+ manv +" AND "+CreateDatabase.TBL_DONDAT_NGAYDAT+" like '"+thang+"'";
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMaDonDat(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MADONDAT)));
            donDatDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MABAN)));
            donDatDTO.setTongTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TONGTIEN)));
            donDatDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TINHTRANG)));
            donDatDTO.setNgayDat(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_NGAYDAT)));
            donDatDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MANV)));
            donDatDTOS.add(donDatDTO);

            cursor.moveToNext();
        }
        return donDatDTOS;
    }
    public List<DonDatDTO> LayDSDonDatTenDN(int manv, String min, String max){
        List<DonDatDTO> donDatDTOS = new ArrayList<DonDatDTO>();
        String query = "SELECT * FROM DONDAT WHERE MANV ="+ manv +" AND "+CreateDatabase.TBL_DONDAT_NGAYDAT+" between ? and ?";
        Cursor cursor = database.rawQuery(query,new String[]{min, max});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            DonDatDTO donDatDTO = new DonDatDTO();
            donDatDTO.setMaDonDat(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MADONDAT)));
            donDatDTO.setMaBan(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MABAN)));
            donDatDTO.setTongTien(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TONGTIEN)));
            donDatDTO.setTinhTrang(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TINHTRANG)));
            donDatDTO.setNgayDat(cursor.getString(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_NGAYDAT)));
            donDatDTO.setMaNV(cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MANV)));
            donDatDTOS.add(donDatDTO);

            cursor.moveToNext();
        }
        return donDatDTOS;
    }

    public long LayMaDonTheoMaBan(int maban, String tinhtrang){
        String query = "SELECT * FROM " +CreateDatabase.TBL_DONDAT+ " WHERE " +CreateDatabase.TBL_DONDAT_MABAN+ " = '" +maban+ "' AND "
                +CreateDatabase.TBL_DONDAT_TINHTRANG+ " = '" +tinhtrang+ "' ";
        long magoimon = 0;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            magoimon = cursor.getInt(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_MADONDAT));

            cursor.moveToNext();
        }
        return magoimon;
    }

    public boolean UpdateTongTienDonDat(int madondat,String tongtien){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_DONDAT_TONGTIEN,tongtien);
        long ktra  = database.update(CreateDatabase.TBL_DONDAT,contentValues,
                CreateDatabase.TBL_DONDAT_MADONDAT+" = "+madondat,null);
        if(ktra != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean UpdateTThaiDonTheoMaBan(int maban,String tinhtrang){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CreateDatabase.TBL_DONDAT_TINHTRANG,tinhtrang);
        long ktra = database.update(CreateDatabase.TBL_DONDAT,contentValues,CreateDatabase.TBL_DONDAT_MABAN+
                " = '"+maban+"'",null);
        if(ktra !=0){
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public long getDoanhThu1(int manv, String thang) {
        String sqlDoanhThu = "Select SUM(TONGTIEN) as doanhThu from DONDAT WHERE MANV = "+ manv +" AND "+CreateDatabase.TBL_DONDAT_NGAYDAT+" like '"+thang+"'";;
        long tongtien = 0;
        Cursor cursor = database.rawQuery(sqlDoanhThu,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tongtien = cursor.getLong(cursor.getColumnIndex(CreateDatabase.TBL_DONDAT_TONGTIEN));
            cursor.moveToNext();
        }
        return tongtien;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String min, String max) {
        String sqlDoanhThu = "Select Sum("+  CreateDatabase.TBL_DONDAT_TONGTIEN  +") as doanhThu from "+CreateDatabase.TBL_DONDAT +" Where "+CreateDatabase.TBL_DONDAT_NGAYDAT+" between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sqlDoanhThu, new String[]{min, max});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }

    //thống kê top 10
    @SuppressLint("Range")
    public List<top> getTop() {
        String sqlTop = "Select MAMON ,count(MAMON) as soLuong From "+CreateDatabase.TBL_CHITIETDONDAT+" Group By MAMON Order By soLuong DESC Limit 10";
        List<top> list = new ArrayList<>();
        MonDAO monDAO = new MonDAO(context);
        Cursor cursor = database.rawQuery(sqlTop, null);
        while (cursor.moveToNext()) {
            top top = new top();
            MonDTO mon = monDAO.LayMonTheoMa(Integer.parseInt(cursor.getString(cursor.getColumnIndex("MAMON"))));
            top.setTenMon(mon.getTenMon());
            top.setSoLuongMon(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soLuong"))));
            list.add(top);
        }
        return list;
    }

}
