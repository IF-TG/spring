/**
 * 롬북으로 대체!
 */

/*
package ifTG.travelPlan.domain.build;

import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import java.time.LocalDate;


public class UserBuilder {
    private String userId;
    private String pw;
    private String name;
    private Sex sex;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;
    private UserAddress userAddress;

    public UserBuilder userId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder pw(String pw) {
        this.pw = pw;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder sex(Sex sex) {
        this.sex = sex;
        return this;
    }

    public UserBuilder birthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public UserBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder userAddress(UserAddress userAddress){
        this.userAddress = userAddress;
        return this;
    }

    public User build() {
        if(userAddress!=null){
            return new User(userId, pw, name, sex, birthDate, phoneNumber, email, userAddress);
        }
        else{
            return new User(userId, pw, name, sex, birthDate, phoneNumber, email);
        }
    }
}
*/
