# Gatling-perf-test Project

### Overview
This project was created to cover requirements of "Backend performance testing"

## Discarted requets
All of requests, whose the type is HTTP `OPTIONS` or the response status are `404` or `503` weren't added to this project, because I got them irrelevant to the flow


## How to execute
The project has 2 execution options:
1. ``mvn gatling:test`` - will be used the default parameter to define the number of users for each flow.
2. ``mvn gatling:test -Dusers=<NUMBER>`` - will calculate dynamically the number of users for each flow.
