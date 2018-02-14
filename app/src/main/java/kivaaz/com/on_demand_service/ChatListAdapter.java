package kivaaz.com.on_demand_service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseChatMessage;

/**
 * Created by Muguntan on 1/31/2018.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatHolder> {

    Context context;
    List<FirebaseChatMessage> data;
    LayoutInflater inflater;
    ChatHolder holder;
    String userID;

    public ChatListAdapter(Context context, List<FirebaseChatMessage> data, String userID) {
        this.context = context;
        this.data = data;
        this.userID = userID;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.message_adapter,null);
        holder = new ChatHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        if(userID.equals(data.get(position).getMessageSender())){

        }else {
        }
        holder.message.setText(data.get(position).getMessage());
        holder.email.setText(data.get(position).getMessageSender());
        holder.date.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                data.get(position).getTimeStamp()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ChatHolder extends RecyclerView.ViewHolder{

        TextView message,email, date;
        LinearLayout itemGroup;

        public ChatHolder(View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message_text);
            email = itemView.findViewById(R.id.email);
            date = itemView.findViewById(R.id.date);
            itemGroup = itemView.findViewById(R.id.item_Group);
        }
    }
}
