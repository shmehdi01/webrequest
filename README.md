# webrequest
Use WebRequest for easy webservice GET,POST, MULTIPART request

Note : Make sure you have given INTERNET PERMISSION in manifest.xml

Step 1. Add the JitPack repository to your build file 

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.shmehdi01:webrequest:v1.0'
	}

How to use ?

1 .Create object of WebRequest.

    WebRequest webrequest = new WebRequest(context);

2. For Get Request

       webrequest.getRequest(String url,Callback callback);

3. For Post Request

       webrequest.postRequest(String url,Callback callback,Map<String,String> params);

4. For multipart Request

       webrequest.multipartRequest(String url,Callback callback,Map<String,String> params,String contentType, String fileKey, String fileName, Uri fileUri);

       
  String contentType = ContentType.IMAGE_JPEG;

  String contentType = ContentType.VIDEO_MP4;

 Override callbacks methods

      @override
      public void preExecute(String url){

           //add your code

      }

      @override
      public void onResponse(String response, String url){

           //add your code

      }

      @override
      public void onErrorResponse(VolleyError error, String url){

           //add your code

      }


