package com.wd.tech.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wd.tech.view.messagefragment.AddCrowdFragment;
import com.wd.tech.view.messagefragment.AddFriendFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/18 19:42
 * @classname :
 */
public class MyAddHomeAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();
    String[] title={"找人","找群"};
    public MyAddHomeAdapter(@NonNull FragmentManager fm) {
        super(fm);
        fragments.add(new AddFriendFragment());
        fragments.add(new AddCrowdFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
