package hwr.oop.userinterface;

import hwr.oop.application.CreateUserService;
import hwr.oop.application.ValidateUserService;
import hwr.oop.outports.LoadPort;
import hwr.oop.outports.SavePort;


public class Cli {
    private final Login login;
    public Cli(LoadPort loadPort, SavePort savePort) {
        login = new Login(System.in,System.out,new MainMenu(),new ValidateUserService(loadPort),new CreateUserService(savePort,loadPort));
    }
    public void startCli(){
        login.start();
    }

}
