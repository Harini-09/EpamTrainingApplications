package com.epam.strategy;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PhoneCameraApp cameraPlusApp = new CameraPlusApp();
        PhoneCameraApp phoneCameraApp = new BasicCameraApp(cameraPlusApp);
        
		/*
		 * phoneCameraApp.takeAPhoto(); phoneCameraApp.editPhoto();
		 * phoneCameraApp.savePhoto();
		 */
        phoneCameraApp.sharePhoto("email");
        phoneCameraApp.sharePhoto("Instagram");
    }
}
