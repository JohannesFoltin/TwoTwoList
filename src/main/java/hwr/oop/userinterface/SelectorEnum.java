package hwr.oop.userinterface;


//        try {
//
//            SelectorEnum input = SelectorEnum.valueOf(choice);
//            input.doSomething();
//
//        } catch (IllegalArgumentException e) {
//            throw new RuntimeException(e);
//        }

public enum SelectorEnum {
    LOGIN("1") {
        @Override
        public void doSomething() {

        }
    }, REGISTER("2") {
        @Override
        public void doSomething() {

        }
    };

    private final String inputString;

    SelectorEnum(String inputString) {

        this.inputString = inputString;
    }

    public abstract void doSomething();

}
