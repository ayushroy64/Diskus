package com.example.diskus.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.diskus.Models.AllMethods;
import com.example.diskus.Models.Message;
import com.example.diskus.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageAdapterViewHolder> {

    Context context;
    List<Message> messages;
    DatabaseReference messagedb;

    public MessageAdapter(Context context, List<Message> messages,DatabaseReference messagedb){
        this.context = context;
        this.messages = messages;
        this.messagedb = messagedb;

    }

    @NonNull
    @Override
    public MessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_message,viewGroup,false);
        return new MessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapterViewHolder messageAdapterViewHolder, int i) {
        Message message = messages.get(i);

        if(message.getName().equals(AllMethods.name)){
            messageAdapterViewHolder.tvTitle.setText("You: "+ message.getMessage());
            messageAdapterViewHolder.tvTitle.setGravity(Gravity.START);
            messageAdapterViewHolder.l1.setBackgroundColor(Color.parseColor("FFFFFF"));
        } else{
            messageAdapterViewHolder.tvTitle.setText(message.getName() + ": " + message.getMessage());
            messageAdapterViewHolder.imgDelete.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MessageAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageButton imgDelete;
        LinearLayout l1;



        public MessageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            imgDelete = (ImageButton)itemView.findViewById(R.id.imgDel);
            l1 = (LinearLayout)itemView.findViewById(R.id.l1Message);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messagedb.child(messages.get(getAdapterPosition()).getKey()).removeValue();
                }
            });
        }
    }
}
