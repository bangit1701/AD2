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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import bangdnph29162.fpoly.ad2.Adapter.UserAdapterGrid;
import bangdnph29162.fpoly.ad2.DAO.UserDAO;

public class ActivityGrid extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    UserDAO dao;
    Button btnThemGrid;
    UserAdapterGrid adapter;
    RecyclerView recyclerViewGrid;
    List<User> list = new ArrayList<>();
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        recyclerViewGrid = findViewById(R.id.ryc_01_grid);

        btnThemGrid = findViewById(R.id.btnThemGrid);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerViewGrid.setLayoutManager(gridLayoutManager);

        dao = new UserDAO(this);
        showUser();

        btnThemGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Dialog
                Dialog dialog = new Dialog(ActivityGrid.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_them);
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                WindowManager.LayoutParams winLayoutParams = window.getAttributes();
                winLayoutParams.gravity = Gravity.CENTER;
                window.setAttributes(winLayoutParams);
                dialog.setCancelable(false);

                EditText edName, edPrice, edDiscription, edImage;
                Button btnAdd, btnCancel;
                Spinner spUser;

                edName = dialog.findViewById(R.id.edName);
                edPrice = dialog.findViewById(R.id.edPrice);
                edDiscription = dialog.findViewById(R.id.edDiscription);
                btnAdd = dialog.findViewById(R.id.btnAdd);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                spUser = dialog.findViewById(R.id.spUser);

                ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(ActivityGrid.this, R.array.array_spinner, android.R.layout.simple_spinner_item);
                adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spUser.setAdapter(adapterr);

                spUser.setOnItemSelectedListener(ActivityGrid.this);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User user = new User();

                        String name = edName.getText().toString().trim();
                        String price = edPrice.getText().toString().trim();
                        String discription = edDiscription.getText().toString().trim();

                        if (name.isEmpty() || price.isEmpty() || discription.isEmpty()) {
                            Toast.makeText(ActivityGrid.this, "Vui lòng không để trống dữ liệu!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (text.equals("img1") || text.equals("img2") || text.equals("img3") || text.equals("img4") || text.equals("img5")) {
                            user.setName(name);
                            user.setPrice(price);
                            user.setDiscription(discription);
                            user.setImage(text);

                            if (dao.insertUser(user) > 0) {
                                Toast.makeText(ActivityGrid.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                showUser();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(ActivityGrid.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ActivityGrid.this, "Vui lòng chọn các ảnh sau: img1 ; img2 ; img3 ; img4 ; img5", Toast.LENGTH_SHORT).show();
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
                UserDAO dao = new UserDAO(ActivityGrid.this);
                if (dao.deleteUser(list.get(index)) > 0) {
                    Toast.makeText(ActivityGrid.this, "Xóa user thành công", Toast.LENGTH_SHORT).show();
                    showUser();

                } else {
                    Toast.makeText(ActivityGrid.this, "Xóa user không thành công", Toast.LENGTH_SHORT).show();
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
        Dialog dialog = new Dialog(ActivityGrid.this);
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
        spUserupdate = dialog.findViewById(R.id.spUserUpdate);

        // Set initial values for the EditText fields
        User selectedUser = list.get(i);
        edNameUpdate.setText(selectedUser.getName());
        edPriceUpdate.setText(selectedUser.getPrice());
        edDiscriptionUpdate.setText(selectedUser.getDiscription());

        ArrayAdapter<CharSequence> adapterr = ArrayAdapter.createFromResource(ActivityGrid.this, R.array.array_spinner, android.R.layout.simple_spinner_item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUserupdate.setAdapter(adapterr);

        // Find the position of the selected user's image in the spinner
        String name = selectedUser.getImage();
        int selectedImagePosition = -1;
        for (int x = 0; x < spUserupdate.getCount(); x++) {
            if (name.equals(spUserupdate.getItemAtPosition(x).toString())) {
                selectedImagePosition = x;
                break;
            }
        }

        // Set the spinner to the position of the selected user's image
        if (selectedImagePosition != -1) {
            spUserupdate.setSelection(selectedImagePosition);
        }

        spUserupdate.setOnItemSelectedListener(ActivityGrid.this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = list.get(i);
                String updatedName = edNameUpdate.getText().toString().trim();
                String updatedPrice = edPriceUpdate.getText().toString().trim();
                String updatedDiscription = edDiscriptionUpdate.getText().toString().trim();

                if (updatedName.equals("") || updatedPrice.equals("") || updatedDiscription.equals("")) {
                    Toast.makeText(ActivityGrid.this, "Không được để trống dữ liệu", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (text.equals("img1") || text.equals("img2") || text.equals("img3") || text.equals("img4") || text.equals("img5")) {
                    user.setName(updatedName);
                    user.setPrice(updatedPrice);
                    user.setDiscription(updatedDiscription);
                    user.setImage(text);

                    if (dao.editUser(user) > 0) {
                        Toast.makeText(ActivityGrid.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        showUser();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(ActivityGrid.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ActivityGrid.this, "Vui lòng chọn các ảnh sau: img1, img2, img3, img4, img5", Toast.LENGTH_SHORT).show();
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
        UserAdapterGrid adapter = new UserAdapterGrid(this, list, new Service() {
            @Override
            public void editUser(int i) {
                editu(i);
            }

            @Override
            public void deleteUser(int i) {
                deleteu(i);
            }
        });
        recyclerViewGrid.setAdapter(adapter);
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


