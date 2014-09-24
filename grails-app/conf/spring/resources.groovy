
import com.restfb.Facebook
import org.springframework.social.facebook.api.FacebookProfile
import org.springframework.social.facebook.api.impl.FacebookTemplate


void onCreate(FacebookUser user, FacebookAuthToken token) {
  Facebook facebook = new FacebookTemplate(token.accessToken.accessToken)
  FacebookProfile fbProfile = facebook.userOperations().userProfile

  //fill the name
  //fieldname ('fullname' at this example) is up to you
  user.username = fbProfile.name
}// Place your Spring DSL code here
beans = {
}
