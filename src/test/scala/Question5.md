# Exercise 5

Explain what the following code means:
```scala
trait MyAlg[F[_]] {
    def insertItSomewhere (someInt: Int ): F [ Unit ]
    def doSomething (someInt: Int ): F [ Int ]
}
```

This trait can be extended by abstract and concrete classes specifying a first-order type ```F[_]```. 
The same type is used to define the return value of the two abstract methods: insertSomewhere and doSomething.

The advantage of this approach is that we can group functions in traits leaving the client that implement it to decide which kind of monad to use.
In some project, for example, we can use some library with particular return types, we can define our own types, or forced to use a specific one.
