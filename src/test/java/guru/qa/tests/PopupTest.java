package guru.qa.tests;

import static com.codeborne.selenide.Selenide.executeJavaScript;

public class PopupTest {

    static void removePopup() {
        executeJavaScript("$('.popup-main-wrapper').remove()");
        executeJavaScript("$('#popmechanic-snippet').remove()");
        executeJavaScript("$('#push-notification-balloon').remove()");
        executeJavaScript("$('#popmechanic-form-49263').remove()");
        //executeJavaScript("$('[src=\"/ig/js/push_subscribe_firebase.js\"]')");
    }
}
