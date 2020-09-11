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
import wangai.collins.leadersboard.service.model.Hours;
import wangai.collins.leadersboard.databinding.HoursItemListsBinding;

public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.MyViewHolder> {

private static final String TAG = "HoursAdapter";
        List<? extends Hours> hoursList;

public HoursAdapter(){ }
public class MyViewHolder extends RecyclerView.ViewHolder{

    final HoursItemListsBinding binding;
    public MyViewHolder(@NonNull HoursItemListsBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HoursItemListsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.hours_item_lists, parent,false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.setHoursCount(hoursList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return hoursList ==null? 0 : hoursList.size();
    }



    public void setHoursList(final List<? extends Hours> hoursList){

        if (this.hoursList==null){
            this.hoursList = hoursList;
            notifyItemRangeInserted(0, hoursList.size());
        }else {

            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return HoursAdapter.this.hoursList.size();
                }

                @Override
                public int getNewListSize() {
                    return hoursList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return HoursAdapter.this.hoursList.get(oldItemPosition).name.equals(hoursList.get(newItemPosition).name);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Hours latest =hoursList.get(newItemPosition);
                    Hours old = hoursList.get(oldItemPosition);
                    return latest.name.equals(old.name) && Objects.equals(latest.country, old.badgeUrl);
                }
            });

            this.hoursList = hoursList;
            result.dispatchUpdatesTo(this);
        }
    }


}

