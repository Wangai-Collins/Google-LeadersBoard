package wangai.collins.leadersboard.view.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import wangai.collins.leadersboard.view.ui.HoursFragment;
import wangai.collins.leadersboard.view.ui.SkillIqFragment;

public class PagerAdapter  extends FragmentPagerAdapter {

    int mNumOfTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.mNumOfTabs = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HoursFragment();
            case 1: return new SkillIqFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

