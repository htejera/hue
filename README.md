# hue
**Hue** provides Java components to speed up writing tests using HtmlUnit, such as jQuery style DOM traversal and manipulation and common data generation tools (postal codes, telephone numbers, etc.).
This repo was automatically exported from https://code.google.com/archive/p/hue/

 The code is written by Kevin Wetzels. Some works based on this approach are: https://gebish.org/manual/1.0/api/geb/navigator/Navigator.html

## Hue Doj: Dom like jQuery
The **1.0** release of Hue focused on the creation of Doj, Hue's jQuery emulator for HtmlUnit.

**Why would you want to use that when you're using HtmlUnit?**

Because you can do stuff like this:

* Get the inputs in the login form through simple CSS selectors
* Set the username 
* Set the password
* Get the submit button and click it Page result

```javascript
Doj input = Doj.on(page).get("#login input");
input.withName("username").value("myusername");
input.withName("password").value("mypassword");
inputs.withType("submit").click(); 

// And since the 1.1 release, you can do this: 
Doj formElements = Doj.on(page).get("select, input.textfield, textarea, button#save-button"); 
```

## Get started with Maven
If you're using Maven, getting started using Hue is as simple as plugging the following into your pom:

```xml
<dependency> 
   <groupId>be.roam.hue</groupId>
   <artifactId>hue</artifactId> 
   <version>1.1</version> 
   <scope>test</scope> 
</dependency>
```

The snapshot releases are available at http://hue.googlecode.com/svn/maven2-snapshots/be/roam/hue/hue/.