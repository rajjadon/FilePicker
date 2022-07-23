# FilePicker

#### This project aims to provide an ultimate and flexible image picking from Gallery, Camera and cropping experience as well as PDF picking from system.

#### For the Image Cropping i have used UCrop library.

# Tag [![](https://jitpack.io/v/rajjadon/FilePicker.svg)](https://jitpack.io/#rajjadon/FilePicker)

# Usage

*For a working implementation, please have a look at the Sample Project - sample*

1. Include the library as a local library project in setting.gradle.

   ```
   allprojects {
      repositories {
         jcenter()
         maven { url "https://jitpack.io" }
      }
   }
   ```

   i Add Dagger dependencies in project level build.gradle file

        ```
        dependencies {
            classpath("com.google.dagger:hilt-android-gradle-plugin:2.40.5")
        }
    
       allprojects{
        ext {
            // dagger hilt
            hilt_version = '2.40.5'
            hilt_jetpack_version = '1.0.0-alpha03'
            hilt_jetpack_navigation_version = '1.0.0'
        }
       }

       ```

   ii. The below dependencies in app level build.gradle file

   ```
   plugin{ 
      id("dagger.hilt.android.plugin")
   }
   
   dependencies {
      // Hilt dependencies
       api "com.google.dagger:hilt-android:$hilt_version"
       kapt "com.google.dagger:hilt-android-compiler:$hilt_version"
   
       implementation 'com.github.rajjadon:FilePicker:Tag'
   }

   ```

## License

    Copyright 2022, Raj Pratap Singh Jadon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

