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
  const fcp = convertMsToSec(performance.getEntriesByName("first-contentful-paint",
      "paint")[0].startTime);
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