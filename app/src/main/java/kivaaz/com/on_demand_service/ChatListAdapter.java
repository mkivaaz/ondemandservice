package kivaaz.com.on_demand_service;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import kivaaz.com.ondemandserviceslibrary.FirebaseChat.FirebaseChatMessage;

/**
 * Created by Muguntan on 1/31/2018.
 */

public class ChatListAdapter extends BaseAdapter {
    Context context;
    List<FirebaseChatMessage> data;

    public ChatListAdapter(Context context, List<FirebaseChatMessage> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
