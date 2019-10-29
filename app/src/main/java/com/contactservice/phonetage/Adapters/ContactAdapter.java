package com.contactservice.phonetage.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.contactservice.phonetage.Models.Model_Log;
import com.contactservice.phonetage.R;
import com.contactservice.phonetage.Utils.CustomUtils;

import java.util.ArrayList;



public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private ArrayList<Model_Log> array_contat;
    private Context m_context;

    public  ContactAdapter(Context context, ArrayList<Model_Log> list){
        m_context = context;
        array_contat = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(m_context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_log, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_contactnumber.setText(array_contat.get(position).getCallNumber());
        String str_date = CustomUtils.getDate(Long.valueOf(array_contat.get(position).getCalltime()));
        holder.txt_contactdate.setText(str_date);
    }


    @Override
    public int getItemCount() {
        return array_contat.size();
    }

    //viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView img_contacttype;
        public TextView  txt_contactname;
        public TextView  txt_contactnumber;
        public TextView  txt_contactdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_contactnumber = itemView.findViewById(R.id.contact_number);
            txt_contactdate = itemView.findViewById(R.id.contact_date);
        }
    }
}
