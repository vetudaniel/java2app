package com.first.myapplication.views;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class GameView extends ViewModel {
    private MutableLiveData<List<String>> dataList = new MutableLiveData<>();
    private String preservedObject;

    public LiveData<List<String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> newDataList) {
        dataList.setValue(newDataList);
    }

    public String getPreservedObject() {
        return preservedObject;
    }

    public void setPreservedObject(String object) {
        this.preservedObject = object;
    }
}
