import 'dart:async';

import 'package:flutter/services.dart';

class Fluttertoast {
  static const MethodChannel _channel = const MethodChannel('fluttertoast');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> getToast(String message) async {
    await _channel.invokeMethod("getToast", {"message": message});
  }
}
