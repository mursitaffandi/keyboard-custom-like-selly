package com.inspiraspace.jokulid.keylogger.Emoji.EmojiHelper;

/**
 * Created by arf on 1/9/18.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.keylogger.Emoji.Emojicon;
import com.inspiraspace.jokulid.keylogger.Emoji.People;

import java.util.Arrays;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 * @author Ankush Sachdeva (sankush@yahoo.co.in)
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */
public class EmojiconGridView {
    public View rootView;
    EmojiconsPopup mEmojiconPopup;
    EmojiconRecents mRecents;
    Emojicon[] mData;
    private boolean mUseSystemDefault = false;


    public EmojiconGridView(Context context, Emojicon[] emojicons, EmojiconRecents recents, EmojiconsPopup emojiconPopup, boolean useSystemDefault) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        mEmojiconPopup = emojiconPopup;
        rootView = inflater.inflate(R.layout.emojicon_grid, null);
        setRecents(recents);
        GridView gridView = (GridView) rootView.findViewById(R.id.Emoji_GridView);
        if (emojicons == null) {
            mData = People.DATA;
        } else {
            Object[] o = (Object[]) emojicons;
            mData = Arrays.asList(o).toArray(new Emojicon[o.length]);
        }
        EmojiAdapter mAdapter = new EmojiAdapter(rootView.getContext(), mData, useSystemDefault);
        mAdapter.setEmojiClickListener(new OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (mEmojiconPopup.onEmojiconClickedListener != null) {
                    mEmojiconPopup.onEmojiconClickedListener.onEmojiconClicked(emojicon);
                }
                if (mRecents != null) {
                    mRecents.addRecentEmoji(rootView.getContext(), emojicon);
                }
            }
        });
        gridView.setAdapter(mAdapter);
    }

    private void setRecents(EmojiconRecents recents) {
        mRecents = recents;
    }

    public interface OnEmojiconClickedListener {
        void onEmojiconClicked(Emojicon emojicon);
    }

}
