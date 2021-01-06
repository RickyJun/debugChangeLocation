package com.my.mytest

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import io.flutter.embedding.android.FlutterActivity
import com.my.mytest.test.SetLocation;
class MainActivity: FlutterActivity(){
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        System.out.println("onCreate in")
        var s = SetLocation(this)
        s.init_location()
        s.startSet(113.378539,23.132608)
        return super.onCreateView(name, context, attrs)
    }
}
