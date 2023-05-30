package sim;

import java.util.*;

public class Network
{
    private Map<Long, Node> parcips = new HashMap<>();

    private static Network network;

    private Network()
    {

    }


    public long registerNode(Node node)
    {
        Random r = new Random();
        long newAddress = 0;

        while(true)
        {
            newAddress = r.nextLong();

            if(newAddress == 0 || this.parcips.containsKey(newAddress))
                continue;

            break;
        }

        this.parcips.put(newAddress, node);
        Node recNode = this.parcips.get(newAddress);

        return newAddress;

    }

    public void unicast(Message m)
    {
        Node rec1Node = this.parcips.get(m.getReceiverAddress());
        rec1Node.putNextMessage(m);
    }

    public void broadcast(Message m)
    {
        for(Node n: this.parcips.values())
            if(n.getAddress() == m.getSenderAddress())
                continue;
            else
                n.putNextMessage(m);
    }



    public static Network getInstance()
    {
        if(Network.network == null)
            Network.network = new Network();
        return Network.network;
    }


    public void start()
    {
        for(Node n: this.parcips.values())
            n.start();
    }

    public void stop()
    {
        for(Node n: this.parcips.values())
            n.stop();
    }


    public long getRandomAddress(final long requestAddress)
    {
        long newRandomAdd = 0;
        List<Long> netAdds = new ArrayList<Long>(this.parcips.keySet());

        if(netAdds.size() < 2)
        {
            try{
                throw new RuntimeException("Network needs more than 2 nodes");
            } catch(RuntimeException e)
            {

            }
        }

        Random r = new Random();

        while(true)
        {
            newRandomAdd = netAdds.get(r.nextInt(netAdds.size()));
            if(newRandomAdd == requestAddress)
                continue;
            break;
        }

        return newRandomAdd;
    }
}
