package com.pucese.ecommerce.security.oauth2.user;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getFirstname() {
        return (String) attributes.get("firstname");
    }
    
    public String getLastname() {
        return (String) attributes.get("lastname");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}
}
