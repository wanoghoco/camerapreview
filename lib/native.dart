import "package:flutter/services.dart";
import "package:flutter/material.dart";
import "package:flutter/foundation.dart";

class Native extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return defaultTargetPlatform == TargetPlatform.android
        ? const AndroidView(
            viewType: "native",
            creationParams: <String, dynamic>{"size": 20},
            layoutDirection: TextDirection.ltr,
            creationParamsCodec: StandardMessageCodec(),
          )
        : Text("Widget Not Supported For " + defaultTargetPlatform.toString());
  }
}
