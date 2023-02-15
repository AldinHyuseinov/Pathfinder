function isGpx() {
    let gpxFile = document.getElementById("inputGpx").files[0];
    let fileNotSelectedErrElement = document.getElementById("inputGpxErrorEmpty");

    if (typeof gpxFile === 'undefined') {
        fileNotSelectedErrElement.style.display = "block";

        return false;
    }
    fileNotSelectedErrElement.style.display = "none";

    let errorElement = document.getElementById("inputGpxError");
    let fileExtension = gpxFile.name.substr(gpxFile.name.lastIndexOf('.') + 1).toLowerCase();

    if (fileExtension !== "gpx") {
        errorElement.style.display = "block";

        return false;
    }
    errorElement.style.display = "none";

    return true;
}

function gpxToString() {

    if (isGpx()) {
        let gpxFile = document.getElementById("inputGpx").files[0];
        let reader = new FileReader();

        reader.onload = function (event) {
            document.getElementById("gpxString").value = event.target.result;
        }
        reader.readAsText(gpxFile);
    }
}



