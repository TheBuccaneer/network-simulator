package sim;

public class Message implements Comparable<Message>
{

    private long senderAddress;

    private long receiverAddress;

    private int delay;

    private String payload;

    public Message(long senderAddress, long receiverAddress, String payload)
    {
        this.senderAddress = senderAddress;
        this.receiverAddress = receiverAddress;
        this.payload = payload;
    }

    public long getSenderAddress()
    {
        return this.senderAddress;
    }

    public long getReceiverAddress()
    {
        return this.receiverAddress;
    }

    public String getPayload()
    {
        return this.payload;
    }

    public int getDelay()
    {
        return this.delay;
    }

    public void addDelay(int d)
    {
        this.delay += d;
    }

    @Override
    public int compareTo(Message o)
    {
        return Integer.compare(this.getDelay(), o.getDelay());
    }
}
