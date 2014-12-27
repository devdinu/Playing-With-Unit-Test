# Playing With Unit Test - Mocking

This code i've tried to explain the value of thenAnswer in Mockito.

* Mocking a method to return a response based on the parameters passed to the method in spy at runtime.
* Mockito's when(methodCall).thenAnswer( ... ) to implement that.
* invocation.getArguments gets the Arguments at runtime.

My Blog which explains a scenario for this : [Dynamic Mocking Reponse](http://learnersguide.wordpress.com/2014/12/27/dynamic-mocking-respose-the-value-of-thenanswer-in-mockito/)