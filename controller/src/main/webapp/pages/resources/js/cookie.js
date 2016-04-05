/**
 * Created by Alexandr on 20.03.2016.
 */
function writeCookie(name, value, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires = " + date.toGMTString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    var searchName = name + "=";
    var cokies = document.cookie.split(';');
    for (var i = 0; i < cokies.length; i++) {
        var c = cokies[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1, c.length);
            if (c.indexOf(searchName) == 0) {
                return c.substring(searchName.length, c.length);
            }
        }
    }
    return null;
}

function eraseCookie(name) {
    writeCookie(name, "", -1);
}

