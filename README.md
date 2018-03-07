# Button Challenge
This repository features a very simple Android application that makes HTTP requests to a mock API server. This is part of the interview process @Button.

## Overview
<img src="/sc_1.png" width="150"><img src="/sc_2.png" width="150"><img src="/sc_3.png" width="150"><img src="/sc_4.png" width="150">

### Assumptions Made
- **The empty response at the `GET /user/:id/transfers` was intentional.** Normally, for such an endpoint, the client can expect to receive an empty array `[]` for nonexistent data. However, this specific endpoint was returning nothing (besides a status code) if no `Transfer`s were found for the specific user.
- **The** `id` **in the** `Transfer` **JSON object was the ID of the** `User` **who was initiating the** `Transfer`. Because of the assumption, I incorrectly designed the app so that the button to add a `Transfer` shows up in the `User` specific page.
- **The** `User` **object will be lightweight.** At least in the context of this challenge, the `User` object is lightweight enought that I am "parceling" to `UserActivity`. I did so because hitting the **`GET /user/:id`** would be more expensive (in scale) than simply parceling the object between Activities.

## Getting Started

### Direct Download
You can download the APK for this application [from the releases page](https://github.com/najmsheikh/button-challenge/releases).

### Clone The Repository
You may also clone this repository and recompile the application from your local machine. Should you run into any issues, please make sure you have Java 8 installed as the source code was written an compiled in 1.8.

`git clone https://github.com/najmsheikh/button-challenge.git`
