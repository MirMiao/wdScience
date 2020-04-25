package com.wd.tech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/3/25 16:18
 * @classname :
 */
public class TimeformatUtil  {
    //月日
    public  static  String  gettime(long time){
        SimpleDateFormat simpleDateFormat=new   SimpleDateFormat("MM-dd");
        Date date= new Date();
        String s=     simpleDateFormat.format(date);
        return  s;
    }
    //时分
    public  static  String  gettimehear(long time){
        SimpleDateFormat simpleDateFormat=new   SimpleDateFormat("HH:mm");
        Date date= new Date();
        String s=     simpleDateFormat.format(date);
        return  s;
    }
    //年月日
    public  static  String  gettimeyy(long time){
        SimpleDateFormat simpleDateFormat=new   SimpleDateFormat("yy-MM-dd");
        Date date= new Date();
        String s=     simpleDateFormat.format(date);
        return  s;
    }
}
