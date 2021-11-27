function refreshInfo() {
    var request = new XMLHttpRequest();
    var content = document.getElementById("content");
    var error = document.getElementById("error");

    request.onload = function () {
        content.innerHTML = this.responseText;
        error.style.display = "none";
        setTimeout(refreshInfo, 2000);
    }

    request.ontimeout = function () {
        error.innerHTML = "<span>Server timeout. Trying again ...</span>";
        error.style.display = "block";
        setTimeout(refreshInfo, 100);
    }

    request.onerror = function () {
        error.innerHTML = "<span>No server reply. Trying again ...</span>";
        error.style.display = "block";
        setTimeout(refreshInfo, 5000);
    }

    request.open("GET", "/info", true);
    request.timeout = 5000;
    request.send();
}