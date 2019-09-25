package com.example.myapplication;

import com.example.baselibrary.BaseViewModel;

public class ImageModel implements BaseViewModel {

    TextModel model= new TextModel();

    public void requestUrls(){
        String key = model.getText();
    }

    @Override
    public void destroy() {

    }
}
