package io.fbr.fbmassenger.Adapters;

import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.fbr.fbmassenger.Model.MessageModel;
import io.fbr.fbmassenger.R;

/**
 * Created by farhad on 11/1/16.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    Context context;
    List<MessageModel> messageModelList= Collections.emptyList();

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void updateAdapterDate(List<MessageModel> data){
        messageModelList=data;
    }
    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.message_row, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        MessageModel message=messageModelList.get(position);

        holder.text_message.setText(message.body);
        switch (message.feel) {
            case "confused":
                holder.im_message.setImageResource(R.drawable.confused);
                break;
            case "happy":
                holder.im_message.setImageResource(R.drawable.happy);
                break;
            case "tongue":
                    holder.im_message.setImageResource(R.drawable.tongue);
                break;
            case "supprised":
                holder.im_message.setImageResource(R.drawable.surprised);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        ImageView im_message;
        TextView text_message;
        ImageButton ibtn_remove, ibtn_edit;

        public MessageViewHolder(View itemView) {
            super(itemView);
            im_message = (ImageView) itemView.findViewById(R.id.im_message);
            text_message = (TextView) itemView.findViewById(R.id.txt_message);
            ibtn_remove = (ImageButton) itemView.findViewById(R.id.remove_message);
            ibtn_edit = (ImageButton) itemView.findViewById(R.id.edit_message);

            ibtn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            ibtn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}