package wangai.collins.leadersboard.view.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import wangai.collins.leadersboard.service.model.SkillIqs;
import wangai.collins.leadersboard.view.adapters.SkillIqAdapter;
import wangai.collins.leadersboard.viewmodel.SkillIqViewModel;
import wangai.collins.leadersboard.databinding.FragmentSkillIqBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIqFragment#} factory method to
 * create an instance of this fragment.
 */
public class SkillIqFragment extends Fragment {
    private static final String TAG = "SkillIqFragment";

    private FragmentSkillIqBinding binding;
    private SkillIqAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSkillIqBinding.inflate(inflater,container,false);
        adapter = new SkillIqAdapter();
        binding.skillRecycler.setAdapter(adapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final SkillIqViewModel skillIqViewModel = ViewModelProviders.of(this).get(SkillIqViewModel.class);

        observeViewModel(skillIqViewModel);

    }

    private void observeViewModel(SkillIqViewModel skillIqViewModel) {
        // Update the list when the data changes
        skillIqViewModel.getSkillIqListObservable().observe(this, new Observer<List<SkillIqs>>() {
            @Override
            public void onChanged(List<SkillIqs> skillIqs) {
                binding.setIsLoading(false);
                adapter.setSkillsList(skillIqs);
            }
        });
    }
    }