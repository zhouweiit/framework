package org.zhouwei.test.lambda;

public class InterfaceTest {

    public static void main(String[] args) {
        interfaceA(()->{System.out.println("interfaceA method test");});
        interfaceB((a,b)->{System.out.println(a.getName() + ":" + b.getName());},new People("a",1),new People("b",1));
        interfaceC(a -> {System.out.println(a.getName());},new People("a",1));
        interfaceC(System.out::println,new People("a",1));
        interfaceB(InterfaceTest::print,new People("a",1),new People("b",1));
        interfaceD(() -> {System.out.println("test");});
        A a = new A();
        a.test();
    }

    public static void print(People a, People b) {
        System.out.print(a);
        System.out.println(b);
    }

    public static void interfaceD(InterfaceD d) {
        d.testA();
    }

    public static void interfaceC(InterfaceC c, People a) {
        c.test(a);
    }

    public static void interfaceB(InterfaceB b, People pa, People pb) {
        b.test(pa,pb);
    }

    public static void interfaceA(InterfaceA a) {
        a.testA();
    }

    public static void run() {
        Thread t = new Thread(()->{System.out.println("lambda");});
        t.start();
    }

}
