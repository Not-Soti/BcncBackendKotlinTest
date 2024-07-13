package com.example.bcnctest.data.imp

import com.example.bcnctest.data.pub.IRemoteData
import org.springframework.web.client.RestTemplate

class RemoteData : IRemoteData {
    private lateinit var restTemplate: RestTemplate

    fun initialize(restTemplate: RestTemplate){
        this.restTemplate = restTemplate
    }

}