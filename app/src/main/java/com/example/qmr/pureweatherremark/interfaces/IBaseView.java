package com.example.qmr.pureweatherremark.interfaces;

import android.support.annotation.StringRes;

/**
 * Created by qmr on 2016/9/7.
 */
public interface IBaseView {

    void shortToast(CharSequence str);

    void shortToast(@StringRes int stringRes);

}
