package com.example.myfirstapplication.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.databinding.ItemContainerSentMessageBinding;
import com.example.myfirstapplication.databinding.ItemContainerUserBinding;
import com.example.myfirstapplication.databinding.ItemContanierReceivedMessageBinding;
import com.example.myfirstapplication.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ChatMessage> chatMessages;
    private final Bitmap receiverProfileImage;
    private final String senderId;
    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;


    public ChatAdapter(List<ChatMessage> chatMessages, Bitmap receiverProfileImage, String senderId) {
        this.chatMessages = chatMessages;
        this.receiverProfileImage = receiverProfileImage;
        this.senderId = senderId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            return new sentMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false


                    )


            );


        } else {
            return new ReceivedMessageViewHolder(

                    ItemContanierReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false

                    )

            );


        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_SENT) {

            ((sentMessageViewHolder) holder).setData(chatMessages.get(position));


        } else {
            ((ReceivedMessageViewHolder) holder).setData(chatMessages.get(position), receiverProfileImage);


        }


    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessages.get(position).senderId.equals(senderId)) {

            return VIEW_TYPE_SENT;

        } else {

            return VIEW_TYPE_RECEIVED;
        }
    }

    static class sentMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerSentMessageBinding binding;

        sentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {

            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;


        }

        void setData(ChatMessage chatMessage) {

            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);


        }


    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {


        private final ItemContanierReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemContanierReceivedMessageBinding itemContanierReceivedMessageBinding) {


            super(itemContanierReceivedMessageBinding.getRoot());
            binding = itemContanierReceivedMessageBinding;


        }


        void setData(ChatMessage chatMessage, Bitmap receiverProfileImage) {
            binding.textMessageRecev.setText(chatMessage.message);
            binding.textDateTimeRecev.setText(chatMessage.dateTime);
            binding.imageprofileRecev.setImageBitmap(receiverProfileImage);


        }


    }


}
