package com.example.core.utils

import com.example.core.data.source.local.entities.CountryEntity
import com.example.core.data.source.local.entities.CountryInfoEntity
import com.example.core.data.source.remote.response.CountryResponse
import com.example.core.domain.model.Country
import com.example.core.domain.model.CountryInfo

object DataMapper {
    fun mapResponsesToEntities(input: List<CountryResponse>):List<CountryEntity>{
        val countryList= ArrayList<CountryEntity>()
        input.map {
             CountryEntity(
                    active = it.active,
                    activePerOneMillion = it.activePerOneMillion,
                    cases = it.cases,
                    casesPerOneMillion = it.casesPerOneMillion,
                    continent = it.continent,
                    country = it.country,
                    critical = it.critical,
                    criticalPerOneMillion = it.criticalPerOneMillion,
                    deaths = it.deaths,
                    deathsPerOneMillion = it.deathsPerOneMillion,
                    oneCasePerPeople = it.oneCasePerPeople,
                    oneDeathPerPeople = it.oneDeathPerPeople,
                    oneTestPerPeople = it.oneTestPerPeople,
                    population = it.population,
                    recovered = it.population,
                    recoveredPerOneMillion =  it.recoveredPerOneMillion,
                    tests = it.tests,
                    testsPerOneMillion = it.testsPerOneMillion,
                    todayCases = it.todayCases,
                    todayDeaths = it.todayDeaths,
                    todayRecovered = it.todayRecovered,
                    updated = it.updated,
                     countryInfoEntity = if(it.countryInfo!=null){CountryInfoDataMapper.mappingResponseToEntity(it.countryInfo)}else CountryInfoEntity()
            ).apply {
                countryList.add(this)
            }
        }
        return countryList
    }
    fun mapEntitiesToDomain(input: List<CountryEntity>):List<Country>{
        val countryList= ArrayList<Country>()
        input.map {
            Country(
                    active = it.active,
                    activePerOneMillion = it.activePerOneMillion,
                    cases = it.cases,
                    casesPerOneMillion = it.casesPerOneMillion,
                    continent = it.continent,
                    country = it.country,
                    critical = it.critical,
                    criticalPerOneMillion = it.criticalPerOneMillion,
                    deaths = it.deaths,
                    deathsPerOneMillion = it.deathsPerOneMillion,
                    oneCasePerPeople = it.oneCasePerPeople,
                    oneDeathPerPeople = it.oneDeathPerPeople,
                    oneTestPerPeople = it.oneTestPerPeople,
                    population = it.population,
                    recovered = it.population,
                    recoveredPerOneMillion =  it.recoveredPerOneMillion,
                    tests = it.tests,
                    testsPerOneMillion = it.testsPerOneMillion,
                    todayCases = it.todayCases,
                    todayDeaths = it.todayDeaths,
                    todayRecovered = it.todayRecovered,
                    updated = it.updated,
                    countryInfo =  if(it.countryInfoEntity!=null){CountryInfoDataMapper.mappingEntitiesToDomain(it.countryInfoEntity)}else CountryInfo(),
                    isFavorite = it.isFavorite
            ).apply {
                countryList.add(this)
            }
        }
        return countryList
    }

    fun mapDomainToEntity(input: Country) = CountryEntity(
            active = input.active,
            activePerOneMillion = input.activePerOneMillion,
            cases = input.cases,
            casesPerOneMillion = input.casesPerOneMillion,
            continent = input.continent,
            country = input.country,
            critical = input.critical,
            criticalPerOneMillion = input.criticalPerOneMillion,
            deaths = input.deaths,
            deathsPerOneMillion = input.deathsPerOneMillion,
            oneCasePerPeople = input.oneCasePerPeople,
            oneDeathPerPeople = input.oneDeathPerPeople,
            oneTestPerPeople = input.oneTestPerPeople,
            population = input.population,
            recovered = input.population,
            recoveredPerOneMillion =  input.recoveredPerOneMillion,
            tests = input.tests,
            testsPerOneMillion = input.testsPerOneMillion,
            todayCases = input.todayCases,
            todayDeaths = input.todayDeaths,
            todayRecovered = input.todayRecovered,
            updated = input.updated,
            countryInfoEntity = if(input.countryInfo!=null){CountryInfoDataMapper.mappingDomainToEntity(input.countryInfo)}else CountryInfoEntity()
    )
}