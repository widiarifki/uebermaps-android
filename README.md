# Uebermaps Android

The goal is to create an Android client app mimicking [Uebermaps Web][UebermapsWebL] and [UebermapsIOSL][2]

### API
Provided by Uebermaps API ([Link][UebermapsAPIL])

### Jetpack Components
- [Hilt Dependency Injection (v2.31.2-alpha)][HiltL]
- [Room DB (v2.2.6)][RoomL]
- [Data Binding][DataBindingL]
- [Paging 3 Library (v3.0.0-beta01)][Paging3L]
- [Navigation][Paging3L]
- etc

### Third Party Components
- Network Request: [Retrofit 2][RetrofitL], [OkHttp][OkHttpL], [Gson Converter][GsonL]
- Image Loader: [Glide][GlideL]
- Size Unit: [Intuit SDP][SdpL]

### Folder structure
```
app
├── base: contains abstract classes that will frequently implemented by another app components (i.e: activity)
├── data: contains code concerns about data, its modelling, its access
|   ├── local: contains code concerns about accessing to local data
|   ├── model: contains entity model classes
|   └── network: contains web API interface & response wrapper class
├── di: contains dependency injection code, defining modules where another code will frequently depends on
├── presentation: contains code concerns about how the app will be presented. Categorized/foldered by single UI screen, one folder will define the activity and the viewmodel attached to the activity
├── repository: contains code concerns about how the presentation/view will interact with data
├── helper: contains helper classes and objects
└── BaseApplication.kt
```

### Screenshot
![Home](https://raw.githubusercontent.com/widiarifki/uebermaps-android/master/_extras/1.jpg =300x)
![Detail Map 1](https://raw.githubusercontent.com/widiarifki/uebermaps-android/master/_extras/2.jpg =300x)
![Detail Map 2](https://raw.githubusercontent.com/widiarifki/uebermaps-android/master/_extras/3.jpg =300x)
![Account](https://raw.githubusercontent.com/widiarifki/uebermaps-android/master/_extras/4.jpg =300x)
![Login](https://raw.githubusercontent.com/widiarifki/uebermaps-android/master/_extras/5.jpg =300x)

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [UebermapsWebL]: <https://uebermaps.com/>
   [UebermapsIOSL]: <https://itunes.apple.com/app/id929047930/>
   [UebermapsAPIL]: <https://uebermaps.com/api/v2>
   [RetrofitL]: <https://github.com/square/retrofit>
   [OkHTTPL]: <https://github.com/square/okhttp>
   [GsonL]: <https://github.com/google/gson>
   [FrescoL]: <https://github.com/facebook/fresco>
   [AndroidYTL]: <https://github.com/PierfrancescoSoffritti/android-youtube-player>
   [TmdbL]: <https://www.themoviedb.org/documentation/api>
   [HiltL]: <https://dagger.dev/hilt/>
   [Paging3L]: <https://developer.android.com/topic/libraries/architecture/paging/v3-overview>
   [DataBindingL]: <https://developer.android.com/topic/libraries/data-binding>
   [RoomL]: <https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase>
   [GlideL]: <https://bumptech.github.io/glide/>
   [SdpL]: <https://github.com/intuit/sdp>