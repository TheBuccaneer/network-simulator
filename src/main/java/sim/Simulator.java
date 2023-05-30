package sim;

public class Simulator
{

    private Network network;

    private static Simulator simulator;

    public Simulator()
    {
        this.network = Network.getInstance();

    }

    public static Simulator getInstance()
    {
        if(Simulator.simulator == null)
            Simulator.simulator = new Simulator();

        return Simulator.simulator;
    }

    public void startSimulation()
    {
        this.network.start();
    }

    public void stopSimulator()
    {
        this.network.stop();
    }
}
