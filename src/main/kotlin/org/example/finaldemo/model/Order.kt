package org.example.finaldemo.model

data class Order constructor(
    val cartValue: Int,
    val deliveryDistance: Int,
    val numberOfItems: Int,
    val time: String
)

//Data classes automatically generate toString(), equals(), hashCode(), 
//and copy() methods based on the properties declared in the primary 
//constructor. This reduces the amount of code you need to write, 
//making your codebase more concise and easier to maintain.

//Data classes can be declared as val (immutable) properties in 
//the primary constructor, making instances of the class immutable by default. 

// Data classes can have default parameter values in their constructors, 
//allowing you to create instances of the class without specifying all 
//properties. 