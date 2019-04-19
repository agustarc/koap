# Koap
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![JitPack](https://jitpack.io/v/AgustaRC/koap.svg)](https://jitpack.io/#AgustaRC/koap)
[![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![Build Status](https://travis-ci.org/AgustaRC/koap.svg?branch=master)](https://travis-ci.org/AgustaRC/koap)

A Kotlin library that makes Android SharedPreferences easier to use.  
You can use it as if you are using a typical data class. 

## Usage
**Note that** this library doesn't allow nullable property except ```ReadWriteSerializable``` delegator
* **Basics**  
Create a SharedPreferences instance and then pass it on to the parameter of the bind function
```kotlin
object AccountPreference : PreferenceHolder(name = "account") {
    var id: String by ReadWriteString()
    var name: String by ReadWriteString(default = "leopold")
    var age: Int by ReadWriteInt(caching = false)
    var token: String by ReadWriteString(key = "pref_token")
}

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Koap.bind(this, AccountPreference)
    }
}

if (AccountPreference.id.isEmpty()) {

}


if (AccountPreference.age > 30) {

}
```

* **Using predicate**
```kotlin
object AccountPreference : PreferenceHolder(name = "account", cacheStrategy = CacheStrategy.LAZY) {
    var id: String by ReadWriteString(predicate = { it.isNotEmpty() })
    var name: String by ReadWriteString(caching = false, predicate = { it.length > 2 && it.startsWith("leo")} )
    var age: Int by ReadWriteInt(predicate = { it > 0 })
    var token: String by ReadWriteString(predicate = { it.length > 10 })
    var imageUrl: String by ReadWriteString(predicate = { it.startsWith("http")} )
    var account: Account? by ReadWriteSerializable(type = inferType<Account>(), predicate = { it?.age ?: 0 > 30 })
}

data class Account(
    val id: String,
    val nick: String,
    val age: Int
)
```

* **Cache Strategy**
```kotlin
object AccountPreference : PreferenceHolder(name = "account", cacheStrategy = CacheStrategy.EAGER) {
    var id: String by ReadWriteString()
}

// Default cacheStrategy is CacheStrategy.LAZY
```

* **File creation mode**
```kotlin
object AccountPreference : PreferenceHolder(name = "account", mode = Context.MODE_PRIVATE) {
    var id: String by ReadWriteString()
}

// Default mode is Context.MODE_PRIVATE
```

* **Default SharedPreferences**
```kotlin
object AccountPreference : PreferenceHolder(name = "", default = true) {
    var id: String by ReadWriteString()
}
```  
  
  
## Testing
Just turn on the test mode.
```kotlin
Koap.isTestMode = true
```
When it is turned on, then all properties are acting just like normal properties.  
  
  
## Importing the Library
Add jitpack.io to your project's repositories:
```grooby
repositories {
    maven { url 'https://jitpack.io' }
}
```
Add the following dependency to your build.gradle file to use the latest version:
```groovy
implementation 'com.github.AgustaRC.koap:koap:1.0.0'
```  
  
  
## Gson serialization
To use Gson serializer, we need to add following dependency:
```grooby
implementation "com.github.AgustaRC.koap:koap-gson-serializer:1.0.0"
```
And specify GsonSerializer as Koap serializer:
```kotlin
Koap.serializer = GsonSerializer(Gson())
```
Since then, we can use all types, even one not supported by SharedPreference  
  
  
## Contributing to the Project
If you'd like to contribute code to this project you can do so through GitHub by forking the repository and generating a pull request.  

By contributing your code, you agree to license your contribution under the terms of the Apache License v2.0.  
  
  
## Developed By
* Leopold Baik - joongwon100@gmail.com  
  
  
## Reference
* [PreferenceHolder](https://github.com/MarcinMoskala/PreferenceHolder)  
  
  
## License

    Copyright 2019 Leopold Baik

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
