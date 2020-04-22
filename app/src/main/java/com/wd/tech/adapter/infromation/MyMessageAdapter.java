package com.wd.tech.adapter.infromation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wd.tech.view.fragment.ContactListFragment;
import com.wd.tech.view.fragment.MessageListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴健
 * Class :1708A
 * @description:
 * @date :2020/4/17 16:21
 * @classname :
 */
public class MyMessageAdapter extends FragmentPagerAdapter {
    List<Fragment> list=new ArrayList<>();
    String []title={"消息","联系人"};
    public MyMessageAdapter(@NonNull FragmentManager fm) {
        super(fm);
        list.add(new MessageListFragment());
        list.add(new ContactListFragment());

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
