package com.example.myapplication.multi;

import com.blankj.utilcode.util.ToastUtils;
import com.example.baselibrary.DoubleClickObservableTransformer;
import com.example.myapplication.R;
import com.example.myapplication.databinding.BinderStudent2Binding;
import com.example.myapplication.databinding.BinderStudentBinding;
import com.jakewharton.rxbinding3.view.RxView;

public class StudentViewBinder2 extends BaseViewBinder<Student, BinderStudent2Binding> {
    @Override
    protected int getLayout() {
        return R.layout.binder_student2;
    }

    @Override
    void onBindView(ViewHolder<BinderStudent2Binding> viewHolder, Student student) {
        viewHolder.viewDataBinding.setStudent(student);

        addDisposable(
                RxView.clicks(viewHolder.viewDataBinding.parent)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> ToastUtils.showShort(student.toString()))

        );
    }
}
