# Sandwich Club Project

## Description
In this project we were given a nearly completed application and asked to fill
in code to parse JSON and properly display sandwich information.

## Versions
Android Studio 3.4.2
Build Tools 28.0.3
Min SDK 16
Target SDK 26

## Thoughts on the project
This was our first foray into creating Android applications, so, it was
interesting to see how they had laid out the initial application.

Parsing JSON using the JSONObject class and methods was a bit tedious (and
would've been easier if we could use a third-party library).

I spent some extra time trying to add polish to the UI. To this end, I made sure
to remove sections that didn't have any data defined and added a fallback image
if we failed to load the image at the URL provided.
