package com.sinhvien.orderdrinkapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sinhvien.orderdrinkapp.DTO.MonDTO;
import com.sinhvien.orderdrinkapp.Database.CreateDatabase;

import java.util.ArrayList;

public class topDAO {
    public int getDoanhThu(Context context, String ngaybatdau, String ngayketthuc){
        ngaybatdau=ngaybatdau.replace("/","");
        ngayketthuc=ngayketthuc.replace("/","");
        CreateDatabase dbHelper = new CreateDatabase(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(" +CreateDatabase.TBL_DONDAT_TONGTIEN + ") FROM  " +CreateDatabase.TBL_DONDAT +  " WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) BETWEEN ? AND ?", new String[]{ngaybatdau,ngayketthuc});
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }
    public ArrayList<MonDTO> getTop10(Context context){
        ArrayList<MonDTO> list=new ArrayList<>();
        CreateDatabase dbHelper=new CreateDatabase(context);
        Cursor cursor= dbHelper.Getdata("SELECT pm."+CreateDatabase.TBL_MON_MAMON+",sc."+CreateDatabase.TBL_MON_TENMON+",COUNT(pm."+CreateDatabase.TBL_MON_MAMON+") FROM "+CreateDatabase.TBL_DONDAT+" pm,"+CreateDatabase.TBL_MON+" sc WHERE pm."+CreateDatabase.TBL_MON_MAMON+" = sc."+CreateDatabase.TBL_MON_MAMON+" GROUP BY pm."+CreateDatabase.TBL_MON_MAMON+",sc."+CreateDatabase.TBL_MON_TENMON+" ORDER BY COUNT(pm."+CreateDatabase.TBL_MON_MAMON+") DESC LIMIT 10");
        if(cursor.getCount()!=0){
            while (cursor.moveToNext()){
                list.add(new MonDTO(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));
            }
        }
        return list;
    }


}
