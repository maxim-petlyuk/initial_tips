# Initial tips before using the app

Library was created for showing the initial tips for the user on the start of the app(e.g. highlighted button with the message text). 
There are several implementations of the animations or you can easily extend exist animation for your request

![](example.gif)

# Download

[![](https://jitpack.io/v/mpetlyuk/initial_tips.svg)](https://jitpack.io/#mpetlyuk/initial_tips)

## Gradle

#### Step 1:
Add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

#### Step 2:
Add the dependency
```groovy
dependencies {
        implementation 'com.github.mpetlyuk:initial_tips:$latest_version'
}
```