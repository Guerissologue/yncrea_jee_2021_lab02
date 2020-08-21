# Lab 02

## Intro

With this lab, you will build 6 modular applications!

We will play with the hexagonal architecture and try to compose several layers in order to have the same feature but in many different forms.

The goal of the app you will build is pretty simple: it displays a random quote in the console, in a webapp or in a JavaFX window.

## Starter kit

If you clone this repo, you will get:
* the Maven configuration & structure (`pom.xml` and several provided modules)
* some code which do not compile for the moment

## Let's build the core...
* create a new Maven module called `lab02-core`. This should create a new folder in your project, with a `pom.xml` file and a `src` folder inside.
* in this brand new Maven module, create an interface `yncrea.lab02.core.dao.QuoteProvider`
  * it has 2 methods:
    * `getOneQuote` which has an integer parameter and returns a String
    * `getNumberOfQuotes` which returns an integer
  * this is what we call `Inversion of Control` because this interface indicates which contract to implement if you want  to provide quotes to the application.
* create an interface called `yncrea.lab02.core.service.QuoteService`
  * it has a single method called `getRandomQuote` which returns a String.
  * this interface is the only "entrypoint" that the future applications will "consume".
* create an implementation for this interface : `yncrea.lab02.core.service.impl.QuoteServiceImpl`
  * find a smart way to "inject" a `QuoteProvider` object in this implementation
  * implement the method, `java.security.SecureRandom` will clearly help you ;)
  
That's it! you have the backbone of all your future apps.

## The provider implementation
* did you notice that the `lab02-provider` module is now ok for the compilation?
* this module has one implementation of the `QuoteProvider` you wrote previously : `CNFProvider`
* it is sure that you will looooove this provider...

## First App
It is time now to build an app wich uses our core and our provider.
* create a new Maven module called `lab02-app`
  * it has 2 dependencies : `lab02-core` & `lab02-provider`
* in this module, create a class `yncrea.lab02.app.Application`
* find a way to "inject" a `QuoteService` object in this application
* implement a method that gets a quote from the service then displays it in the console.
* create the last class `yncrea.lab02.app.AppLauncher` with the main method:
  * it instanciates a provider
  * it instanciates a service
  * it instanciates an application
  * it injects the provider in the service
  * it injects the service in the application
  * it calls the method of the application.

Now you are able to appreciate the best quotes about our favourite Texas hero.

## Console is good but web is better!
In the `lab02-webapp` module, read the `web.xml` file. 

Now you're a superstar of the servlets and you know how to "wire" a quote application, write a servlet which prints a random quote for each request. 
`ReflectionUtils` utility class will help you. 

Deploy this app in Tomcat and enjoy.


## Stop kidding, let's build an app like a pro!
This part of the lab relies on Spring Framework. We will use this amazing tool to scaffold our app.
* first of all, in the `lab02-springapp` module, create a `yncrea.lab02.app.QuoteApplication` class.
  * find a smart way to inject a `QuoteService` inside.
  * implement a method that prints a quote in the console (yes, once again...)
* now you have all the Lego bricks, fill the `META-INF/applicationContext.xml` provided file to build your application.
* create a `yncrea.lab02.launcher.SpringXMLAppLauncher` with a main method:
  * it builds a Spring Application Context : `new ClassPathXmlApplicationContext(("META-INF/applicationContext.xml"));`
  * it gets an `QuoteApplication` bean from this context
  * it prints a quote thanks to the bean
  
Gorgious! with simple parts of code, you were able to build a complex architecture which allows you to deploy a feature in many different ways. Congrats! 

## You want more?
* create a `yncrea.lab02.config.AppConfig` class which brings the scaffolding info. This file replaces the xml file.
* create a `yncrea.lab02.launcher.SpringAppLauncher` with a main method:
  * it builds a Spring Application Context : `new AnnotationConfigApplicationContext(AppConfig.class);`
  * it gets an `QuoteApplication` bean from this context
  * it prints a quote thanks to the bean

## Even more?
* create a `yncrea.lab02.gateway.QuoteGateway` class
  * annotate it with `@Named`
  * it has a `QuoteService` field, annotated with `@Inject`
  * it has a `getQuoteFromService()` method which returns a String (guess the implementation)
* duplicate the `AppConfig` class and name it `AppInjectConfig`
  * remove the creation of the `QuoteApplication` bean
  * add this annotation to the class : `@ComponentScan(basePackages = "yncrea.lab02.gateway")`
* create a launcher which uses this `AppInjectConfig`

Do you get it? Thanks to the scan and the annotations, Spring managed to build automatically the beans and their injections.

## Maybe this lab is not long enough...
Try to write a new provider and play with it!
 
# _Good luck!_
 
 
