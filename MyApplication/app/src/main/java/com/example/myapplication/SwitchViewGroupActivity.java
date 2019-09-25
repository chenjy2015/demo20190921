package com.example.myapplication;

import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.SizeUtils;
import com.example.baselibrary.ui.BaseUIActivity;
import com.example.baselibrary.widget.BorderTextView;
import com.example.myapplication.databinding.ActivitySwitchViewGroupBinding;
import com.example.myapplication.viewmodel.LoginViewModel;

public class SwitchViewGroupActivity extends BaseUIActivity<ActivitySwitchViewGroupBinding, LoginViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_switch_view_group;
    }

    @Override
    public void init() {
        //初始化必须先手动调用 因为onclick事件回调会默认回调一次 而这时view还未初始化完成
        dataBinding.switchViewGroup.init();
        int padding = SizeUtils.dp2px(15);

        FrameLayout.LayoutParams showAllParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        showAllParams.gravity = Gravity.TOP;
        BorderTextView showAll = new BorderTextView(this);
        showAll.setLayoutParams(showAllParams);
        showAll.setText("show");
        showAll.setPadding(padding, padding, padding, padding);
        showAll.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        dataBinding.switchViewGroup.addView(showAll);

        FrameLayout.LayoutParams switchFirstParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        int margin = SizeUtils.dp2px(20);
        switchFirstParams.gravity = Gravity.RIGHT;
        BorderTextView switchFirst = new BorderTextView(this);
        switchFirst.setLayoutParams(switchFirstParams);
        switchFirst.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        switchFirst.setPadding(padding, padding, padding, padding);
        switchFirst.setText("first");
        dataBinding.switchViewGroup.addView(switchFirst);

        BorderTextView next = new BorderTextView(this);
        FrameLayout.LayoutParams nextParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        nextParams.gravity = Gravity.BOTTOM;
        next.setLayoutParams(nextParams);
        next.setPadding(padding, padding, padding, padding);
        next.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        next.setText("next");
        dataBinding.switchViewGroup.addView(next);

        FrameLayout previewLayout = new FrameLayout(this);
        FrameLayout.LayoutParams previewParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        previewParams.gravity = Gravity.CENTER;
        previewLayout.setPadding(padding, padding, padding, padding);
        previewLayout.setLayoutParams(previewParams);
        BorderTextView preview = new BorderTextView(this);
        preview.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        preview.setText("preview");
        previewLayout.addView(preview);
        dataBinding.switchViewGroup.addView(previewLayout);

        FrameLayout hideLayout = new FrameLayout(this);
        FrameLayout.LayoutParams hideParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        hideParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        hideLayout.setPadding(padding, padding, padding, padding);
        hideLayout.setLayoutParams(hideParams);
        BorderTextView hide = new BorderTextView(this);
        hide.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        hide.setText("hide");
        hideLayout.addView(hide);
        dataBinding.switchViewGroup.addView(hideLayout);

        BorderTextView last = new BorderTextView(this);
        FrameLayout.LayoutParams lastParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lastParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        last.setLayoutParams(lastParams);
        last.setPadding(padding, padding, padding, padding);
        last.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        last.setText("last");
        dataBinding.switchViewGroup.addView(last);

        showAll.setOnClickListener(v -> dataBinding.switchViewGroup.showAll());
        switchFirst.setOnClickListener(v -> dataBinding.switchViewGroup.switchFirst());
        next.setOnClickListener(v -> dataBinding.switchViewGroup.switchNext());
        preview.setOnClickListener(v -> dataBinding.switchViewGroup.switchPreview());
        hide.setOnClickListener(v -> dataBinding.switchViewGroup.hideAll());
        last.setOnClickListener(v -> dataBinding.switchViewGroup.switchLast());


    }

    @Override
    public void initEvent() {
//        addDisposable(
//                RxView.clicks(dataBinding.switchViewGroup.findChild(0)).subscribe(o -> dataBinding.switchViewGroup.showAll())
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.showFirst).subscribe(o -> dataBinding.switchViewGroup.switchFirst())
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.showPreview).subscribe(o -> dataBinding.switchViewGroup.switchPreview())
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.showNext).subscribe(o -> dataBinding.switchViewGroup.switchNext())
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.showId).subscribe(o -> dataBinding.switchViewGroup.show(dataBinding.border.getId()))
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.hideAll).subscribe(o -> dataBinding.switchViewGroup.hideAll())
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.hideId).subscribe(o -> dataBinding.switchViewGroup.hide(dataBinding.border.getId()))
//        );
//        addDisposable(
//                RxView.clicks(dataBinding.index).subscribe(o -> Log.d("SwitchViewGroup", "current index : " + dataBinding.switchViewGroup.getCurrentVisibleIndex()))
//        );
    }
}
