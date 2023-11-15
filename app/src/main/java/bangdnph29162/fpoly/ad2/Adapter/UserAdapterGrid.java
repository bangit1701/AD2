package bangdnph29162.fpoly.ad2.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import bangdnph29162.fpoly.ad2.R;
import bangdnph29162.fpoly.ad2.Service;
import bangdnph29162.fpoly.ad2.User;


public class UserAdapterGrid extends RecyclerView.Adapter<UserAdapterGrid.UserViewHolder> {
    Context context;
    List<User> list ;
    Service service;


    public UserAdapterGrid(Context context, List<User> list,Service service) {
        this.context = context;
        this.list = list;
        this.service=service;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);// layout chung cua ung dung
        View view_of_item = inflater.inflate(R.layout.item_grid,parent,false);// layout cua 1 phan tu
        UserViewHolder viewHolder = new UserViewHolder(view_of_item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = list.get(position);
        holder.tv_id_grid.setText(""+user.getId());
        holder.tv_name_grid.setText(user.getName());
        holder.tv_price_grid.setText(user.getPrice());
        holder.tv_discription_grid.setText(user.getDiscription());
        String imgName = user.getImage();
        int res_img = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.img_res.setImageResource(res_img);


        final int a = position;



        holder.imgEdit_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.editUser(a);
            }
        });
        holder.imgDelete_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.deleteUser(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tv_id_grid,tv_name_grid,tv_price_grid,tv_discription_grid;
        ImageView img_res,imgEdit_grid,imgDelete_grid;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            //an xa cac view
            tv_id_grid = itemView.findViewById(R.id.tv_id_grid);
            tv_name_grid=itemView.findViewById(R.id.tv_name_grid);
            tv_price_grid = itemView.findViewById(R.id.tv_price_grid);
            tv_discription_grid = itemView.findViewById(R.id.tv_discription_grid);
            img_res=itemView.findViewById(R.id.img_avatar_grid);
            imgDelete_grid=itemView.findViewById(R.id.img_delete_grid);
            imgEdit_grid=itemView.findViewById(R.id.img_edit_grid);


        }
    }
}

