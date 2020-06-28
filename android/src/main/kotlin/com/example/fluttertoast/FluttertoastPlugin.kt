package com.example.fluttertoast

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

/** FluttertoastPlugin */
public class FluttertoastPlugin: FlutterPlugin, MethodCallHandler {

  var context: Context? = null
  
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    
    context = flutterPluginBinding.applicationContext;
    channel = MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "fluttertoast")
    channel.setMethodCallHandler(this);
  }

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "fluttertoast").also {
        it.setMethodCallHandler(FluttertoastPlugin())
      }
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {


    if(call.method=="getToast"){
      var message = call.argument<String>("message")
      Toast.makeText(this.context,message,Toast.LENGTH_LONG).show()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
