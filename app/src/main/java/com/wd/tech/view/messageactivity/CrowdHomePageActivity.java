package com.wd.tech.view.messageactivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.api.ApiService;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.messagebean.AlterCrowdGroupNameBean;
import com.wd.tech.bean.messagebean.CrowGroupDetailMessageBean;
import com.wd.tech.bean.messagebean.DeleteCrowdGroupBean;
import com.wd.tech.bean.messagebean.UploadCrowdHeadpicBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RetrofitUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;



//自己的群
public class CrowdHomePageActivity extends BaseActivity<Presenter> implements IContract.IView, View.OnClickListener {
    @BindView(R.id.crowd_head)
    ImageView crowdHead;
    @BindView(R.id.crowd_member_count)
    TextView crowdMemberCount;
    @BindView(R.id.btn_crowd_member)
    ImageView btnCrowdMember;
    @BindView(R.id.crowd_name)
    TextView crowdName;
    @BindView(R.id.btn_crowd_introduce)
    ImageView btnCrowdIntroduce;
    @BindView(R.id.btn_crowd_infrom)
    ImageView btnCrowdInfrom;
    @BindView(R.id.btn_crowd_manage)
    ImageView btnCrowdManage;
    @BindView(R.id.btn_quitcrowd)
    Button btnQuitcrowd;
    @BindView(R.id.btn_crowd_chatrecord)
    LinearLayout btnCrowdChatrecord;
    private CrowGroupDetailMessageBean.ResultBean crowgroupdetailmessageresult;
    private int groupId;
    private PopupWindow popupWindow;
    private TextView group_name;
    private EditText update_friendmessage;
    private Observable<UploadCrowdHeadpicBean> uploadCrowdHeadpicBeandata;
    private  int MAX_SIZE = 769;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_crowd_home_page;
    }

    @Override
    protected void initData() {
        groupId = getIntent().getIntExtra("groupId", 0);
        presenter.getCrowGroupDetailMessageBeandata(groupId);
    }

    @Override
    protected void initView() {

    }

    public void back(View view) {
        finish();
    }

    @Override
    public void success(Object o) {
        if (o instanceof CrowGroupDetailMessageBean) {
            crowgroupdetailmessageresult = ((CrowGroupDetailMessageBean) o).getResult();
            RetrofitUtil.getInstance().getRectphoto(crowgroupdetailmessageresult.getGroupImage(), crowdHead);
            crowdMemberCount.setText("共" + crowgroupdetailmessageresult.getCurrentCount() + "人");
            crowdName.setText(crowgroupdetailmessageresult.getGroupName() + "群");
            btnCrowdIntroduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentchatintroduce = new Intent(CrowdHomePageActivity.this, CrowdIntroduceActivity.class);
                      intentchatintroduce.putExtra("groupId1",groupId);
                    startActivity(intentchatintroduce);
                }
            });
            btnQuitcrowd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showquitPopupWindow();
                }


            });
        }

        if (o instanceof AlterCrowdGroupNameBean){
            Toast.makeText(App.context,((AlterCrowdGroupNameBean) o).getMessage(),Toast.LENGTH_LONG).show();
            if (((AlterCrowdGroupNameBean) o).getStatus().equals("0000")){
                presenter.getCrowGroupDetailMessageBeandata(groupId);
            }

        }
        if (o instanceof DeleteCrowdGroupBean){
            Toast.makeText(App.context,((DeleteCrowdGroupBean) o).getMessage(),Toast.LENGTH_LONG).show();
            if (((DeleteCrowdGroupBean) o).getStatus().equals("0000")){
                presenter.getCrowGroupDetailMessageBeandata(groupId);
            }
        }

        crowdName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              updatePopupWindow();
            }
        });

        crowdHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headpicPopupWindow();
            }
        });
    }
    //上传
    private void headpicPopupWindow() {
        View contentView = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.popwindow, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button xj = (Button) contentView.findViewById(R.id.xj);
        Button xc = (Button) contentView.findViewById(R.id.xc);
        Button tc = (Button) contentView.findViewById(R.id.tc);
        Button qx = (Button) contentView.findViewById(R.id.qx);

        xj.setOnClickListener(this);
        xc.setOnClickListener(this);
        tc.setOnClickListener(this);
        qx.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    //修改群名
    private void updatePopupWindow() {
        View contentView = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.update_crowd_name, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_update = (Button) contentView.findViewById(R.id.btn_update);
        Button btn_updateno = (Button) contentView.findViewById(R.id.btn_updateno);
        update_friendmessage = (EditText) contentView.findViewById(R.id.update_friendmessage);

        btn_update.setOnClickListener(this);
        btn_updateno.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void failur(Throwable throwable) {

    }

    //退群
    private void showquitPopupWindow() {
        View contentView = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.quit_crowd_item, null);
        popupWindow = new PopupWindow(contentView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setContentView(contentView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        //设置各个控件的点击响应
        Button btn_yes = (Button) contentView.findViewById(R.id.btn_yes);
        Button btn_no = (Button) contentView.findViewById(R.id.btn_no);
        group_name = (TextView) contentView.findViewById(R.id.group_name);
        group_name.setText("您将解散" + crowgroupdetailmessageresult.getGroupName() + "群");
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(CrowdHomePageActivity.this).inflate(R.layout.fragment_message, null);
        popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    @OnClick({R.id.btn_crowd_member, R.id.btn_crowd_infrom, R.id.btn_crowd_manage, R.id.btn_crowd_chatrecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_crowd_member:
                Intent intentmember = new Intent(CrowdHomePageActivity.this, CrowdMemberActivity.class);
                intentmember.putExtra("groupIdmember",groupId);
                startActivity(intentmember);
                break;
            case R.id.btn_crowd_infrom:
                Intent intentinfrom = new Intent(CrowdHomePageActivity.this, CrowdInformActivity.class);
                startActivity(intentinfrom);
                break;
            case R.id.btn_crowd_manage:
                Intent intentmanage = new Intent(CrowdHomePageActivity.this, CrowdManageActivity.class);
                intentmanage.putExtra("groupIdmanage",groupId);
                startActivity(intentmanage);
                break;
            case R.id.btn_crowd_chatrecord:
                Intent  intentcc=new Intent(CrowdHomePageActivity.this,CrowdChatActivity.class);
                intentcc.putExtra("groupIdcc",groupId);
                intentcc.putExtra("groupname",crowgroupdetailmessageresult.getGroupName());
                intentcc.putExtra("crowdhead",crowgroupdetailmessageresult.getGroupImage());
                startActivity(intentcc);
                break;

        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_yes: {
                presenter.getDeleteCrowdGroupBeandata(groupId);
                popupWindow.dismiss();
            }
            break;
            case R.id.btn_no: {
                popupWindow.dismiss();
            }
            break;
            case R.id.btn_update: {
                String name=update_friendmessage.getText().toString().trim();
                if(name!=null){
                    presenter.getAlterCrowdGroupNameBeandata(groupId,name);
                }
                popupWindow.dismiss();

            }
            break;
            case R.id.btn_updateno: {
                popupWindow.dismiss();
            }
            break;
            case R.id.xj: {

                popupWindow.dismiss();
            }
            break;
            case R.id.xc: {
                // 打开系统图库选择图片
                Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 0);
                popupWindow.dismiss();
            }
            break;
            case R.id.tc: {
                popupWindow.dismiss();
            }
            break;
            case R.id.qx: {
                popupWindow.dismiss();
            }
            break;
        }
    }


    //上传

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri uri = data.getData();
            String[] arr = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(uri, arr, null, null, null);
            int img_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String img_path = cursor.getString(img_index);
            File file = new File(img_path);
            String path = file.getAbsolutePath();
            File file1 = new File( path );
            RetrofitUtil instance = RetrofitUtil.getInstance();
            ApiService service = instance.creatService(ApiService.class);
            RequestBody requestBody = RequestBody.create( MediaType.parse( "multipart/form-data"), file );
            RequestBody requestBody2 = RequestBody.create( MediaType.parse( "multipart/form-data"), groupId+"" );
            MultipartBody.Part formData = MultipartBody.Part.createFormData( "image", file1.getName(), requestBody );
            uploadCrowdHeadpicBeandata = service.getUploadCrowdHeadpicBeandata(requestBody2, formData);
            uploadCrowdHeadpicBeandata.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<UploadCrowdHeadpicBean>() {
                        @Override
                        public void accept(UploadCrowdHeadpicBean uploadCrowdHeadpicBean) throws Exception {
                            Toast.makeText(App.context, uploadCrowdHeadpicBean.getMessage(), Toast.LENGTH_LONG).show();
                            if (uploadCrowdHeadpicBean.getStatus().equals("0000")) {
                                presenter.getCrowGroupDetailMessageBeandata(groupId);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(App.context,throwable.getMessage() , Toast.LENGTH_LONG).show();

                        }
                    });
            BitmapFactory.Options options = new BitmapFactory.Options();


            options.inJustDecodeBounds = false;
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                BitmapFactory.decodeStream(inputStream,null,options);
                inputStream.close();
                int height = options.outHeight;
                int width = options.outWidth;
                int sampleSize = 1;
                int max = Math.max(height,width);
                if (max>MAX_SIZE){
                    int nw = width/2;
                    int nh = height/2;
                    while ((nw/sampleSize)> MAX_SIZE || (nh / sampleSize)>MAX_SIZE){
                        sampleSize *=2;
                    }
                }
                options.inSampleSize = sampleSize;
                options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeStream( getContentResolver().openInputStream( uri ), null, options );
                crowdHead.setImageBitmap(bitmap);


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }

