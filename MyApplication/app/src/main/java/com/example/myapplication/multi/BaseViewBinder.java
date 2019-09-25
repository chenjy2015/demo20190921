package com.example.myapplication.multi;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.drakeet.multitype.ItemViewBinder;

import org.jetbrains.annotations.NotNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewBinder<P extends People, V extends ViewDataBinding> extends ItemViewBinder<P, BaseViewBinder.ViewHolder> {

    private CompositeDisposable mCompositeDisposable;


    abstract int getLayout();

    abstract void onBindView(ViewHolder<V> viewHolder, P p);

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull LayoutInflater layoutInflater, @NotNull ViewGroup viewGroup) {
        V viewDataBinding = DataBindingUtil.bind(layoutInflater.inflate(getLayout(), viewGroup, false));
        return new ViewHolder(viewDataBinding);
    }


    @Override
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, P p) {
        onBindView(viewHolder, p);
    }

    static class ViewHolder<V extends ViewDataBinding> extends RecyclerView.ViewHolder {

        public V viewDataBinding;

        public ViewHolder(@NonNull V viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }
    }


    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void clearDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NotNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        clearDisposable();
    }
}
