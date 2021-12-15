package com.example.blablacar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blablacar.R;
import com.example.blablacar.databinding.StopShortItemBinding;
import com.example.blablacar.model.response.StopDetail;

import java.util.List;

public class StopListAdapter extends RecyclerView.Adapter<StopListAdapter.StopViewHolder> {
    private List<StopDetail> stopDetails;
    private StopSelection stopSelection;

    public StopListAdapter(StopSelection stopSelection) {
        this.stopSelection = stopSelection;
    }

    public void setStopDetails(List<StopDetail> stopDetails) {
        this.stopDetails = stopDetails;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public StopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        StopShortItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.stop_short_item, parent, false);
        return new StopViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StopViewHolder holder, int position) {
        holder.bindType(stopDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return stopDetails != null ? stopDetails.size() : 0;
    }

    public class StopViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final StopShortItemBinding itemBinding;

        public StopViewHolder(@NonNull StopShortItemBinding binding) {
            super(binding.getRoot());
            itemBinding = binding;
            itemBinding.getRoot().setOnClickListener(this);
        }

        public void bindType(StopDetail value) {
            itemBinding.setStop(value);
            itemBinding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            if(stopSelection!=  null){
                stopSelection.onStopClick(stopDetails.get(getAdapterPosition()));
            }
        }
    }
    public interface StopSelection{
        void onStopClick(StopDetail detail);
    }
}
