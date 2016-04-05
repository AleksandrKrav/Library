/**
 * Created by Alexandr on 21.03.2016.
 */

function checkEnter(form) {
    if (validNameValue(form["reg-name"], form["name_help"]) && validLoginValue(form["reg-login"], form["login_help"])
        && validPasswordValue(form["req-pass"]), form["pass_help"]){
        form.submit();
    } else {
        alert("Простите, но форма заполнена неверно.");
    }
}

function validNameValue(inputField, helpText) {
    if(!validateNonEmpty(inputField, helpText)){
        return false;
    }
    return validRegEx(/^[a-z0-9_-]{3,15}$/, inputField.value, helpText, "Пожалуйста, введите имя (например, Алексанр)");
}

function validLoginValue(inputField, helpText) {
    if(!validateNonEmpty(inputField, helpText)){
        return false;
    }
    return validRegEx(/^[a-zA-Z0-9_-]{3,15}$/, inputField.value, helpText, "Пожалуйста, введите имя (например, Alex23)");
}

function validPasswordValue(inputField, helpText) {
    if(!validateNonEmpty(inputField, helpText)){
        return false;
    }
    return validRegEx(/([a-zA-Z0-9_-].{5,20})/, inputField.value, helpText, "Пожалуйста, введите имя (например, Password23)");
}

function validRegEx(regex, inputStr, helpText, helpMessage) {
    if (!regex.test(inputStr)) {
        if (helpText != null)
            helpText.innerHTML = helpMessage;
        return false;
    } else {
        if (helpText != null)
            helpText.innerHTML = "";
        return true;
    }
}

function validateNonEmpty(inputField, helptext) {
    if (inputField.value.length == 0) {
        if (helptext != null) {
            helptext.innerHTML = "Please enter the value."
            return false;
        }
        else {
            if (helptext != null) {
                helptext.innerHTML = "";
                return true;
            }
        }
    }
}