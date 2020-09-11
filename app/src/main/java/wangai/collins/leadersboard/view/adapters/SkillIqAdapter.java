package wangai.collins.leadersboard.view.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import wangai.collins.leadersboard.R;
import wangai.collins.leadersboard.service.model.SkillIqs;
import wangai.collins.leadersboard.databinding.SkillItemListBinding;

public class SkillIqAdapter  extends RecyclerView.Adapter<SkillIqAdapter.MyViewHolder> {

    private static final String TAG = "HoursAdapter";
    List<? extends SkillIqs> skillsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final SkillItemListBinding binding;

        public MyViewHolder(@NonNull SkillItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SkillItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.skill_item_list, parent, false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setSkillIq(skillsList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return skillsList == null ? 0 : skillsList.size();
    }


    public void setSkillsList(final List<? extends SkillIqs> skillsList) {

        if (this.skillsList == null) {
            this.skillsList = skillsList;
            notifyItemRangeInserted(0, skillsList.size());
        } else {

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return SkillIqAdapter.this.skillsList.size();
                }

                @Override
                public int getNewListSize() {
                    return skillsList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return SkillIqAdapter.this.skillsList.get(oldItemPosition).name.equals(skillsList.get(newItemPosition).name);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    SkillIqs latest = skillsList.get(newItemPosition);
                    SkillIqs old = skillsList.get(oldItemPosition);
                    return latest.name.equals(old.name) && Objects.equals(latest.country, old.badgeUrl);
                }
            });

            this.skillsList = skillsList;
            result.dispatchUpdatesTo(this);
        }
    }
}

