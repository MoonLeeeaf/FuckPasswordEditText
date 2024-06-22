package io.github.moonleeeaf.fuckpasswordedittext;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Hook implements IXposedHookLoadPackage {
        
    public void fuck(XC_LoadPackage.LoadPackageParam param, Class<?> clazz) throws Exception {
        XposedBridge.hookMethod(
            clazz.getMethod(
                "setInputType",
                int.class
            ),
            new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam mP) throws Throwable {
                    if (((int) mP.args[0]) == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD))
                        mP.args[0] = InputType.TYPE_CLASS_TEXT;
                }
            }
        );
    }
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam param) throws Throwable {
        fuck(param, EditText.class);
        fuck(param, MultiAutoCompleteTextView.class);
    }
   
}
