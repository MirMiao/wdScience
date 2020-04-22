package com.wd.tech.contract;

import com.wd.tech.base.mvp.IBaseModel;
import com.wd.tech.base.mvp.IBaseView;

/**
 * 时间 :2020/4/22  6:39
 * 作者 :苗恒
 * 功能 :
 */
  public interface IContract {
       interface IModel extends IBaseModel{
           void getBannerData(ModelCallBack modelCallBack);  //banner展示
          interface ModelCallBack{
              void success(Object o);
              void failur(Throwable throwable);
          }
       }
       interface IPresenter{
           void getBannerData();//banner展示
       }
       interface IView extends IBaseView{
           void success(Object o);
           void failur(Throwable throwable);
       }
}
