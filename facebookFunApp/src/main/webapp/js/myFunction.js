/**
 * Created by ChathuraDR on 5/12/2017.
 */

function makeRequest() {
    // Define properties
    var AUTH_ENDPOINT = "https://www.facebook.com/dialog/oauth";
    var RESPONSE_TYPE = "code";
    var CLIENT_ID = "1443129495732404";
    var REDIRECT_URI = "http://localhost:8080/facebookFunApp/callback";
    var SCOPE = "public_profile";

    // Build authorization request endpoint
    // According OAuth 2 specification, all the request parameters should be URL encoded
    var requestEndpoint = AUTH_ENDPOINT +
        "?response_type=" + encodeURIComponent(RESPONSE_TYPE) +
        "&client_id=" + encodeURIComponent(CLIENT_ID) +
        "&redirect_uri=" + encodeURIComponent(REDIRECT_URI) +
        "&scope=" + encodeURIComponent(SCOPE);

    // Send to authorization request endpoint
    window.location = requestEndpoint;

}
