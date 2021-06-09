import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

class MyKeyAdapter extends KeyAdapter
{
    private int[] codes;
    private boolean[] keyStates;
    private long[] releaseTimeStamps;
    
    public MyKeyAdapter() {
        this.codes = new int[] { 38, 40, 37, 39, 17, 27, 65, 90, 75, 77, 32 };
        this.keyStates = null;
        this.releaseTimeStamps = null;
        this.keyStates = new boolean[this.codes.length];
        this.releaseTimeStamps = new long[this.codes.length];
    }
    
    public int getIndexFromKeyCode(final int n) {
        for (int i = 0; i < this.codes.length; ++i) {
            if (this.codes[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        final int indexFromKeyCode = this.getIndexFromKeyCode(keyEvent.getKeyCode());
        if (indexFromKeyCode >= 0) {
            this.keyStates[indexFromKeyCode] = true;
        }
    }
    
    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        final int indexFromKeyCode = this.getIndexFromKeyCode(keyEvent.getKeyCode());
        if (indexFromKeyCode >= 0) {
            this.keyStates[indexFromKeyCode] = false;
            this.releaseTimeStamps[indexFromKeyCode] = System.currentTimeMillis();
        }
    }
    
    public boolean isKeyPressed(final int n) {
        final boolean b = this.keyStates[n];
        final long n2 = this.releaseTimeStamps[n];
        return b || System.currentTimeMillis() - n2 <= 5L;
    }
    
    public void debug() {
        System.out.print("Key states = {");
        for (int i = 0; i < this.codes.length; ++i) {
            System.out.print(" " + this.keyStates[i] + ((i < this.codes.length - 1) ? "," : ""));
        }
        System.out.println(" }");
    }
}