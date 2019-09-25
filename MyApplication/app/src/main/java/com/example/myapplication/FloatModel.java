package com.example.myapplication;

import com.example.baselibrary.BaseViewModel;

public class FloatModel implements BaseViewModel {
    TextModel model= new TextModel();

    public void calcFloat(){
        String key = model.getText();
    }


    @Override
    public void destroy() {

    }
}
