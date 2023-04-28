package com.example.test2.ModelClasses

import com.example.test2.ImageLinks

data class  volumeInfo (
    val title: String,
    val authors: ArrayList<String>,
    val imageLinks: ImageLinks,
    var description: String,
    var buyLink: String,
    var previewLink: String,
    var categories: ArrayList<String>,
//    val averageRating: Int
    )
 