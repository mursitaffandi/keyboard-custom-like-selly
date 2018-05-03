package com.inspiraspace.jokulid.utils;

/**
 * Created by arf on 1/16/18.
 */

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.EditorInfo;

import java.util.List;

//import com.anysoftkeyboard.base.utils.Logger;
//import com.menny.android.anysoftkeyboard.BuildConfig;

public class IMEUtil {
    public static final int IME_ACTION_CUSTOM_LABEL = EditorInfo.IME_MASK_ACTION + 1;


    private static final String TAG = "ASK IMEUtils";

    /* Damerau-Levenshtein distance */

    /**
     * Remove duplicates from an array of strings.
     * <p/>
     * This soft_method will always keep the first occurrence of all strings at their position
     * in the array, removing the subsequent ones.
     */
    public static void removeDupes(final List<CharSequence> suggestions, List<CharSequence> stringsPool) {
        if (suggestions.size() < 2) return;
        int i = 1;
        // Don't cache suggestions.size(), since we may be removing items
        while (i < suggestions.size()) {
            final CharSequence cur = suggestions.get(i);
            // Compare each suggestion with each previous suggestion
            for (int j = 0; j < i; j++) {
                CharSequence previous = suggestions.get(j);
                if (TextUtils.equals(cur, previous)) {
                    removeSuggestion(suggestions, i, stringsPool);
                    i--;
                    break;
                }
            }
            i++;
        }
    }

    public static void tripSuggestions(List<CharSequence> suggestions, final int maxSuggestions, List<CharSequence> stringsPool) {
        while (suggestions.size() > maxSuggestions) {
            removeSuggestion(suggestions, maxSuggestions, stringsPool);
        }
    }

    private static void removeSuggestion(List<CharSequence> suggestions, int indexToRemove, List<CharSequence> stringsPool) {
        CharSequence garbage = suggestions.remove(indexToRemove);
        if (garbage instanceof StringBuilder) {
            stringsPool.add(garbage);
        }
    }

    public static int getImeOptionsActionIdFromEditorInfo(final EditorInfo editorInfo) {
        if ((editorInfo.imeOptions & EditorInfo.IME_FLAG_NO_ENTER_ACTION) != 0) {
            //IME_FLAG_NO_ENTER_ACTION:
            // Flag of imeOptions: used in conjunction with one of the actions masked by IME_MASK_ACTION.
            // If this flag is not set, IMEs will normally replace the "enter" key with the action supplied.
            // This flag indicates that the action should not be available in-line as a replacement for the "enter" key.
            // Typically this is because the action has such a significant impact or is not recoverable enough
            // that accidentally hitting it should be avoided, such as sending a message.
            // Note that TextView will automatically set this flag for you on multi-line text views.
            return EditorInfo.IME_ACTION_NONE;
        } else if (editorInfo.actionLabel != null) {
            return IME_ACTION_CUSTOM_LABEL;
        } else {
            // Note: this is different from editorInfo.actionId, hence "ImeOptionsActionId"
            return editorInfo.imeOptions & EditorInfo.IME_MASK_ACTION;
        }
    }
}
