package com.example.jaehyolim.viewmodel.view;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

abstract public class BaseViewModelFragment<T extends ViewModel> extends Fragment {

    public abstract T viewModel();


    public T mViewModel;
    private MaterialDialog materialDialog;
    private DisposableLifecycleObserver observer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lifecycle lifecycle = this.getLifecycle();
        observer = new DisposableLifecycleObserver(getLifecycle());
        lifecycle.addObserver(observer);
        mViewModel = viewModel();

    }

    @Override
    public void onStart() {
        super.onStart();
        observer.enable();
    }

    public CompositeDisposable getCompositeDisposable(){
        return observer.getDisposable();
    }
    @Override
    public void onStop() {
        super.onStop();
        if (materialDialog != null) {
            materialDialog.dismiss();
        }
    }

    public void addDisposable(Disposable disposable) {
        if (observer != null) {
            observer.addDisposable(disposable);
        }


    }




}