package bangdnph29162.fpoly.ad2.DAO;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import bangdnph29162.fpoly.ad2.Database.DBHelper;
import bangdnph29162.fpoly.ad2.User;

public class UserDAO {
    private SQLiteDatabase db;

    public UserDAO(Context context) {
        DBHelper helper = new DBHelper(context);
        db = helper.getWritableDatabase();

    }

    public long insertUser(User u){
        ContentValues values = new ContentValues();
        values.put("name",u.getName());
        values.put("price",u.getPrice());
        values.put("image",u.getImage());
        return db.insert("user",null,values);

    }
    public int editUser(User u){
        ContentValues values = new ContentValues();
        values.put("name",u.getName());
        values.put("price",u.getPrice());
        values.put("discription",u.getDiscription());
        values.put("image",u.getImage());
        return db.update("user",values,"id=?",new String []{String.valueOf(u.getId())});

    }
    public int deleteUser(User u){

        return db.delete("user","id=?",new String []{String.valueOf(u.getId())});

    }

    public List<User> getAll(){
        String sql = "SELECT * FROM user";
        return getList(sql);
    }

    @SuppressLint("Range")
    private List<User> getList(String sql, String ...Agrs) {
        List<User> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, Agrs);
        while(c.moveToNext()){
            User user = new User();
            user.setId(c.getInt(c.getColumnIndex("id")));
            user.setName(c.getString(c.getColumnIndex("name")));
            user.setPrice(c.getString(c.getColumnIndex("price")));
            user.setDiscription(c.getString(c.getColumnIndex("discription")));
            user.setImage(c.getString(c.getColumnIndex("image")));
            list.add(user);
        }

        return list;
    }

}

