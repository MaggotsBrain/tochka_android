package social.tochka.android.construct;

import android.graphics.Bitmap;

import java.util.List;

public class MerchItem {
    private List<Bitmap> blackList, greenList, greyList, redList;

    public List<Bitmap> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<Bitmap> blackList) {
        this.blackList = blackList;
    }

    public List<Bitmap> getGreenList() {
        return greenList;
    }

    public void setGreenList(List<Bitmap> greenList) {
        this.greenList = greenList;
    }

    public List<Bitmap> getGreyList() {
        return greyList;
    }

    public void setGreyList(List<Bitmap> greyList) {
        this.greyList = greyList;
    }

    public List<Bitmap> getRedList() {
        return redList;
    }

    public void setRedList(List<Bitmap> redList) {
        this.redList = redList;
    }
}
