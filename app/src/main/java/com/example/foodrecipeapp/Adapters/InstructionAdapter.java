package com.example.foodrecipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipeapp.Models.InstructionResponse;
import com.example.foodrecipeapp.R;

import java.util.List;

public class InstructionAdapter extends RecyclerView.Adapter<InstructionAdapterViewHolder> {
    Context context;
    List<InstructionResponse> list;

    public InstructionAdapter(Context context, List<InstructionResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionAdapterViewHolder holder, int position) {
             holder.textview_instruction_name.setText(list.get(position).name);
             holder.recycler_instruction_step.setHasFixedSize(true);
             holder.recycler_instruction_step.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
             InstructionSteps steps = new InstructionSteps(context,list.get(position).steps);
             holder.recycler_instruction_step.setAdapter(steps);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
 class InstructionAdapterViewHolder extends RecyclerView.ViewHolder{

     TextView textview_instruction_name;
     RecyclerView recycler_instruction_step;
    public InstructionAdapterViewHolder(@NonNull View itemView) {
        super(itemView);

        recycler_instruction_step = itemView.findViewById(R.id.recycler_instruction_step);
        textview_instruction_name = itemView.findViewById(R.id.textview_instruction_name);
    }
}
