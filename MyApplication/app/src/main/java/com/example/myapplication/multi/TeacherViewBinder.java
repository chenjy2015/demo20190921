package com.example.myapplication.multi;

import com.blankj.utilcode.util.ToastUtils;
import com.example.baselibrary.DoubleClickObservableTransformer;
import com.example.myapplication.R;
import com.example.myapplication.databinding.BinderStudentBinding;
import com.example.myapplication.databinding.BinderTeacherBinding;
import com.jakewharton.rxbinding3.view.RxView;

public class TeacherViewBinder extends BaseViewBinder<Teacher, BinderTeacherBinding> {
    @Override
    protected int getLayout() {
        return R.layout.binder_teacher;
    }

    @Override
    void onBindView(ViewHolder<BinderTeacherBinding> viewHolder, Teacher teacher) {
        viewHolder.viewDataBinding.setTeacher(teacher);
        addDisposable(
                RxView.clicks(viewHolder.viewDataBinding.parent)
                        .compose(new DoubleClickObservableTransformer())
                        .subscribe(o -> ToastUtils.showShort(teacher.toString()))

        );
    }
}
