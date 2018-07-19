package com.taowei.opinions.ui.fragment;


import android.support.v4.app.Fragment;

import java.util.HashMap;

public class FragmentFactory {


    public static final int F1 = 1;
    public static final int F2 = 2;
    public static final int F3 = 3;
    public static final int F4 = 4;
    public static final int F5 = 5;

    private static HashMap<Integer, Fragment> mFragments = new HashMap<>();

    public static Fragment createFragment(int fragmentName) {
        //从缓存中取出
        Fragment fragment = mFragments.get(fragmentName);

        if (fragment == null) {
            switch (fragmentName) {

                case F1:
                    fragment = MeFragment.newInstance("");
                    break;
                case F2:
                    fragment = NewsFragment.newInstance("");
                    break;
                case F3:
                    fragment = VideoFragment.newInstance("");
                    break;
                case F4:
                    fragment = BookFragment.newInstance("");
                    break;
                case F5:
                    fragment = MoneyFragment.newInstance("");
                    break;

            }
            mFragments.put(fragmentName, fragment);
        }

        return fragment;
    }
}
