package com.climbingtrackerapp.architecture

interface Destination

interface RouteReceiver<TypeOfDestination: Destination> {
    fun onRoute(destination: TypeOfDestination)
}

interface RouteSender<TypeOfDestination: Destination> {
    fun registerRouteReceiver(routeReceiver: RouteReceiver<TypeOfDestination>)
    fun TypeOfDestination.push()
}
