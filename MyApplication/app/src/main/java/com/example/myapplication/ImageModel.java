package com.example.myapplication;

public class ImageModel implements IBaseModel {

    TextModel model= new TextModel();

    public void requestUrls(){
        String key = model.getText();
    }

    @Override
    public void onDestory() {

    }
}
