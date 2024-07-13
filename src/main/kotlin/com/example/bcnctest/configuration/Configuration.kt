package com.example.bcnctest.configuration

import com.example.bcnctest.data.imp.LocalData
import com.example.bcnctest.data.imp.RemoteData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.web.client.RestTemplate

@Configuration
class BeanFactory {

    @Bean
	fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun remoteData(
        @Autowired restTemplate: RestTemplate
    ) : RemoteData {
        val remoteData = RemoteData()
        remoteData.initialize(restTemplate)
        return remoteData
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    fun localData() = LocalData()
}