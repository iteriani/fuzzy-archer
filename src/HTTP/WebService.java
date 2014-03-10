package HTTP;

public abstract class WebService {
	protected final String WEB_SERVICE="http://placeit-db.herokuapp.com/";
	protected final String SIGNUP_URL = WEB_SERVICE + "/signup/";
	protected final String LOGIN_URL = WEB_SERVICE + "/login/";
	protected final String LOGOUT_URL = WEB_SERVICE + "/logout/";
	protected final String ALL_PLACEITS = WEB_SERVICE + "/placeits/";
	protected final String SINGLE_PLACEIT = WEB_SERVICE + "/placeit/";
	protected final String UPDATE_PLACEIT = WEB_SERVICE + "/placeit/update";
	protected final String DELETE_PLACEIT = WEB_SERVICE + "/placeit/delete";
}
	