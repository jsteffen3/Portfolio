
var close = document.getElementById('close');
var open = document.getElementById('open');
var nav = document.getElementById('nav');

close.addEventListener("click", function(){closeNav()}, false);
open.addEventListener("click", function(){openNav()}, false);

function closeNav() {
    nav.disabled = true;
    nav.hidden = true;
    open.hidden = false;
    open.disabled = false;
}

function openNav() {
    nav.disabled = false;
    nav.hidden = false;
    open.hidden = true;
    open.disabled = true;
}