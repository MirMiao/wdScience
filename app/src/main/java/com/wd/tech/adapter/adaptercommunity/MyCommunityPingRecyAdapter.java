package com.wd.tech.adapter.adaptercommunity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.bean.beancommunity.CommunityVoList;
import java.util.List;
public class MyCommunityPingRecyAdapter extends RecyclerView.Adapter<MyCommunityPingRecyAdapter.MyCommunityPingViewHolder> {
    private List<CommunityVoList> lists;
    private Context context;
    public MyCommunityPingRecyAdapter(List<CommunityVoList> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCommunityPingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recy_communitypinglun, parent, false);
        MyCommunityPingViewHolder myCommunityPingViewHolder = new MyCommunityPingViewHolder(inflate);
        return myCommunityPingViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCommunityPingViewHolder holder, int position) {
        CommunityVoList communityVoList = lists.get(position);
        String nickName = communityVoList.getNickName();
        String content = communityVoList.getContent();

        holder.pingname.setText(nickName);
        holder.pingneirong.setText(content);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    class MyCommunityPingViewHolder extends RecyclerView.ViewHolder {
        private TextView pingname;
        private TextView pingneirong;
        public MyCommunityPingViewHolder(@NonNull View itemView) {
            super(itemView);
            pingname = (TextView) itemView.findViewById(R.id.pingname);
            pingneirong = (TextView) itemView.findViewById(R.id.pingneirong);
        }
    }

}
