## Java 3 MVC Framework
This is a tiny MVC framework that has been created to support learning about the Model-View-Controller pattern. 

### Usage

#### MVCContext
To get started using this framework, create a new `MVCContext` object with the package 
in which your controller classes will exist and call `processRequest` with a new `Request` object:

```java
MVCContext context = new MVCContext("edu.cscc");
context.processRequest(new Request("Employees", "index"));
```

The `Request` accepts a resource and action (and an optional `Map<String, Object>` of params).
The resource should be the prefix of a controller class name. For example, the resource "Employees" will 
indicate that a class called "EmployeesController" will exist (in this case in the `edu.cscc` package). 
The action "index" indicates that a method called `index()` will exist on the `EmployeesController` class.

Once `processRequest` has been called an execution loop will begin which will continue until the program is stopped 
or a controller method is called with a view that does not route the user anywhere.

If `processRequest` encounters a route request that can't be mapped to a controller and accompanying method then a
`RoutingException` is thrown (this is a runtime exception). 

#### Controllers
A new controller can be created by creating a new class in the package specified when creating the `MVCContext` object
and extending the abstract `ApplicationController` class.
Controller names must follow the pattern of "<RESOURCE_NAME>Controller". So, `EmployeesController` indicates that the resource
`Employees` exists and can be routed to.

##### Example
```java
package edu.cscc;

public class EmployeesController extends ApplicationController {
    public EmployeesController(MVCContext context) {
        super(context);
    }  
    
    //Methods...
}
```

Each action method on a controller must render a view. This is accomplished by calling the `render()` method
on the base `ApplicationController` class. Failing to call `render()` will cause a `RenderingException` to be thrown.

```java
package edu.cscc;

public class EmployeesController extends ApplicationController {
    public EmployeesController(MVCContext context) {
        super(context);
    }  
    
    public void index() {
        render(new EmployeesIndexView(context, new MyFirstModelObject(), new MySecondModelObject()));
    }   
}
```

#### Views
Create a view is as easy as creating a new POJO that extends the abstract `ApplicationView` class. Each view class
must implement the `show()` method and route the user somewhere based on user input. If no route is specified then the
application will end.

##### Example
```java
public class EmployeesIndex extends ApplicationView {
    private MyFirstModelObject myFirstModelObject;
    private MySecondModelObject mySecondModelObject;

    public EmployeesIndex(MVCContext context, MyFirstModelOjbect myFirstModelOjbect, MySecondModelObject mySecondModelObject) {
        super(context);
        this.myFirstModelObject = mySecondModelObject;
        this.mySecondModelObject = mySecondModelObject;
    }
    
    public void show() {
        //rendering logic goes here using whatever model params have been passed.
        Map<String, Object> params = new HashMap<String, Object>();
        route("Companies", "show", params); //params is optional
    }   
}
```

If an error occurs while rendering the view a `RenderingException` will be thrown.

### Building
This project uses Gradle to manage the build process. 

To build, simply execute:
```shell
./gradlew clean build
```

This will generate jar files in `build/libs/`, including the source code with javadocs in `mvc-framework-<version>-SNAPSHOT-sources.jar`.