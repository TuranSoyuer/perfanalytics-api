# Performance Analyzer API
A web performance analyzer api.

### API
Application written with Spring. Data stored in MongoDb.

* GET /api/analytics -> Returns all the metrics for last half hour.
  * Optional query string inputs
    * startDate: time in ms 
    * endDate: time in ms
* POST /api/analytics -> Saves metric data.
    * Input object: AnalyticInput
    
    
### JS Library

Simple and tiny library that has no dependencies.

It relies on the following API's:

* [PerformanceObserver (observe)](https://developer.mozilla.org/en-US/docs/Web/API/PerformanceObserver)
* [Performance (timing)](https://developer.mozilla.org/en-US/docs/Web/API/Performance)
* [Navigator.sendBeacon()](https://developer.mozilla.org/en-US/docs/Web/API/Navigator/sendBeacon)

All of them has enough support to use.

#### Integration

Add the following snippet to head tag of your web app:

```html
<script src="https://performanceanalytics-api.herokuapp.com/perfanalytics.js"></script>
```

It will send the metrics.