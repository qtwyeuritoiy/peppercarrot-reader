# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/nightlock/Android/Sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep class com.lsjwzh.widget.recyclerviewpager.**
-dontwarn com.lsjwzh.widget.recyclerviewpager.**

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn com.squareup.okhttp.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault

-dontwarn com.google.**
-dontwarn com.sun.jersey.**
-dontwarn io.netty.**
-dontwarn net.sf.cglib.**
-dontwarn okhttp3.**
-dontwarn org.bouncycastle.**
-dontwarn org.joda.time.**
-dontwarn org.junit.**
-dontwarn junit.**

-keep class .R
-keep class **.R$* {
    <fields>;
}