# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

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

### OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

### Picasso
-dontwarn com.squareup.okhttp.**

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*

# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform

-keep class com.crashlytics.** { *; }

-dontwarn com.google.errorprone.annotations.**

-keepclassmembers class **.PaperParcel* {
  static void writeToParcel(...);
}
-keepnames class **.PaperParcel*
-keepnames @paperparcel.PaperParcel class *

-keep class me.zhanghai.android.materialprogressbar.** { *; }

-keepclassmembers class ** {
  @com.squareup.moshi.FromJson *;
  @com.squareup.moshi.ToJson *;
}

-dontwarn retrofit.**
-keep class retrofit.** { *; }

-keep class com.github.mikephil.charting.** { *; }
-keep class io.reactivex.** { *; }

-keep class cryptomarket.eoinahern.ie.cryptomarket.data.models.** { *;}
-keep class cryptomarket.eoinahern.ie.cryptomarket.UI.views.** { *;}

-dontwarn sun.misc.Unsafe
-dontwarn org.w3c.dom.bootstrap.DOMImplementationRegistry


