package hwr.oop.applicationTest;

import hwr.oop.application.CreateUserService;
import hwr.oop.inports.CreateUserUseCase;
import hwr.oop.application.User;
import hwr.oop.application.AppData;
import hwr.oop.outports.LoadPort;
import hwr.oop.outports.SavePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateUserTest {
    @Mock
    private SavePort savePort;
    private AppData appData;
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp() {
        appData = new AppData(new ArrayList<>(),new ArrayList<>());
        LoadPort loadPort = () -> appData;
        savePort = mock();
        createUserUseCase = new CreateUserService(savePort, loadPort);
    }

    @Test
    void createUserTest() {
         String name = "Test";

         User user = createUserUseCase.createUser(name);

         assertThat(user.getName()).isEqualTo(name);
         verify(savePort).saveData(any());
    }
}
