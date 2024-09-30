package com.beam.instragramfeed.data.local.entity

import io.realm.kotlin.types.RealmObject

class PostObject : RealmObject {
    var id: Int = 0
    var title: String = ""
    var description: String = ""
    var imageUrl: String = ""
}
