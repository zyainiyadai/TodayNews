package com.zwhkj.todaynews.todaynews.presenter.base;

import android.content.Context;

public class BasePresenter<T> {
	protected Context context;
    private T mView;
	public BasePresenter(Context context) {
		this(context, null);
	}

    public BasePresenter() {
    }
	
	public BasePresenter(Context context,T mvpView) {
		attachView(mvpView);
		this.context = context;
	}

    public BasePresenter(T mvpView) {
        attachView(mvpView);
    }
	
    public void attachView(T mvpView) {
        this.mView = mvpView;
    }

    public void detachView() {
        this.mView = null;
    }

    public boolean checkViewAttached() {
        return mView != null;
    }

    public T getView() {
        return mView;
    }
}
