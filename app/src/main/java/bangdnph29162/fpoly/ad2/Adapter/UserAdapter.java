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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    Context context;
    List<User> list ;
    Service service;


    public UserAdapter(Context context, List<User> list,Service service) {
        this.context = context;
        this.list = list;
        this.service=service;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);// layout chung cua ung dung
        View view_of_item = inflater.inflate(R.layout.item,parent,false);// layout cua 1 phan tu
        UserViewHolder viewHolder = new UserViewHolder(view_of_item);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User user = list.get(position);
        holder.tv_id.setText(""+user.getId());
        holder.tv_name.setText(user.getName());
        holder.tv_price.setText(user.getPrice());
        holder.tv_discription.setText(user.getDiscription());
        String imgName = user.getImage();
        int res_img = ((Activity)context).getResources().getIdentifier(imgName,"drawable",((Activity)context).getPackageName());
        holder.img_res.setImageResource(res_img);
        final int a = position;



        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.editUser(a);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
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
        TextView tv_id,tv_name,tv_price,tv_discription;
        ImageView img_res,imgEdit,imgDelete;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            //an xa cac view
            tv_id = itemView.findViewById(R.id.tvId);
            tv_name=itemView.findViewById(R.id.tvName);
            tv_price=itemView.findViewById(R.id.tvPrice);
            tv_discription=itemView.findViewById(R.id.tvDiscription);
            img_res=itemView.findViewById(R.id.imgAvatar);
            imgDelete=itemView.findViewById(R.id.imgDelete);
            imgEdit=itemView.findViewById(R.id.imgEdit);


        }
    }
}

