package bangdnph29162.fpoly.ad2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bangdnph29162.fpoly.ad2.Adapter.UserAdapter;
import bangdnph29162.fpoly.ad2.DAO.UserDAO;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    UserDAO dao;
    Button btnThem;
    UserAdapter adapter;
    RecyclerView recyclerView;
    List<User> list = new ArrayList<>();
    String text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.ryc_01);
        btnThem = findViewById(R.id.btnThem);
        dao = new UserDAO(this);
        showUser();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_them);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams winLayoutParams = window.getAttributes();
                winLayoutParams.gravity = Gravity.CENTER;
                window.setAttributes(winLayoutParams);
                dialog.setCancelable(false);

                EditText edName, edPrice, edDiscription;
                Button btnAdd, btnCancel;
                Spinner spUser;

                edName = dialog.findViewById(R.id.edName);
                edPrice = dialog.findViewById(R.id.edPrice);
                edDiscription = dialog.findViewById(R.id.edDiscription);
                btnAdd = dialog.findViewById(R.id.btnAdd);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                spUser = dialog.findViewById(R.id.spUser);

                ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_spinner, android.R.layout.simple_spinner_item);
                adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spUser.setAdapter(adapterr);
                spUser.setOnItemSelectedListener(MainActivity.this);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();

                        if (edName.getText().toString().trim().equals("") || edPrice.getText().toString().trim().equals("") || edDiscription.getText().toString().trim().equals("")) {
                            Toast.makeText(MainActivity.this, "Vui lòng không để trống dữ liệu!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (text.equals("img1") || text.equals("img2") || text.equals("img3") || text.equals("img4") || text.equals("img5")) {
                            user.setName(edName.getText().toString().trim());
                            user.setPrice(edPrice.getText().toString().trim());
                            user.setDiscription(edDiscription.getText().toString().trim());
                            user.setImage(text);

                            if (dao.insertUser(user) > 0) {
                                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                showUser();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Vui lòng chọn các img sau: img1 ; img2 ; img3 ; img4 ; img5", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }



    private void deleteu(int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UserDAO dao = new UserDAO(MainActivity.this);
                if (dao.deleteUser(list.get(index)) > 0) {
                    Toast.makeText(MainActivity.this, "Xóa user thành công", Toast.LENGTH_SHORT).show();
                    showUser();

                } else {
                    Toast.makeText(MainActivity.this, "Xóa user không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
        showUser();
    }

    private void editu(int i) {
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams winLayoutParams = window.getAttributes();
        winLayoutParams.gravity = Gravity.CENTER;
        window.setAttributes(winLayoutParams);
        dialog.setCancelable(false);
        EditText edNameUpdate, edPriceUpdate, edDiscriptionUpdate;
        Button btnUpdate, btnCancelUpdate;
        Spinner spUserupdate;
        edNameUpdate = dialog.findViewById(R.id.edNameUpdate);
        edPriceUpdate = dialog.findViewById(R.id.edPriceUpdate);
        edDiscriptionUpdate = dialog.findViewById(R.id.edDiscriptionUpdate);
        btnUpdate = dialog.findViewById(R.id.btnUpdate);
        btnCancelUpdate = dialog.findViewById(R.id.btnCancelUpdate);

        // Set initial values from the selected item
        User selectedUser = list.get(i);
        edNameUpdate.setText(selectedUser.getName());
        edPriceUpdate.setText(selectedUser.getPrice());
        edDiscriptionUpdate.setText(selectedUser.getDiscription());

        spUserupdate = dialog.findViewById(R.id.spUserUpdate);
        ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_spinner, android.R.layout.simple_spinner_item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUserupdate.setAdapter(adapterr);

        // Find the position of the current image and set it as selected
        int selectedPosition = adapterr.getPosition(selectedUser.getImage());
        spUserupdate.setSelection(selectedPosition);

        spUserupdate.setOnItemSelectedListener(MainActivity.this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = list.get(i);

                if (edNameUpdate.getText().toString().trim().equals("") || edPriceUpdate.getText().toString().trim().equals("") || edDiscriptionUpdate.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (text.equals("img1") || text.equals("img2") || text.equals("img3") || text.equals("img4") || text.equals("img5")) {
                    user.setName(edNameUpdate.getText().toString().trim());
                    user.setPrice(edPriceUpdate.getText().toString().trim());
                    user.setDiscription(edDiscriptionUpdate.getText().toString().trim());
                    user.setImage(text);

                    if (dao.editUser(user) > 0) {
                        Toast.makeText(MainActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        showUser();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(MainActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng chọn các img sau: img1 ; img2 ; img3 ; img4 ; img5", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        btnCancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    public void showUser() {
        list = dao.getAll();
        UserAdapter adapter = new UserAdapter(this, list, new Service() {
            @Override
            public void editUser(int i) {
                editu(i);
            }

            @Override
            public void deleteUser(int i) {
                deleteu(i);
            }
        });
        recyclerView.setAdapter(adapter);
    }
    private List<User> getListUser(){
        List<User> listUser = new ArrayList<>();
        dao = new UserDAO(this);
        listUser=dao.getAll();
        return listUser;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}