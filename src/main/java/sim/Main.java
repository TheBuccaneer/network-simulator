package sim;



public class Main extends Node
{

    public static void main(String[] args) throws InterruptedException
    {
        Simulator s = Simulator.getInstance();
        new Main();
        new Main();
        new Main();
        new Main();
        new Main();
        new Main();
        new Main();
        new Main();
        new Main();

        s.startSimulation();

        Thread.sleep(1000);

        s.stopSimulator();
    }


    @Override
    protected void work()
    {
            String s = "Hello";
            long a = this.getRandomAddress();
            this.send(MessageType.UNICAST, a, s);
    }

    @Override
    protected void receiveNewMessage(Message m)
    {
        System.out.println("node " + this.getAddress() + "received from " + m.getSenderAddress());
    }
}
