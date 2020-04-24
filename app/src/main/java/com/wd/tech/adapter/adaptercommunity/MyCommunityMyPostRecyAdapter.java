package com.wd.tech.adapter.adaptercommunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.beancommunity.MyPostResult;
import com.wd.tech.util.RetrofitUtil;

import java.util.List;

public class MyCommunityMyPostRecyAdapter extends RecyclerView.Adapter<MyCommunityMyPostRecyAdapter.MyPostViewHolder> {
    private List<MyPostResult> list;
    private Context context;
    public MyCommunityMyPostRecyAdapter(List<MyPostResult> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_mypost, parent, false);
        MyPostViewHolder myPostViewHolder = new MyPostViewHolder(inflate);
        return myPostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPostViewHolder holder, int position) {

        MyPostResult myPostResult = list.get(position);
        String headPic = myPostResult.getHeadPic();
        String nickName = myPostResult.getNickName();
        String signature = myPostResult.getSignature();

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(headPic,holder.mypostHeadim);

        holder.mypostName.setText(nickName);
        holder.mypostQianming.setText(signature);

        holder.mypostJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.mypostInnerrecy.setLayoutManager(new LinearLayoutManager(context));
        MyCommunityMyPostInnerRecy myCommunityMyPostInnerRecy=new MyCommunityMyPostInnerRecy(list,context);
        holder.mypostInnerrecy.setAdapter(myCommunityMyPostInnerRecy);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyPostViewHolder extends RecyclerView.ViewHolder {
        private ImageView mypostHeadim;
        private TextView mypostName;
        private TextView mypostQianming;
        private ImageView mypostJia;
        private RecyclerView mypostInnerrecy;
        public MyPostViewHolder(@NonNull View itemView) {
            super(itemView);
            mypostHeadim = (ImageView) itemView.findViewById(R.id.mypost_headim);
            mypostName = (TextView) itemView.findViewById(R.id.mypost_name);
            mypostQianming = (TextView) itemView.findViewById(R.id.mypost_qianming);
            mypostJia = (ImageView) itemView.findViewById(R.id.mypost_jia);
            mypostInnerrecy = (RecyclerView) itemView.findViewById(R.id.mypost_innerrecy);
        }
    }

}
