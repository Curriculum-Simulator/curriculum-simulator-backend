const pwaCache = 'curriculum-simulator-site-cache';
const assets = [
  '/',
  '/index',
  '/courses/*',
  '/program',
  '/img/logo-esi.png',
  '/css/layout.css',
  '/css/style.css',
];

self.addEventListener('install', e => {
  e.waitUntil(
    // after the service worker is installed,
    // open a new cache
    caches.open(pwaCache).then(cache => {
      // add all URLs of resources we want to cache
      return cache.addAll(assets);
    })
  );
});

self.addEventListener("fetch", fetchEvent => {
  fetchEvent.respondWith(
    caches.match(fetchEvent.request).then(res => {
      return res || fetch(fetchEvent.request)
    })
  )
});
