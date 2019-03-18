package com.Be1StopClick.security.oauth2.user;

/*
 * Created by dendy-prtha on 18/03/2019.
 * OAuth2 for facebook
 * https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-2/
 */

import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo{

    public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }

    @Override
    public String getImageUrl() {
        if(attributes.containsKey("picture")) {
            Map<String, Object> pictureObj = (Map<String, Object>) attributes.get("picture");
            if(pictureObj.containsKey("data")) {
                Map<String, Object>  dataObj = (Map<String, Object>) pictureObj.get("data");
                if(dataObj.containsKey("url")) {
                    return (String) dataObj.get("url");
                }
            }
        }
        return null;
    }
}
