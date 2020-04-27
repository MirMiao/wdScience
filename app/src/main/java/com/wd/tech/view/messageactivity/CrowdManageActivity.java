package com.wd.tech.view.messageactivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.infromation.MyCrowdGroupAllUserMessagea2dapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.AdjustCrowdMemberBean;
import com.wd.tech.bean.messagebean.CrowdGroupAllUserMessageBean;
import com.wd.tech.bean.messagebean.DeleteCrowdMemberBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
//群管理
public class CrowdManageActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.btn_applyfriend)
    ImageView btnApplyfriend;
    @BindView(R.id.crowdManagListView)
    SwipeRecyclerView crowdManagListView;
    private int groupIdmanage;
    private List<CrowdGroupAllUserMessageBean.ResultBean> crowdgroupallusermessageresult;
    private MyCrowdGroupAllUserMessagea2dapter myCrowdGroupAllUserMessagea2dapter;
    private int role;
    private PopupWindow popupWindow;
    private  int madapterPosition;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_manage;
    }

    @Override
    protected void initData() {
        groupIdmanage = getIntent().getIntExtra("groupIdmanage", 0);
        presenter.getCrowdGroupAllUserMessageBeandata(groupIdmanage);
        // 设置监听器。
            crowdManagListView.setSwipeItemMenuEnabled(true);
            crowdManagListView.setSwipeMenuCreator(mSwipeMenuCreator);
            // 菜单点击监听。
            crowdManagListView.setOnItemMenuClickListener(mMenuItemClickListener);
    }
    @Override
    protected void initView() {

    }
    // 创建菜单：
 private     SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            if(crowdgroupallusermessageresult.get(viewType).getRole()==2){
            SwipeMenuItem modifyItem = new SwipeMenuItem(CrowdManageActivity.this)
                    .setBackgroundColor(Color.BLUE)
                    .setText("撤销管理员")
                    .setTextColor(Color.WHITE)
                    .setTextSize(15) // 文字大小。
                    .setWidth(180)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(modifyItem);
            }
            if(crowdgroupallusermessageresult.get(viewType).getRole()==1){
            SwipeMenuItem shezhiItem = new SwipeMenuItem(CrowdManageActivity.this)
                    .setBackgroundColor(Color.BLUE)
                    .setText("设置管理员")
                    .setTextColor(Color.WHITE)
                    .setTextSize(15) // 文字大小。
                    .setWidth(180)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(shezhiItem);
            }
            if(crowdgroupallusermessageresult.get(viewType).getRole()!=3){
            // 2 删除
            SwipeMenuItem deleteItem = new SwipeMenuItem(CrowdManageActivity.this);
            deleteItem.setText("移除")
                    .setBackgroundColor(Color.BLUE)
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(15) // 文字大小。
                    .setWidth(180)
                    .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                rightMenu.addMenuItem(deleteItem);
            }
            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };


    OnItemMenuClickListener mMenuItemClickListener = new OnItemMenuClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
            menuBridge.closeMenu();
            int menuPosition= menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。
            madapterPosition=adapterPosition;
            if (menuPosition == 0) {
                   int userId= crowdgroupallusermessageresult.get(adapterPosition).getUserId();
                        if (crowdgroupallusermessageresult.get(adapterPosition).getRole()==1){
                            presenter.getAdjustCrowdMemberBeandata(groupIdmanage,userId,2);
                        }
                        if (crowdgroupallusermessageresult.get(adapterPosition).getRole()==2){
                            presenter.getAdjustCrowdMemberBeandata(groupIdmanage,userId,1);
                        }
            } else {
                showdeletePopupWindow();
            }
        }


    };
     //删除PopupWindow
    private void showdeletePopupWindow() {
        View contentView = LayoutInflater.from(CrowdManageActivity.this).inflate(R.layout.quit_crowd_item, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_yes = (Button) contentView.findViewById(R.id.btn_yes);
        Button btn_no = (Button) contentView.findViewById(R.id.btn_no);
        TextView group_name = (TextView) contentView.findViewById(R.id.group_name);
        group_name.setText("确定将该用户移除该群吗？");
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(CrowdManageActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowdGroupAllUserMessageBean) {
            crowdgroupallusermessageresult = ((CrowdGroupAllUserMessageBean) o).getResult();
            myCrowdGroupAllUserMessagea2dapter = new MyCrowdGroupAllUserMessagea2dapter(crowdgroupallusermessageresult, this);
            crowdManagListView.setAdapter(myCrowdGroupAllUserMessagea2dapter);
            crowdManagListView.setLayoutManager(new LinearLayoutManager(this));
        }
        //撤销
        if (o instanceof AdjustCrowdMemberBean){
            if (((AdjustCrowdMemberBean) o).getStatus().equals("0000")){
                Toast.makeText(App.context,((AdjustCrowdMemberBean) o).getMessage(),Toast.LENGTH_LONG).show();
                presenter.getCrowdGroupAllUserMessageBeandata(groupIdmanage);
                myCrowdGroupAllUserMessagea2dapter.notifyDataSetChanged();
            }
        }
        //移除
        if (o instanceof DeleteCrowdMemberBean){
            if (((DeleteCrowdMemberBean) o).getStatus().equals("0000")){
                Toast.makeText(App.context,((DeleteCrowdMemberBean) o).getMessage(),Toast.LENGTH_LONG).show();
                presenter.getCrowdGroupAllUserMessageBeandata(groupIdmanage);
                myCrowdGroupAllUserMessagea2dapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void failur(Throwable throwable) {

    }


    @OnClick(R.id.btn_applyfriend)
    public void onViewClicked() {
        Intent intentsreach = new Intent(CrowdManageActivity.this, SreachActivity.class);
        intentsreach.putExtra("groupIdscreach",groupIdmanage);
        startActivity(intentsreach);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                 int userId= crowdgroupallusermessageresult.get( madapterPosition).getUserId();
                 presenter.getDeleteCrowdMemberBeandata(groupIdmanage,userId);
                popupWindow.dismiss();
                break;
            case R.id.btn_no:
                popupWindow.dismiss();
                break;
        }
    }
}
