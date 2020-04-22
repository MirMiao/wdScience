package com.wd.tech.view.communityactivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.adaptercommunity.MyCommentaryRecyAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.beancommunity.CommentaryData;
import com.wd.tech.bean.beancommunity.Commentaryresult;
import com.wd.tech.contract.IContract;
import com.wd.tech.event.CommunityEvent;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;
public class QuanbupinglunActivity extends BaseActivity<Presenter> implements IContract.IView {
    private RecyclerView recycommentary;
    private ImageView fanhui;
    private ImageView touxiang;
    private TextView faburen;
    private String headpic;
    private String name;

    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_quanbupinglun;
    }

    @Override
    protected void initData() {
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recycommentary.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void initView() {

       EventBus.getDefault().register(this);

        recycommentary = (RecyclerView) findViewById(R.id.recycommentary);

        fanhui = (ImageView) findViewById(R.id.fanhui);
        touxiang = (ImageView) findViewById(R.id.touxiang);
        faburen = (TextView) findViewById(R.id.faburen);

        RetrofitUtil instance = RetrofitUtil.getInstance();
        instance.getRoundphoto(headpic,touxiang);
        faburen.setText(name);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void Event(CommunityEvent communityEvent){
        int id=communityEvent.id;
        headpic = communityEvent.headPic;
        name = communityEvent.nickName;
        presenter.getCommentary(id,1,5);
    }

    @Override
    public void success(Object o) {
        if(o instanceof CommentaryData){
            List<Commentaryresult> result = ((CommentaryData) o).getResult();
            if(result!=null){
                MyCommentaryRecyAdapter myCommentaryRecyAdapter=new MyCommentaryRecyAdapter(result,this);
                recycommentary.setAdapter(myCommentaryRecyAdapter);
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
