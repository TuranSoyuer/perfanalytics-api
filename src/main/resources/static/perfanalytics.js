const isPerformanceSupported = () => {
  return !!window.performance && !!window.performance.timing
      && !!window.performance.getEntriesByType
      && !!performance.getEntriesByName;
}

const convertMsToSec = (ms) => {
  return ms / 1000;
}

const getPerformanceMetrics = (performance) => {
  const timing = performance.timing;

  const ttfb = convertMsToSec(timing.responseStart - timing.requestStart);
  const domLoad = convertMsToSec(
      timing.domContentLoadedEventEnd - timing.navigationStart);
  const windowLoad = convertMsToSec(Date.now() - timing.navigationStart);
  //const fcp = convertMsToSec(performance.getEntriesByName("first-contentful-paint",
  //    "paint")[0].startTime);
  const fcpEntry = performance.getEntriesByName("first-contentful-paint","paint");
  let fcp = 0;
  if(fcpEntry && fcpEntry.length>0) {
    fcp = convertMsToSec(fcpEntry[0].startTime);
  }
  console.log("OLD FCP HEERE: " + performance.getEntriesByName("first-contentful-paint","paint"));
  console.log("OLD FCP getEntries by type: " + performance.getEntriesByType("paint"));
  console.log("OLD FCP getEntries  " + performance.getEntries());
  const resourceMetrics = performance.getEntriesByType('resource').map(
      (resource) => {
        return {
          name: resource.name,
          duration: convertMsToSec(resource.duration),
          transferSize: resource.transferSize,
          initiatorType: resource.initiatorType
        }
      });

  return {ttfb, fcp, domLoad, windowLoad, resourceMetrics};
}

const sendPerformanceMetrics = (metrics) => {
    let headers = {
        type: 'application/json'
    };
    let blob = new Blob([JSON.stringify(metrics)], headers);
    navigator.sendBeacon('https://performanceanalytics-api.herokuapp.com/api/analytics', blob);
}

const startObserver = () => {
  console.log('start observer:' + typeof(PerformanceObserver));
  if(typeof(PerformanceObserver) === 'undefined') return;

  const observerEntryHandlers = {
    paint(entry) {
      console.log("enrty:" + entry);
      if(entry.name !== 'first-contentful-paint') return;

      console.log("first-contentful-paint start time(FCP):" + entry.startTime);
    }
  }
  
  console.log("pass1");
  /*
  const observer = new PerformanceObserver((list) => {
    console.log("pass2" + list);
    for (const entry of list.getEntries()) {
      console.log("observer entry:" + entry);
      observerEntryHandlers[entry.entryType](entry);
    }
  })
  */

  var observer = new PerformanceObserver(function(list, obj) {
    var entries = list.getEntries();
    for (var i=0; i < entries.length; i++) {
      // Process "mark" and "frame" events
      console.log("observer entry:" + entry);
    }
  });
  observer.observe({entryTypes: ["paint", "mark"]});

  console.log("pass3");
  //observer.observe({ entryTypes: ['paint'] });
}

//startObserver();

window.addEventListener('load', () => {
  if (!isPerformanceSupported()) {
    console.log("perfanalytics.js: window.performance is not supported!");
    return;
  }

  let perfMetrics = getPerformanceMetrics(window.performance);
  perfMetrics["siteUrl"] = window.location.href;
  console.log(perfMetrics);
  sendPerformanceMetrics(perfMetrics);
});
