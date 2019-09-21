package com.example.myapplication;

public class FloatModel implements IBaseModel {
    TextModel model= new TextModel();

    public void calcFloat(){
        String key = model.getText();
    }

    @Override
    public void onDestory() {

    }
}
