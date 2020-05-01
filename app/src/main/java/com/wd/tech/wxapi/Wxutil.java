package com.wd.tech.wxapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelmsg.GetMessageFromWX;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.App;

import butterknife.internal.Constants;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/5/1 16:16
 * @classname :
 */
public class Wxutil {
    // APP_ID 替换为你的应用从官方网站申请到的合法appID
    private static final String APP_ID = "wx4c96b6b8da494224";

    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI api;

    public static   void regToWx(Context context) {
            // 通过WXAPIFactory工厂，获取IWXAPI的实例
            api = WXAPIFactory.createWXAPI(App.context, APP_ID, true);

            // 将应用的appId注册到微信
            api.registerApp(APP_ID);

            //建议动态监听微信启动广播进行注册到微信
            context.registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    // 将该app注册到微信
                    api.registerApp(APP_ID);
                }
            }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));
    }

    public  static  void   setText(String text) {
        if (isWeixinInstalled()) {
            //初始化一个 WXTextObject 对象，填写分享的文本内
            WXTextObject textObj = new WXTextObject();
            textObj.text = text;

// 用 WXTextObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage(textObj);
            msg.description = text;

// 构造一个Resp
            GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
// 将req的transaction设置到resp对象中，其中bundle为微信传递过来的Intent所带的内容，通过getExtras()方法获取
            resp.transaction = String.valueOf(System.currentTimeMillis());
            resp.message = msg;

//调用api接口，发送数据到微信
            api.sendResp(resp);
        }
    }

    //朋友圈
    public  static void   setfriend(String text, Bundle bundle){
        // 初始化一个 WXTextObject 对象
        WXTextObject textObj = new WXTextObject();
        textObj.text = text;

// 用 WXTextObject 对象初始化一个 WXMediaMessage 对象
        WXMediaMessage msg = new WXMediaMessage(textObj);
        msg.description = text;

// 构造一个Resp
        GetMessageFromWX.Resp resp = new GetMessageFromWX.Resp();
// 将req的transaction设置到resp对象中，其中bundle为微信传递过来的Intent所带的内容，通过getExtras()方法获取
        resp.transaction = new GetMessageFromWX.Req(bundle).transaction;
        resp.message = msg;

//调用api接口，发送数据到微信
        api. sendResp (resp) ;


    }

    public  static  void  add(){
        // send oauth request
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

//判断是否安装微信
    public static   boolean isWeixinInstalled(){
     boolean  installed=   api.isWXAppInstalled();
     if (!installed){
         Toast.makeText(App.context,"请先安装微信",Toast.LENGTH_LONG).show();
     }
     return  installed;
    }


}
