package com.inspiraspace.jokulid.fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TimePicker;
import android.widget.Toast;

import com.inspiraspace.jokulid.R;
import com.inspiraspace.jokulid.utils.AlarmReceiver;

import java.util.Calendar;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsDailyReminderFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
    @BindString(R.string.key_notif_daily)
    String notif_daily;

Preference timePreference;
    AlarmReceiver alarmReceiver = new AlarmReceiver();
    final Calendar currentDate = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_list);
        ButterKnife.bind(this, getActivity());
timePreference = findPreference(getString(R.string.key_notif_daily_time));

        // triger callback on changes each item setting
        findPreference(notif_daily).setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String key_pref = preference.getKey().toString().toString().toString().toString();
        final String messageToast;
        boolean switch_state = (boolean) o;
        if (key_pref.equals(notif_daily)) {
            if (switch_state) {
                new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        alarmReceiver.setRepeatingAlarm(getActivity(), hourOfDay, minute);

                        StringBuilder time = new StringBuilder()
                                .append(String.valueOf(hourOfDay))
                                .append(":")
                                .append(String.valueOf(minute));

                        StringBuilder builder = new StringBuilder()
                                .append(getString(R.string.message_set_daily_reminder_on))
                                .append(" ")
                                .append(time);

                        timePreference.setSummary(time.toString());
                        Toast.makeText(getActivity(), builder.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();

            } else {
                alarmReceiver.cancelAlarm(getActivity());
                messageToast = getResources().getString(R.string.message_set_daily_reminder_off);
                Toast.makeText(getActivity(), messageToast, Toast.LENGTH_SHORT).show();
            }

            return true;
        }
        return false;
    }
}
