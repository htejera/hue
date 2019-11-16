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

## Introduction

[HtmlUnit](http://htmlunit.sourceforge.net/) offers a lot of power and flexibility in testing webpages, but if you want to find the **span** with class item in the third column of the second row of the **table** inside the **div** with **id** updates, you're basically left with two choices: 

* Use a hideous [XPath](https://en.wikipedia.org/wiki/XPath) expression
* Get the element with **id** updates, get the **table**, get the second **row**, the third column of that **row**, etc.

**Doj** offers features that make sure you never have to touch an XPath expression again when using **HtmlUnit**, while keeping your code concise and null pointer safe.

Below is the corresponding code. Remember that elements in HTML can have multiple classes, separated by whitespace.

```java 

//The XPath way... 
DomNode spanNode = page.getFirstByXPath("//id('updates')//tr[2]/td[3]/span[contains(concat(' ', @class, ' '),concat(' ', 'item', ' '))]");

// The do-it-yourself way... If one of the elements is missing 
// you'll get a nice NullPointerException in your code 
List spanList =  page.getElementById("updates")
                     .getElementsByTagName("tr").get(1)
                     .getElementsByTagName("td").get(2)
                     .getElementsByTagName("span"); 
HtmlElement span = null; 

for (HtmlElement element : spanList) { 
     String classValue = element.getAttribute("class"); 
     classValue = (classValue == null ? "" : " " + classValue + " "); 
     if (classValue.contains(" item ")) { 
         span = element; 
         break; 
     } 
 }
     
// The Doj way... 
Doj spanDoj = Doj.on(page).get("#updates tr", 1).get("td", 2).get("span.item"); 
```
