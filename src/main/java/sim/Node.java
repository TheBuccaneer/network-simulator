package sim;

import java.util.concurrent.PriorityBlockingQueue;

public abstract class Node
{
    final private long address;

    final private PriorityBlockingQueue<Message> receiveMessageQueue = new PriorityBlockingQueue<Message>();

    final Thread workThread;
    final Thread receiveThread;

    protected enum MessageType
    {
        BROADCAST,
        UNICAST
    }

    public Node()
    {
        Network tempNetwork = Network.getInstance();
        this.address = tempNetwork.registerNode(this);
        this.workThread = new Thread(this::internalWork);
        this.receiveThread = new Thread(this::internalReceive);
    }

    public void putNextMessage(Message m)
    {
        this.receiveMessageQueue.put(m);
    }

    /*
    ####################################
    ##########NETWORK STUFF#############
    ####################################
     */

    public long getRandomAddress()
    {
        return Network.getInstance().getRandomAddress(this.address);
    }

    public void send(final MessageType type, final long receiverAddress, String message)
    {
        Message m = new Message(this.address, receiverAddress, message);
        Network n = Network.getInstance();

        switch(type)
        {
            case UNICAST:
                n.unicast(m);
            break;
            case BROADCAST: n.broadcast(m);
            break;

        }

    }


    /*
    ####################################
    ##########THREAD METHODS############
    ####################################
     */

    private void internalWork()
    {
        //either loop around work or loop in work
        this.work();
    }

    private void internalReceive()
    {
        try
        {
            while (!Thread.currentThread().isInterrupted())
            {
                final Message m = this.receiveMessageQueue.take();
                this.receiveNewMessage(m);
            }
        } catch (InterruptedException e)
        {
            System.out.println("Node with address: " + this.address + " has stopped receiving messages");
        }
    }

    public void stop()
    {
        this.receiveThread.interrupt();
        this.workThread.interrupt();
    }

    public void start()
    {
        this.receiveThread.start();
        this.workThread.start();
    }

    /*
    ########################################################
    ##########ABSTRACT METHODS TO BE IMPLEMENTED############
    ########################################################
  */
    protected abstract void work();

    protected abstract void receiveNewMessage(Message m);


    public long getAddress()
    {
        return this.address;
    }
}
