package com.example.retrofit2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewHolderClass extends RecyclerView.ViewHolder {

    ConstraintLayout cardSingleDesign;

    ImageView ButtonEditItemView, ButtonDeleteItemView;
    TextView contents;

    public RecyclerViewHolderClass(@NonNull View itemView) {
        super(itemView);

        cardSingleDesign = itemView.findViewById(R.id.SingleView);
        ButtonEditItemView = itemView.findViewById(R.id.ItemEditButton);
        ButtonDeleteItemView = itemView.findViewById(R.id.ItemDeleteButton);
        contents = itemView.findViewById(R.id.TextViewForTheText);



    }
}
