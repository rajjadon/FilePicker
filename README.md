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
   ImageAndFilePickerInjector.initInjectorInstance(this)
   ```
3. Register Result Registry in onCreate/OnViewCreated of Activity/Fragment by calling below method

   ```   
   imageAndFilePicker.registerResultRegistry(
            onResult = this,
            lifecycle = lifecycle,
            activityResultRegistry = activityResultRegistry
        )
   ```

4. Remove register Result Registry Observer in OnDestroy/OnDestroyView of Activity/Fragment like below

   ```   
    imageAndFilePicker.removeResultRegistry(lifecycle)
   ```
7. Call following method as per need

   ```   
   i.   imageAndFilePicker.openCamera() // For selecting image direct from Camera
   ii.  imageAndFilePicker.openGallery() // For selecting image from Gallery
   iii. imageAndFilePicker.pickDOCFile() // For Pic DOC form file manager
   iv.  imageAndFilePicker.getDataFromActivityResult(
        resultECode: StartActivityForResultEnum,
        result: Intent,
        fragment: Fragment? = null,
        isCroppingEnable: Boolean = false,
    ) //  Use this function in OnResult it will return URI of selected image and PDF file. by passing true in isCroppingEnable argument you can enable the image Cropping as well
   
   v.   getFileName(fileUri: Uri) // For get the File name by passing the File Uri.
   ```

## License

```   
   MIT License

   Copyright (c) 2022 Raj Pratap Singh Jadon

   Permission is hereby granted, free of charge, to any person obtaining a copy
   of this software and associated documentation files (the "Software"), to deal
   in the Software without restriction, including without limitation the rights
   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
   copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all
   copies or substantial portions of the Software.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
   SOFTWARE.
   
```
