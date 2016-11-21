package com.pms

class PmGoals {

    long id
    long version
    long serviceId
    String goal
    int sequence

    static mapping = {
        goal     sqlType: 'text'
    }

    static constraints = {
        goal     size: 2..15000
    }
}
