package com.example.core.utils

import com.example.core.data.source.local.entities.CountryInfoEntity
import com.example.core.data.source.remote.response.CountryInfoResponse
import com.example.core.domain.model.CountryInfo

object CountryInfoDataMapper {
    fun mappingResponseToEntity( input: CountryInfoResponse): CountryInfoEntity {
        return CountryInfoEntity(
                _id = input._id,
                flag = input.flag,
                iso2 =  if(input.iso2!=null)input.iso2!!else "",
                iso3 = if(input.iso3!=null)input.iso3!!else "",
                lat = input.lat,
                long = input.long
        )
    }

    fun mappingEntitiesToDomain(input: CountryInfoEntity): CountryInfo{
        return CountryInfo(
                _id = input._id,
                flag = input.flag,
                iso2 =  if(input.iso2!=null) input.iso2 else "",
                iso3 = if(input.iso3!=null) input.iso3 else "",
                lat = input.lat,
                long = input.long
        )
    }
    fun mappingDomainToEntity(input: CountryInfo): CountryInfoEntity {
        return CountryInfoEntity(
                _id = input._id,
                flag = input.flag,
                iso2 =  if(input.iso2!=null)input.iso2!!else "",
                iso3 = if(input.iso3!=null)input.iso3!!else "",
                lat = input.lat,
                long = input.long
        )
    }
}