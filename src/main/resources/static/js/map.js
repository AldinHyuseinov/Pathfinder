let gpxCoords = document.getElementById('gpxCoords').value;

// Create a Leaflet map
let map = L.map('map').setView([42.6977, 23.3219], 13);

// Add a tile layer to the map
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors',
    maxZoom: 18
}).addTo(map);

// Parse the GPX coordinates and add them as a polyline to the map
new L.GPX(gpxCoords, {
    async: true,
    polyline_options: {
        color: 'red'
    },
    marker_options: {
        startIconUrl: '/images/pin-icon-start.png',
        endIconUrl: '/images/pin-icon-end.png',
        shadowUrl: '/images/pin-shadow.png',
        wptIconUrls: {
            '': '/images/pin-icon-wpt.png',
        }
    }
}).on('loaded', function (e) {
    map.fitBounds(e.target.getBounds());
}).addTo(map);