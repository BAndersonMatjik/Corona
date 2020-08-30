package com.beone.bestpractice.core.utils

import com.beone.bestpractice.core.data.local.entities.CountryEntity
import com.beone.bestpractice.core.data.local.entities.CountryInfoEntity
import com.beone.bestpractice.core.data.source.remote.response.CountryInfoResponse
import com.beone.bestpractice.core.domain.model.CountryInfo

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
                iso2 =  input.iso2,
                iso3 = input.iso3,
                lat = input.lat,
                long = input.long
        )
    }
    fun mappingDomainToEntity(input: CountryInfo): CountryInfoEntity{
        return CountryInfoEntity(
                _id = input._id,
                flag = input.flag,
                iso2 =  input.iso2!!,
                iso3 = input.iso3!!,
                lat = input.lat,
                long = input.long
        )
    }
}