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
        //高德：113.371721,23.125588 ，经度，-向左，纬度，-向下
        //百度：113.378416,23.132594
        //微调整过后：113.366601,23.128978
        s.startSet(113.366601,23.128978)
        return super.onCreateView(name, context, attrs)
    }
}
