function loadFile(event){
    document.getElementById("fotos").innerHTML = '';
    document.getElementById("fotos").appendChild(document.createElement("br"));
    for (i = 0; i < event.target.files.length; i++) {
        var elem = document.createElement("img");
        elem.setAttribute("src", URL.createObjectURL(event.target.files[i]));
        elem.setAttribute("height", "100");
        elem.setAttribute("width", "100");
        document.getElementById("fotos").appendChild(elem);
    };
};