package com.example.myapplication.multi;

import com.blankj.utilcode.util.ToastUtils;
import com.example.baselibrary.DoubleClickObservableTransformer;
import com.example.myapplication.R;
import com.example.myapplication.databinding.BinderStudentBinding;
import com.jakewharton.rxbinding3.view.RxView;

public class StudentViewBinder extends BaseViewBinder<Student, BinderStudentBinding> {
    @Override
    protected int getLayout() {
        return R.layout.binder_student;
    }

    @Override
    void onBindView(ViewHolder<BinderStudentBinding> viewHolder, Student student) {
        viewHolder.viewDataBinding.setStudent(student);
        addDisposable(
                RxView.clicks(viewHolder.viewDataBinding.parent)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> ToastUtils.showShort(student.toString()))

        );
    }
}
