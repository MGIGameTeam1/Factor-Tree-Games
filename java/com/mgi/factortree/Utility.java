package com.mgi.factortree;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Achmad Siddik on 13/12/2016.
 */

public class Utility {
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String sound;
    public void setSettingSound(Context context,String status){
        sp= PreferenceManager.getDefaultSharedPreferences(context);
        editor=sp.edit();
        editor.putString("xSound",status);
        editor.commit();
    }

    public void setSettingBGM(Context context,String status){
        sp= PreferenceManager.getDefaultSharedPreferences(context);
        editor=sp.edit();
        editor.putString("xBGM",status);
        editor.commit();
    }

    public boolean isSound(Context context){
        sp=PreferenceManager.getDefaultSharedPreferences(context);
        String value=null;
        sound=sp.getString("xSound",value);
        if(sound!=null){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isBGM(Context context){
        sp=PreferenceManager.getDefaultSharedPreferences(context);
        String value=null;
        sound=sp.getString("xBGM",value);
        if(sound!=null){
            return true;
        }
        else{
            return false;
        }
    }
}
