
showImageChoose(0);
var lastChoose;
function showImageChoose(n) {

    var x = document.getElementsByClassName("gallery-big-image");
    if (lastChoose != null) {
        x[lastChoose].classList.remove("gallery-display-block");
    }
    x[n].classList.add("gallery-display-block");
    lastChoose = n;

}
