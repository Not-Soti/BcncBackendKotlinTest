package com.example.bcnctest.repository.imp

import com.example.bcnctest.data.pub.ILocalData
import com.example.bcnctest.data.pub.IRemoteData
import com.example.bcnctest.repository.pub.IRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class Repository(
    @Autowired private val localData: ILocalData,
    @Autowired private val remoteData: IRemoteData
) : IRepository {

    override fun getAlbums() {
        TODO("Not yet implemented")
    }

}