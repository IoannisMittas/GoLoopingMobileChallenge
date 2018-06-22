# Familynet Mobile Engineering Challenge

## The role

[http://www.go-looping.com/en/jobs/](http://www.go-looping.com/en/jobs/)

## Context

A common user-case for mobile apps is a user account with associated credentials that allow the app to securely interact with a RESTful back-end API.

## Use case

The user is required to log into the app, authenticated against the back-end API. After a successful login, the API returns a JSON payload containing an API token.

Other API endpoints require the API token to be sent in the HTTP header `Bearer` variable.

The app requires a simple login screen that accepts a username and password.

Once successfully logged in, the user is shown a profile screen with their email address and a profile image.

By default, the profile photo should be a blank silhouette avatar. Tapping the photo should allow the user to select an image from their photo library, or to take one with the front-facing camera. Saving the changes should update the avatar locally and with the back-end.

The back-end has the following rest endpoints:

```
POST /sessions/new
{ "email”: "email", "password": "password"} -> { "userid": "userid", "token": "token" }

GET /user/:userid -> { "email": "email", "avatar_url": "avatar_url" }

POST /user/:userid/avatar
{ "avatar": ":base64_encoded_data" } -> { "avatar_url": "avatar_url" }
```
`
The valid username and password combination is
```
email: user@email.com
password: password
```
Assume that the back-end makes of use of standard HTTP success and error codes.

## Challenge

### Required

* Build a simple native app (iOS or Android) that uses standard interface controls to provide the login and account profile screens.

* On subsequent launches of the app, the user should be automatically logged in (assuming that the last login attempt was successful).

* Add the ability for the user to change their avatar, either by taking a photo with the inbuilt front camera, or by selecting a photo from their photo library.

* Ensure that when the device captures a new image that the data sent back to the back-end is not greater than 1Mb.

### Stretch goals

* Display the user’s avatar in a circle. Ensure that the photo is correctly positioned within the display area.

* Apply a filter to the image (of your choice) prior to uploading to the backend.

* Implement the app using an alternative to the 'standard' MVC architecture, such as VIPER or MVVM.

## What we're looking for

We're biased towards clean, readable and idiomatic code. Where there's a choice between cleverness and clarity, we'll pick clarity every time. Tests are good; intelligently-applied tests and consideration of edge cases is even better.

## Server

A basic [Sinatra-based](http://sinatrarb.com) server app is included, to provide the backend described above.

It exposes a basic API on port 3000.

### Running the server

#### Prerequisites

* [Docker](https://www.docker.com) installed locally

#### Process

* Change to the `sinatra` subdirectory
* Build the server: `docker build -t server .` (note the trailing dot)
* Run the server: `docker run --rm -p 3000:3000 server`

### Postman Library

A collection of Postman requests is [available](https://www.getpostman.com/collections/bf9de3813d9dd0f74962?_ga=2.161426621.1808206250.1503483118-1306924130.1503483118)
