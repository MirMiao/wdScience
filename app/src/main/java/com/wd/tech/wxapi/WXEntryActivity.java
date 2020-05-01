package com.wd.tech.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.App;
import com.wd.tech.api.ApiService;
import com.wd.tech.bean.informationentity.LoginEntity;
import com.wd.tech.bean.messagebean.WxloginBean;
import com.wd.tech.util.RetrofitUtil;
import com.wd.tech.util.SpUtil;
import com.wd.tech.view.activity.MainActivity;
import com.wd.tech.view.information.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.wd.tech.wxapi.Wxutil.api;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/5/1 16:14
 * @classname :
 */
public class WXEntryActivity  extends AppCompatActivity implements IWXAPIEventHandler {


    private Observable<WxloginBean> wxloginBeandata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String  code=   ((SendAuth.Resp) baseResp).code;
                Log.i("code", "run: "+code);
                 log(code);
            }
        }.start();
        finish();
    }
    public void log(final  String code) {
        ApiService  apiService = RetrofitUtil.getInstance().creatService(ApiService.class);
        wxloginBeandata = apiService.getWxloginBeandata(code);
        wxloginBeandata.observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<WxloginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(WxloginBean wxloginBean) {
                         if (wxloginBean.getStatus().equals("0000")){
                             WxloginBean.ResultBean result = wxloginBean.getResult();
                             Toast.makeText(App.context,wxloginBean.getMessage(),Toast.LENGTH_LONG).show();
                             int userId = result.getUserId();
                             String sessionId = result.getSessionId();
                             SpUtil.saveInt("userid", userId);
                             SpUtil.saveString("sesseion", sessionId);
                             List<WxloginBean.ResultBean> list = new ArrayList<>();
                             list.add(result);
                             SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                             SharedPreferences.Editor edit = sp.edit();
                             Gson gson = new Gson();
                             String s = gson.toJson(list);
                             edit.putString("userInfo", s).commit();
                             Intent  intent = new Intent(WXEntryActivity.this, MainActivity.class);
                             startActivity(intent);

                         }
                    else {
                             Toast.makeText(App.context,wxloginBean.getMessage(),Toast.LENGTH_LONG).show();
                         }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
