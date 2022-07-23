/*
 * Created by Raj Pratap Singh Jadon on 13/08/21, 5:36 PM
 * Copyright (c) 2021 . All rights reserved.
 * Last modified 09/08/21, 6:49 PM
 */

package com.raj.jadon.filepicker.customStartActivityResult

import androidx.activity.result.ActivityResult

interface StartActivityContracts {

    fun setGalleryResult(result: ActivityResult)
    fun setCamera(result: ActivityResult)
    fun setPdf(result: ActivityResult)
}