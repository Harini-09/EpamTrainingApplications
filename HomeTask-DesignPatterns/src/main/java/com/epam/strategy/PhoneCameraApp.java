package com.epam.strategy;

public abstract class PhoneCameraApp {
	abstract void editPhoto();
	
	public void takePhoto() {
		System.out.println("Taking a photo");		
	}

	
	public void savePhoto() {
		System.out.println("Saving the photo");
		
	}

	public void sharePhoto(ShareMode mode) {
		mode.share();
	}
}
