package com.wd.tech.base.mvp;

import java.lang.ref.WeakReference;


public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView> {

    protected M model;

    protected WeakReference<V> weakReference;

    public BasePresenter(){
        model = initModel();
    }

    //绑定 View
    public void attachView(V v){
        weakReference = new WeakReference<>(v);
    }

    //解绑View
    public void detachView(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference =null;
        }
    }
    //得到弱引用对象
    public V getView(){
        return weakReference.get();
    }

    //交给子类实先
    protected abstract M initModel();

}
