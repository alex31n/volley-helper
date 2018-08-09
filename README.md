# Volley Helper
A helper for Volley, for making http request with simple and easy ways...

Android volley is a networking library was introduced to make networking calls much easier, faster without writing tons of code. By default all the volley network calls works asynchronously, so we don’t have to worry about using asynctask anymore.

Volley comes with lot of features. Some of them are

- Request queuing and prioritization
- Effective request cache and memory management
- Extensibility and customization of the library to our needs
- Cancelling the requests

 &nbsp;
 &nbsp;
 

## Installation
Add dependency

```
dependencies {
        
        implementation 'com.ornach.volleyhelper:volley-helper:1.0.2'  // for android studio 3.+
		
        compile 'com.ornach.volleyhelper:volley-helper:1.0.2'         // for android studio 2.3 or older
}
```

 
&nbsp;
&nbsp;
## Usage
Basic code
```
RequestHelper.newInstance(context).httpResponseWithProgress(Method.GET, url, new Listener() {
            @Override
            public void onSuccess(String response) {
                Log.d("TAG", "response "+response);
            }

            @Override
            public void onError(String error) {
                Log.d("TAG", "error "+error);
            }
        });
```
&nbsp;
&nbsp;
### Custom Configuration
**Step 1:**  Under app package, create a class name **AppController.java** and extend the class from Application and add the following code.
```
public class AppController extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Configuration config = new Configuration.Builder()
                .setCache(true)         
                .setTimeout(1000*60)    
                .build();
        VolleyController.setConfigure(config);

    }
}
```

**Step 2:**  Now open **AndroidManifest.xml** and add this **AppController** class in <application> tag using **android:name** property to execute the class automatically whenever app launches. Also add INTERNET permission as we are going to make network calls.
```
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.test.volleyhelper">

    <uses-permission android:name="android.permission.INTERNET"/>

    <application
        android:name=".AppController"
        android:icon="@mipmap/ic_launcher"
        android:theme="@style/AppTheme">
        
	<!-- all activities and other stuff -->
	
    </application>
</manifest>
```

&nbsp;
&nbsp;
### Custom Http request
```
RequestHelper helper = RequestHelper.newInstance(this);
helper.setParams(params);
helper.setHeaders(headerParams);
helper.setBody(body);
helper.httpResponseWithProgress(Method.POST, url, new Listener() {
    @Override
    public void onSuccess(String response) {
	Log.d("TAG", "response "+response);
    }

    @Override
    public void onError(String error) {
	Log.d("TAG", "error "+error);
    }
});
```


&nbsp;
&nbsp;
## License
    Copyright 2018 Alex
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and limitations under the License.
