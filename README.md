# FilePicker

#### This project aims to provide an ultimate and flexible image picking from Gallery, Camera and cropping experience as well as PDF picking from system.

#### For the Image Cropping [UCrop](https://github.com/Yalantis/uCrop) library being used.

#### For the Permission [Dexter](https://github.com/Karumi/Dexter) library being used.

# [![](https://jitpack.io/v/rajjadon/FilePicker.svg)](https://jitpack.io/#rajjadon/FilePicker)

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

   i Add dependencies in app level build.gradle file

   ```   
   dependencies {   
       implementation 'com.github.rajjadon:FilePicker:Tag'
   }

   ```

2. Initialize Library in to Application class

   ```   
   Injector.initInjectorInstance(this)
   ```
3. Register Result Registry on Activity like below

   ```   
   Injector.getInstance().startActivityContracts.resultRegistry = activityResultRegistry
   lifecycle.addObserver(Injector.getInstance().startActivityContracts)
   ```

4. Register Result Registry and add it in lifecycle Observer on Activity like below

   ```   
   Injector.getInstance().startActivityContracts.resultRegistry = activityResultRegistry
   lifecycle.addObserver(Injector.getInstance().startActivityContracts)
   
5. Remove register Result Registry Observer in OnDestroy of Activity like below

   ```   
    lifecycle.removeObserver(Injector.getInstance().startActivityContracts)
   ```
7. Implement OnResult Method Like below to get the Result

   ```   
   Injector.getInstance().startActivityContracts.onResultManager.startActivityCustomOnResult = this
   ```
8. Call following method as per need

   ```   
   i.   Injector.getInstance().imageAndFilePicker.openCamera() // For selecting image direct from Camera
   ii.  Injector.getInstance().imageAndFilePicker.openGallery() // For selecting image from Gallery
   iii. Injector.getInstance().imageAndFilePicker.pickPDFFile() // For Pic PDF file form file manager
   iv.  Injecto.getInstance().imageAndFilePicker.getDataFromActivityResult(
        resultECode: StartActivityForResultEnum,
        result: Intent,
        fragment: Fragment? = null,
        isCroppingEnable: Boolean = false,
    ) //  Use this function in OnResult it will return URI of selected image and PDF file. by passing true in isCroppingEnable argument you can enable the image Cropping as well
   
   v.   Injector.getInstance().imageAndFilePicker.getFileName(fileUri: Uri) // For get the File name by passing the File Uri.
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

