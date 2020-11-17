package ika.test.runningapp.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingViewModel extends ViewModel {

    private MutableLiveData<Integer> srcIndex = new MutableLiveData<>();
    private MutableLiveData<Integer> dstIndex = new MutableLiveData<>();

    private MutableLiveData<Integer> srcSpinnerIndex = new MutableLiveData<>();
    private MutableLiveData<Integer> dstSpinnerIndex = new MutableLiveData<>();

    public MutableLiveData<Integer> getSrcIndex() {
        return srcIndex;
    }

    public MutableLiveData<Integer> getDstIndex() {
        return dstIndex;
    }

    public MutableLiveData<Integer> getSrcSpinnerIndex() {
        return srcSpinnerIndex;
    }

    public MutableLiveData<Integer> getDstSpinnerIndex() {
        return dstSpinnerIndex;
    }

    public void setSrcSpinnerIndex(Integer srcSpinnerIndex) {
        this.srcSpinnerIndex.setValue(srcSpinnerIndex);
        this.srcIndex.setValue(srcSpinnerIndex);
    }

    public void setDstSpinnerIndex(Integer dstSpinnerIndex) {
        this.dstSpinnerIndex.setValue(dstSpinnerIndex);
        this.dstIndex.setValue(dstSpinnerIndex + 50);
    }

    public void setSrcIndex(Integer srcIndex) {
        this.srcIndex.setValue(srcIndex);
        this.srcSpinnerIndex.setValue(srcIndex);
    }

    public void setDstIndex(Integer dstIndex) {
        this.dstIndex.setValue(dstIndex);
        this.dstSpinnerIndex.setValue(dstIndex - 50);
    }
}
