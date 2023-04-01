package com.climbingtrackerapp.architecture

interface Destination

interface Router<TypeOfDestination: Destination> {
    fun TypeOfDestination.routeTo()
}