package com.emirk.turkcellandroidmusicplayer.data.repository

import com.emirk.turkcellandroidmusicplayer.domain.repository.FirebaseRepository
import com.emirk.turkcellandroidmusicplayer.domain.ui_model.MusicCategory
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
) : FirebaseRepository {
    override suspend fun getMusicCategories(): List<MusicCategory>? {
        val firestore = Firebase.firestore
        var list = listOf<MusicCategory>()
        val documentSnapshot =
            firestore.collection("MusicCategories").document("list").get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val musicCategoryList = documentSnapshot.toObject(object :
                            TypeToken<List<MusicCategory>>() {}.type as Class<Any>)
                        list = musicCategoryList as List<MusicCategory>
                    } else {
                        // Belirtilen belge bulunamadı
                    }
                }
                .addOnFailureListener { exception ->
                    // Çekme işlemi başarısız oldu, hata mesajını burada ele alabilirsiniz
                }
        return if (list.isNullOrEmpty()) {
            list
        } else {
            null
        }
    }

    override suspend fun saveMusicCategories(musicCategories: List<MusicCategory>?) {
        val firestore = Firebase.firestore
        if (musicCategories != null) {
            firestore.collection("MusicCategories").document("list")
                .set(musicCategories)
                .await()
        }
    }
}