package bangdnph29162.fpoly.ad2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String db="Lab3";
    public static final int version=3;

    public DBHelper(@Nullable Context context) {
        super(context, db, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,price TEXT,discription TEXT , image TEXT)";
        db.execSQL(sql);

        sql = "INSERT INTO user (name,price,discription,image) VALUES ('Hai Long','10 diem','Dep Trai','img2')";
        db.execSQL(sql);
        sql = "INSERT INTO user (name,price,discription,image) VALUES ('Viet Linh','9 diem','Xinh Trai','img3')";
        db.execSQL(sql);
        sql = "INSERT INTO user (name,price,discription,image) VALUES ('Hai Linh','8 diem','Dep Gai','img4')";
        db.execSQL(sql);
        sql = "INSERT INTO user (name,price,discription,image) VALUES ('Viet Long','11 diem','Dep Trai','img5')";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop = "DROP TABLE IF EXISTS user";
        db.execSQL(drop);
        onCreate(db);
    }
}
