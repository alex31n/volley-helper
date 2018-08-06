# Volley Helper
A helper for Volley, for making http request with simple and easy ways...

 &nbsp;
 &nbsp;
 

## Installation
Add dependency

```
dependencies {
        
        implementation 'com.ornach.volleyhelper:volley-helper:1.0'  // for android studio 3.+
		
        compile 'com.ornach.volleyhelper:volley-helper:1.0'         // for android studio 2.3 or older
}
```

 
&nbsp;
&nbsp;
## Usage
Basic code
```
RequestHelper.newInstance(context).httpResponseWithProgress(Request.Method.GET, url, new Listener() {
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
&nbsp;
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
