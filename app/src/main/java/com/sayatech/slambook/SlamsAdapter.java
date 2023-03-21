package com.sayatech.slambook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SlamsAdapter extends RecyclerView.Adapter<SlamsAdapter.ViewHolder>{

    private final SlamRecyclerViewInterface slamRecyclerViewInterface;
    ArrayList<SlamsModel> arrSlams ;

    Context context;

    SlamsAdapter(Context context, ArrayList<SlamsModel> arrSlams, SlamRecyclerViewInterface slamRecyclerViewInterface){
        this.context = context;
        this.arrSlams =arrSlams;
        this.slamRecyclerViewInterface = slamRecyclerViewInterface;
    }

    public void setFilteredList(ArrayList<SlamsModel> filteredList){
        arrSlams = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.slams_row, parent,false);
        ViewHolder viewHolder = new ViewHolder(view, slamRecyclerViewInterface);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textName.setText(arrSlams.get(position).goodName);
        holder.textNickName.setText(arrSlams.get(position).knownAs);
        holder.textBornOn.setText(arrSlams.get(position).bornOn);

        holder.cardViewSlam.startAnimation(AnimationUtils.loadAnimation(
                holder.itemView.getContext(), R.anim.recycler_anim ));
    }

    @Override
    public int getItemCount() {
        return arrSlams.size();
    }


    public void setItems(ArrayList<SlamsModel> arrSlams){
        this.arrSlams = arrSlams;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        final CardView cardViewSlam;
        TextView textName, textNickName, textBornOn;
        LinearLayout llRow;

        public ViewHolder(View itemView, SlamRecyclerViewInterface slamRecyclerViewInterface) {

            super(itemView);
            textName =itemView.findViewById(R.id.slamName);
            textNickName= itemView.findViewById(R.id.slamNickName);
            textBornOn = itemView.findViewById(R.id.slamBornOn);
            llRow = itemView.findViewById(R.id.firstRow);
            cardViewSlam = itemView.findViewById(R.id.cardViewSlam);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (slamRecyclerViewInterface!= null){
                        int pos = getAdapterPosition();

                        if (pos!= RecyclerView.NO_POSITION){
                            slamRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(view -> {
                if (slamRecyclerViewInterface!= null){
                    int pos = getAdapterPosition();

                    if (pos!= RecyclerView.NO_POSITION){
                        slamRecyclerViewInterface.onItemLongClick(pos);
                    }
                }
                return true;
            });
        }
    }
}
