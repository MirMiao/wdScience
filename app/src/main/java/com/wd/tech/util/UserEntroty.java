package com.wd.tech.util;
import android.widget.Toast;
import com.wd.tech.App;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/3/27 10:16
 * @classname :
 */
public class UserEntroty {
    public  static  boolean isphone(String phone){
      Pattern pattern=  Pattern.compile("^1[0-9]{10}$");
        Matcher matcher = pattern.matcher(phone);
        boolean matches = matcher.matches();
        if(matches==true){
            return  matches;
        }
        else {
            Toast.makeText(App.context,"输入手机号不合法", Toast.LENGTH_LONG).show();
        }
        return  false;
    }


    public  static  boolean ispwd(String pwd){
        Pattern pattern=  Pattern.compile("^[a-zA-Z0-9]{6,8}$");
        Matcher matcher = pattern.matcher(pwd);
        boolean matches = matcher.matches();
        if(matches==true){
            return  matches;
        }
        else {
            Toast.makeText(App.context,"输入手机密码不合法", Toast.LENGTH_LONG).show();
        }
        return  false;
    }



}
