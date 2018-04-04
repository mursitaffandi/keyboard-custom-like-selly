package com.inspiraspace.jokulid.keylogger.Emoji.EmojiHelper;

/**
 * Created by arf on 1/9/18.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.keylogger.Emoji.Emojicon;

import java.util.List;

/**
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */

class EmojiAdapter extends ArrayAdapter<Emojicon> {
    private boolean mUseSystemDefault = false;
    EmojiconGridView.OnEmojiconClickedListener emojiClickListener;


    public EmojiAdapter(Context context, List<Emojicon> data, boolean useSystemDefault) {
        super(context, R.layout.emojicon_items, data);
        mUseSystemDefault = useSystemDefault;
    }

    public EmojiAdapter(Context context, Emojicon[] data, boolean useSystemDefault) {
        super(context, R.layout.emojicon_items, data);
        mUseSystemDefault = useSystemDefault;
    }


    public void setEmojiClickListener(EmojiconGridView.OnEmojiconClickedListener listener){
        this.emojiClickListener = listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = View.inflate(getContext(), R.layout.emojicon_items, null);
            ViewHolder holder = new ViewHolder();
            holder.icon = v.findViewById(R.id.emojicon_icons);
            holder.icon.setUseSystemDefault(mUseSystemDefault);

            v.setTag(holder);
        }

        Emojicon emoji = getItem(position);
        ViewHolder holder = (ViewHolder) v.getTag();
        holder.icon.setText(emoji.getEmoji());
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emojiClickListener.onEmojiconClicked(getItem(position));
            }
        });

        return v;
    }

    class ViewHolder {
        EmojiconTextView icon;
    }
}
