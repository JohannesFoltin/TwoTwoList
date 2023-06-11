package hwr.oop.applicationTest;

import hwr.oop.application.CreateTaskException;
import hwr.oop.application.User;
import hwr.oop.application.ValidateUserService;
import hwr.oop.application.ValidateUserUseCase;
import hwr.oop.persistence.AppData;
import hwr.oop.persistence.LoadPort;
import hwr.oop.persistence.UserNotInAppDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ValidateUserTest {
    private ValidateUserUseCase validateUserUseCase;
    private AppData appData;
    @BeforeEach
    void setUp(){

        appData = new AppData(new ArrayList<>(),new ArrayList<>());

        LoadPort loadPort = () -> appData;

        validateUserUseCase = new ValidateUserService(loadPort);

    }
    @Test
    void validateUserTest(){
        User user = new User(UUID.randomUUID(),"Test",null,null);

        appData.getUserList().add(user);

        User userTest = validateUserUseCase.validateUser(user.getName());

        assertThat(userTest).isEqualTo(user);

    }

    @Test
    void validateUserTest_Exception(){
        assertThatThrownBy(() -> validateUserUseCase.validateUser("Hallo"))
                .isInstanceOf(UserNotInAppDataException.class)
                .hasMessage("User not in appdata no shit");
    }
}
