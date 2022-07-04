package com.example.myfirstapplication.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.databinding.ItemContainerRecentConversionBinding;
import com.example.myfirstapplication.listeners.ConversionListener;
import com.example.myfirstapplication.models.ChatMessage;
import com.example.myfirstapplication.models.User;

import java.util.List;

public class RecentConversionAdapter extends  RecyclerView.Adapter<RecentConversionAdapter.ConversionViewHolder> {

  private  final   List<ChatMessage> chatMessages;
  private ConversionListener conversionListener ;

    public RecentConversionAdapter(List<ChatMessage> chatMessages, ConversionListener conversionListener) {
        this.conversionListener = conversionListener;
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConversionViewHolder(
              ItemContainerRecentConversionBinding.inflate(
                      LayoutInflater.from(parent.getContext()),
                      parent,
                      false

              )

        );
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        holder.setData(chatMessages.get(position));

    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    class ConversionViewHolder extends RecyclerView.ViewHolder {

        ItemContainerRecentConversionBinding binding;

        public ConversionViewHolder(ItemContainerRecentConversionBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }


        void setData(ChatMessage chatMessage) {


            binding.imageProfileItem.setImageBitmap(getConversionImage(chatMessage.conversionImage));
            binding.textNameItem.setText(chatMessage.conversionName);
            binding.textRecentMessage.setText(chatMessage.message);
           binding.getRoot().setOnClickListener(v -> {

               User user = new User();
               user.id= chatMessage.conversionId;
               user.name = chatMessage.conversionName;
               user.image = chatMessage.conversionImage;
               conversionListener.onConversionClicked(user);


           } );




        }


    }


    private Bitmap getConversionImage(String encodeImage) {
        byte[] bytes = Base64.decode(encodeImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);


    }

}
