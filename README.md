# NewsAppKotlin
An app which fetches news from an open source news api (https://newsapi.org), having offline persistence and auto download using Work manager.


# ScreenShots


<img src = "https://github.com/adarshvris/NewsAppKotlin/blob/master/screenshot/news_app_landing_screen.jpg" width = 275 /> <img src = "https://github.com/adarshvris/NewsAppKotlin/blob/master/screenshot/news_list_screen.jpg" width = 275 /> <img src = "https://github.com/adarshvris/NewsAppKotlin/blob/master/screenshot/news_detail_screen.jpg" width = 275 />


# Tech Stack


* __Language__ : Kotlin
* __Architecture__ : MVVM
* __Retrofit__ : For Network calls
* __Coroutines__ : For background operations like fetching network response
* __Live Data__ : To notify view for change
* __Room Database__ : For offline persistence
* __Work Manager__ : To enqueue auto fetch data task
* __Dagger__ : For dependency injection

