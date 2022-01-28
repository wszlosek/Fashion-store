# Fashion store - mobile app

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=wszlosek_UJ-Android&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=wszlosek_UJ-Android)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=wszlosek_UJ-Android&metric=bugs)](https://sonarcloud.io/summary/new_code?id=wszlosek_UJ-Android)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=wszlosek_UJ-Android&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=wszlosek_UJ-Android)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=wszlosek_UJ-Android&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=wszlosek_UJ-Android)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=wszlosek_UJ-Android&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=wszlosek_UJ-Android)

Mobile application of a fashion store with a local database (client-side). Apart from it, the server-side with the database along with the REST API.

## Table of contents
* [General info](#a)
* [Scenarios and possibilities of the application](#b)
* [Technologies](#c)
* [Run](#d)
* [Status](#e)

## General info <a name="a"></a>

This project is a **mobile application** for the **Android** platform that supports the **fashion store**. 

<br>

The following **models** are implemented on the ***server-side***: *Cart, Category, Color, LoginData, Product, ShopLocalization, User*. For each of these models, CRUD (get, put, post, delete) methods are implemented. The server-side data are stored on the **SQLite** Database. The server was created with the **Ktor** framework (with gson serialization).

<br>

The ***front-end*** was based on Android **fragments** with a **navigation component** (graph). The data is collected from the server API via **Retrofit services** (which download data from REST API endpoints). The local data are stored on the **Realm** Database. You can log in to the application in a traditional way (registration, login with a login and password) and via an account on Github. Payment in our fashion store is made by VISA card with Stripe service.

In addition, there is real-time synchronization between the server database (SQLite) and the client-side database (Realm).



## Scenarios and possibilities of the application <a name="b"></a>

### Graph of transitions of application views:

![app_graph](https://user-images.githubusercontent.com/53795852/151457930-f060fce4-e110-4305-9d6b-0bf572112b21.png)


#### Examples of application usage paths:
* (happy path...) registration/logging in or logging in using a Github account --> getting to know the product list --> getting to know our company - optional --> product selection --> product purchase --> payment.
* logging in --> getting to know our company.
* incorrect login or password.
* logging in --> getting to know the product list --> product selection --> product purchase --> payment failed (lack of account funds, incorrect credit card data, etc).

#### Use of HTTP methods for API (endpoints):

For each model, you can use following HTTP commands. Tip: for generate https address, you can use [ngrok](https://ngrok.com/) - write in terminal:
```bash
./ngrok http <your_port_number> 
```

* **GET**: ***<https_address>/model_name***, ***<https_address>/***model_name/id***
* **POST**: ***<https_address>/model_name***
* **PUT**: ***<https_address>/model_name***, , ***<https_address>/***model_name/id***
* **DELETE**:  ***<https_address>/model_name***, , ***<https_address>/***model_name/id***


## Technologies <a name="c"></a>

#### Back-end:
* **Kotlin** - main programming language
* [Ktor](https://ktor.io/) framework - server, routing
* SQLite with [JetBrains Exposed](https://github.com/JetBrains/Exposed) framework - database and operations on it


#### Front-end:
* **Kotlin** - main programming language
* [Retrofit](https://square.github.io/retrofit/) - HTTP Client, executing commands from endpoints
* [Realm](https://realm.io/) - database and operations on it
* OAuth, Stripe, Google Maps - services that enable certain app functions to work


#### Other (including the tools used while working on the application):
* Postman, ngrok, MongoDB Realm Studio, SonarCloud, Docker


## Run (instructions) <a name="d"></a>
You should prepare API keys for proper operation:
* on the **server-side**, you will need the secret API key of [Stripe](https://stripe.com/docs/keys). You should put it in your program, creating file ***Secrets.kt*** with the following Object in Kotlin (and make import with this file in ***OrderAndStripeRoutes.kt***): 
```kotlin
object Secrets {
    const val secretServerKey = <your_stripe_api_key_string>
}
```

* on the **client-side**, you will need the publishable API key of [Stripe](https://stripe.com/docs/keys), [Google Maps API Key](https://developers.google.com/maps/documentation/javascript/get-api-key) and [Github OAuth secret-key & Client ID](https://github.com/settings/developers). 
<br>

**Stripe** key put in file ***Secrets.kt***:
```kotlin
object Secrets {
    const val secretKeyClient = <your_client_stripe_api_key_string>
} 
```

<br> **Github** secret-key and client ID put in ***GithubConstants.kt***:
```kotlin
object GithubConstants {
    const val CLIENT_ID = <your_client_id_string>
    const val CLIENT_SECRET = <your_client_secret_key_string>
    const val REDIRECT_URI = "https://github.com/wszlosek"
    const val SCOPE = "read:user,user:email"
    const val AUTHURL = "https://github.com/login/oauth/authorize"
    const val TOKENURL = "https://github.com/login/oauth/access_token"
} 
```
<br> 

**Google Maps**: in ***local.properties*** add the following code:
```bash
MAPS_API_KEY=<YOUR_GOOGLE_MAPS_API_KEY>
```

<br> <br>

You can build (and run) a server side with **Docker**, with the port *portNumber*:
```bash
   docker build -t serverside .  
```
```bash
   docker run -p <portNumber>:<portNumber> serverside
```

## Status <a name="e"></a>

#### The project has been completed. The only corrections required are the appearance of the UI layer.
