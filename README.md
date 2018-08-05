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

